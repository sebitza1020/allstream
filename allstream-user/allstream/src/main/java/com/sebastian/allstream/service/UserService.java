package com.sebastian.allstream.service;

import com.sebastian.allstream.model.DAO.Language;
import com.sebastian.allstream.model.DAO.User;
import com.sebastian.allstream.repository.LanguageRepository;
import com.sebastian.allstream.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<User> findAllUsers() throws Exception {
        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            throw new Exception("Users list not found!");
        } else {
            return userList;
        }
    }

    public User saveUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("User is null!");
        } else {
            return this.userRepository.save(user);
        }
    }

    public User addUser(User user, int languageId) {
        Language language = null;
        try {
            language = languageRepository.findById(languageId);
        } catch (RuntimeException e) {
            throw new RuntimeException("Language not found!");
        }

        user.setLanguage(language);
        return userRepository.save(user);
    }

    public User findUserById(int id) throws RuntimeException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found!");
        } else {
            return user;
        }
    }

    public void deleteUserById(int id) {
        User user = null;
        try {
            user = userRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Employee not found!");
        }
        userRepository.delete(user);
    }

    public User updateUser(User user, int userId, int languageId) {
        User updatedUser = findUserById(userId);

        updatedUser.setUserName(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(updatedUser.getPassword());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setBirthDate(user.getBirthDate());

        Language language = languageRepository.findById(languageId);
        if (language == null) {
            throw new RuntimeException("Language not found!");
        }

        updatedUser.setLanguage(language);
        updatedUser.setAdmin(user.isAdmin());

        userRepository.save(updatedUser);
        return updatedUser;
    }
}

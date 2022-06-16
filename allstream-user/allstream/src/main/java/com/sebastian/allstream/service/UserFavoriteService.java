package com.sebastian.allstream.service;

import com.sebastian.allstream.model.DAO.User;
import com.sebastian.allstream.model.DAO.UserFavorite;
import com.sebastian.allstream.repository.UserFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoriteService {

    @Autowired
    UserFavoriteRepository userFavoriteRepository;

    public List<UserFavorite> findAllUsersFavorites() {
        List<UserFavorite> userFavoritesList = userFavoriteRepository.findAll();
        if (userFavoritesList.size() == 0) {
            throw new RuntimeException("User's favorites not found!");
        } else {
            return userFavoritesList;
        }
    }

    public UserFavorite findUsersFavoriteById(int id) {
        UserFavorite userFavorite = userFavoriteRepository.findById(id);
        if (userFavorite == null) {
            throw new RuntimeException("User's favorite not found!");
        } else {
            return userFavorite;
        }
    }

    public UserFavorite saveUserFavorite(UserFavorite userFavorite) {
        if (userFavorite == null) {
            throw new RuntimeException("User favorite is null!");
        } else {
            return this.userFavoriteRepository.save(userFavorite);
        }
    }

    public void deleteUserFavoriteById(int id) {
        UserFavorite userFavorite = null;
        try {
            userFavorite = userFavoriteRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Employee not found!");
        }
        userFavoriteRepository.delete(userFavorite);
    }
}

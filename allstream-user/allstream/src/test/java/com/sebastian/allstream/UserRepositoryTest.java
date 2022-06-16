package com.sebastian.allstream;

import com.sebastian.allstream.model.DAO.Language;
import com.sebastian.allstream.model.DAO.User;
import com.sebastian.allstream.repository.LanguageRepository;
import com.sebastian.allstream.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    public void testCreateUser() {
        User user = new User();

        user.setUserName("alex1999");
        user.setEmail("alexicuza@yahoo.com");
        user.setPassword("acuza");
        user.setFirstName("Alexandru");
        user.setLastName("Cuza");
        user.setBirthDate(LocalDate.parse("15-09-1988", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user.setLanguage(languageRepository.findById(2));
        user.setAdmin(false);

        User savedUser = userRepository.save(user);
        User existUser = testEntityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(user.getUserName()).isEqualTo(existUser.getUserName());
    }
}

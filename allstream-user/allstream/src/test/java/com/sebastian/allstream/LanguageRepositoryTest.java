package com.sebastian.allstream;

import com.sebastian.allstream.model.DAO.Language;
import com.sebastian.allstream.repository.LanguageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class LanguageRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    public void testAddLanguage() {
        Language language = new Language();
        language.setLanguageName("FR");

        Language savedLanguage = languageRepository.save(language);
        Language existLanguage = testEntityManager.find(Language.class, savedLanguage.getLanguageId());

        Assertions.assertThat(language.getLanguageName()).isEqualTo(existLanguage.getLanguageName());
    }
}

package com.sebastian.allstream.service;

import com.sebastian.allstream.model.DAO.Language;
import com.sebastian.allstream.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public Language saveLanguage(Language language) throws Exception {
        if (language.isValid()) {
            return languageRepository.save(language);
        } else {
            throw new Exception("Language not found!");
        }
    }

    public Language findLanguageById(int id) throws Exception {
        Language language = this.languageRepository.findById(id);
        if (language == null) {
            throw new Exception("Language not found!");
        } else {
            return language;
        }
    }

    public List<Language> getAllLanguages() {
        return this.languageRepository.findAll();
    }

    public Language updateLanguage(Language language, int id) throws Exception {
        Language updatedLanguage = findLanguageById(id);
        if (updatedLanguage.isValid()) {
            updatedLanguage.setLanguageName(language.getLanguageName());
        } else {
            throw new Exception("Language not found!");
        }
        languageRepository.save(updatedLanguage);
        return updatedLanguage;
    }

    public void deleteLanguageById(int id) throws Exception {
        Language language = null;
        try {
            language = languageRepository.findById(id);
        } catch (RuntimeException e) {
            throw new Exception("Language not found!");
        }
        languageRepository.delete(language);
    }
}

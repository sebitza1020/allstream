package com.sebastian.allstream.repository;

import com.sebastian.allstream.model.DAO.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Override
    List<Language> findAll();

    Language findById(int id);
}

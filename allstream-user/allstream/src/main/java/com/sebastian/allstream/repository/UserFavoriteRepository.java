package com.sebastian.allstream.repository;

import com.sebastian.allstream.model.DAO.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Integer> {
    UserFavorite findById(int id);
}

package com.sebastian.allstream.repository;

import com.sebastian.allstream.model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
}

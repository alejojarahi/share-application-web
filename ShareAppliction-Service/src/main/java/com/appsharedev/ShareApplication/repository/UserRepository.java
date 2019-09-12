package com.appsharedev.ShareApplication.repository;

import com.appsharedev.ShareApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // find is a key reserved by spring to created queries
    User findUserByDocument(int document);
    User findUserByEmail(String email);

    // exists is a key reserved by spring to created checks
    boolean existsByEmail(String email);
    boolean existsByDocument(Integer document);
}
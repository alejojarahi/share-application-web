package com.appsharedev.ShareApplication.repository;

import com.appsharedev.ShareApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByDocument(int document);
}
package com.appsharedev.ShareApplication.repository;

import com.appsharedev.ShareApplication.model.Rol;
import com.appsharedev.ShareApplication.model.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    // find is a key reserved by spring to created queries
    Optional<Rol> findByRolname(RolName rolname);
}

package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.Rol;
import com.appsharedev.ShareApplication.model.enums.RolName;
import com.appsharedev.ShareApplication.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    // Query to select a user by the name
    public Optional<Rol> getByRolName(RolName rolname) {
        return rolRepository.findByRolname(rolname);
    }
}

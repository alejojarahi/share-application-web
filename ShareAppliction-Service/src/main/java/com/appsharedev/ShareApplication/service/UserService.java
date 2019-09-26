package com.appsharedev.ShareApplication.service;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.model.dto.UserDTO;
import com.appsharedev.ShareApplication.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper; // This class for the Entity to DTO conversion

    @Autowired
    UserRepository userRepository;

    // Query select all users
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> usersDTO = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
        return usersDTO;
    }

    // Query select user with the document
    public User getUserByDocument(int document) {
        return userRepository.findUserByDocument(document);
    }

    // Query select user with the document
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    // Insert new row in the table user
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    // Query for check that exist to user with parameter email
    public boolean existUserByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    // Query for check that exist to user with parameter document
    public boolean existUserByDocument(Integer document) {
        return this.userRepository.existsByDocument(document);
    }

    // Query with filters for get the list of users for pagination
    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(this::convertToUserDto);
    }

    // Convert Users to DTO
    private UserDTO convertToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        //conversion here
        userDTO.setDocument(user.getDocument());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}

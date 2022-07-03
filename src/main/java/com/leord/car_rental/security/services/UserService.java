package com.leord.car_rental.security.services;

import com.leord.car_rental.security.models.User;
import com.leord.car_rental.security.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User role(String role) {

        return userRepository.findByRole(role);
    }

    public User username(String username) {

        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findById(int id) {
    	
    	return userRepository.findById(id);
    }

}

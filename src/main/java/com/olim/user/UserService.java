package com.olim.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean saveUser(User user) {
        user = userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }
    public boolean deleteUser(Long id){
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }
    public User getUser(Long id)  {
        return userRepository.findById(id).get();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
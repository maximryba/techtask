package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUser() {
        return this.userRepository.findUserWithMaxTotalDeliveredIn2003();
    }

    @Override
    public List<User> findUsers() {
        return this.userRepository.findUsersWithPaidOrdersIn2010();
    }
}

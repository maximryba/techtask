package com.example.techtask.controller;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Attention! Only DI and service interaction applicable in this class. Each controller method
 * should only contain a call to the corresponding service method
 */
@RestController
@RequestMapping("api/v1/users")
public class UserController {

  // DI here
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("desired-user")
  public User findUser() {
    return this.userService.findUser();
  }

  @GetMapping("desired-users")
  public List<User> findUsers() {
    return this.userService.findUsers();
  }
}

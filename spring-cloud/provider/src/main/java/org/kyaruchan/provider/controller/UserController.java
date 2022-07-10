package org.kyaruchan.provider.controller;
import org.kyaruchan.model.bean.User;
import org.kyaruchan.provider.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = "application/json; charset=UTF-8")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/append")
    public Boolean appendUser(User user){
        System.out.println("===Provider: I am invoking===");
        return userService.appendUser(user);
    }

    @RequestMapping(path = "/getById/{id}")
    public User getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        System.out.println("provider-getUser: " + user.toString());
        return user;
    }

    @RequestMapping(path = "/getInDatabase/{dbName}")
    public List<User> getUserInDatabase(@PathVariable String dbName){
        List<User> users = userService.getUserInDatabase(dbName);
        System.out.println("provider-getUserInDatabase: " + users);
        return users;
    }

    @RequestMapping(path = "/getAll")
    public List<User> getAllUser(){
        List<User> users = userService.getAll();
        System.out.println("provider-getAllUser: " + users);
        return users;
    }
}

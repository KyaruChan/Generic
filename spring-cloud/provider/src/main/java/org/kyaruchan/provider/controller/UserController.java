package org.kyaruchan.provider.controller;
import org.kyaruchan.model.bean.User;
import org.kyaruchan.provider.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path = "/append", method = RequestMethod.POST)
    public Boolean appendUser(User user){
        System.out.println("===Provider: I am invoking===");
        return userService.appendUser(user);
    }

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
    public User getUser(Long id){
        System.out.println("===Provider: I am invoking===");
        return userService.getUser(id);
    }

    @RequestMapping(path = "/getInDatabase", method = RequestMethod.GET)
    public List<User> getUserInDatabase(String dbName){
        System.out.println("===Provider: I am invoking===");
        return userService.getUserInDatabase(dbName);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<User> getAllUser(){
        System.out.println("===Provider: I am invoking===");
        return userService.getAll();
    }
}

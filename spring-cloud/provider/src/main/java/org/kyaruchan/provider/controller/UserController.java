package org.kyaruchan.provider.controller;
import org.kyaruchan.model.bean.User;
import org.kyaruchan.provider.service.UserService;
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

    @RequestMapping(path = "/getById", method = RequestMethod.GET)
    public User getUser(long id){
        return userService.getUser(id);
    }

    @RequestMapping(path = "/getInDatabase", method = RequestMethod.GET)
    public List<User> getUserInDatabase(String dbName){
        return userService.getUserInDatabase(dbName);
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.getAll();
    }
}

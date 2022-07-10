package org.kyaruchan.consumer.controller;
import org.kyaruchan.model.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = "application/json; charset=UTF-8")
public class UserController {
    private static final String REQUEST_URL_PREFIX = "http://localhost:8100/user";
    private RestTemplate restTemplate;

    @Autowired
    public UserController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @RequestMapping(path = "/append")
    public Boolean appendUser(User user){
        System.out.println("===Consumer: I am prepared to consume appendUser===");
        return restTemplate.postForObject(REQUEST_URL_PREFIX + "/append", user, Boolean.class);
    }

    @RequestMapping(path = "/getById/{id}")
    public User getUser(@PathVariable Long id){
        System.out.println("===Consumer: I am prepared to consume getUser===");
        return restTemplate.getForObject(REQUEST_URL_PREFIX + "/getById/{id}", User.class, id);
    }

    @RequestMapping(path = "/getInDatabase/{dbName}")
    public List<User> getUserInDatabase(@PathVariable String dbName){
        System.out.println("===Consumer: I am prepared to consume getUserInDatabase===");
        return restTemplate.getForObject(REQUEST_URL_PREFIX + "/getInDatabase/{dbName}", List.class, dbName);
    }

    @RequestMapping(path = "/getAll")
    public List<User> getAllUser(){
        System.out.println("===Consumer: I am prepared to consume getAllUser===");
        return restTemplate.getForObject(REQUEST_URL_PREFIX + "/getAll", List.class);
    }
}

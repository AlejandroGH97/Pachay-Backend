package controller;


import business.UserService;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    User newUser(@RequestBody User newUser){
        return service.save(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable String id){
        return service.update(newUser, id);
    }
}

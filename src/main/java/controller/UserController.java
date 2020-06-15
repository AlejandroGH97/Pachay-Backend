package controller;


import business.UserService;
import controller.config.jwtApi.MyUserDetailsService;
import controller.config.util.JwtTokenUtil;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    ResponseEntity<?> newUser(@RequestBody User newUser){
        service.save(newUser);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(newUser.email);
        String jwt = jwtTokenUtil.createJwt(userDetails);
        HashMap<String,String> response = new HashMap<>();
        response.put("jwt",jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

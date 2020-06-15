package controller.config.jwtApi;

import business.UserService;
import controller.config.util.JwtTokenUtil;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody User user){ //TODO FALTA GENERAR EL TOKEN Y DEVOLVERLO EN EL RESPONSE

        User _user = service.findByEmail(user.email);

        UserDetails userDetails = null;

        try{
            userDetails = this.myUserDetailsService.loadUserByUsername(user.email);
        }
        catch (UsernameNotFoundException e){
            return new ResponseEntity<>("Email inválido.",HttpStatus.BAD_REQUEST);
        }

        if(_user.password.equals(user.password)){
            final String jwt = jwtTokenUtil.createJwt(userDetails);
            HashMap<String,String> response = new HashMap<>();
            response.put("jwt",jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>("Password inválido", HttpStatus.BAD_REQUEST);
    }
}

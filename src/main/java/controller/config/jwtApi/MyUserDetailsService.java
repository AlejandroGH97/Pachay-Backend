package controller.config.jwtApi;

import business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        entities.User user = userService.findByEmail(email);
        if( user != null){
            return new User(user.email, user.password, new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}

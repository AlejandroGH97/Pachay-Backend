package business;


import data.entities.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User save(User newUser){
        return repository.save(newUser);
    }

    public  User findByEmail(String email){
        return repository.findByEmail(email);
    }

    public User update(User newUser, String id){
        return repository.findById(id).map( user -> {
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(newUser.getPassword());
            return repository.save(user);
        }).orElseGet(()->{
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    public Boolean favorite(String postId, String email){
        User user = repository.findByEmail(email);
        if(user.getFavorites().contains(postId)){
            user.getFavorites().remove(postId);
            repository.save(user);
            return false;
        }
        else{
            user.getFavorites().add(postId);
            repository.save(user);
            return true;
        }
    }


}

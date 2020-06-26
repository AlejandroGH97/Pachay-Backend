package controller;

import business.TopicService;
import data.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> readAll(){
        return topicService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> newTopic(@RequestBody Topic topic){
        try{
            if(topicService.save(topic) == null){
                return new ResponseEntity<>("Invalid topic.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Topic created.", HttpStatus.OK);
        }
        catch (DuplicateKeyException e){
            return new ResponseEntity<>("Duplicate topic.", HttpStatus.BAD_REQUEST);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>("Invalid topic.", HttpStatus.BAD_REQUEST);
        }

    }
}

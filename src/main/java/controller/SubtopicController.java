package controller;

import business.SubtopicService;
import data.DTO.SubtopicDTO;
import data.entities.Subtopic;
import data.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtopic")
public class SubtopicController {

    @Autowired
    private SubtopicService subtopicService;

    @GetMapping
    public List<Subtopic> readAll(){
        return subtopicService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> newSubtopic(@RequestBody SubtopicDTO subtopic){

        Subtopic _subtopic = subtopicService.create(subtopic);

        try{
            if(subtopicService.save(_subtopic) == null){
                return new ResponseEntity<>("Invalid subtopic.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Subtopic created.", HttpStatus.OK);
        }
        catch (DuplicateKeyException e){
            return new ResponseEntity<>("Duplicate subtopic.", HttpStatus.BAD_REQUEST);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>("Invalid subtopic.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/topic")
    public List<Subtopic> filterByTopic(@RequestBody Topic topic){
        return subtopicService.findByTopic(topic.getTopic());
    }
}

package controller;

import business.SubtopicService;
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
    public ResponseEntity<?> newSubtopic(@RequestBody Subtopic subtopic){
        try{
            subtopicService.save(subtopic);
            return new ResponseEntity<>("Subtopic created.", HttpStatus.OK);
        }
        catch (DuplicateKeyException e){
            return new ResponseEntity<>("Duplicate subtopic.", HttpStatus.BAD_REQUEST);
        }

    }
}
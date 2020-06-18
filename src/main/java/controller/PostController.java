package controller;

import business.PostService;
import entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> readAll(){
        return postService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> newPost(@RequestBody Post post){


        postService.save(post);

        return new ResponseEntity<>("Post created.", HttpStatus.OK);
    }
}

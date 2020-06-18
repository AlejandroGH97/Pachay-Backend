package controller;

import business.PostService;
import data.DTO.PostDTO;
import data.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> newPost(@RequestBody PostDTO post){
        post.setDate(LocalDate.now());

        Post _post = postService.create(post);

        postService.save(_post);

        return new ResponseEntity<>("Post created.", HttpStatus.OK);
    }
}

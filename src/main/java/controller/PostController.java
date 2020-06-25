package controller;

import business.PostService;
import business.TopicService;
import business.UserService;
import controller.config.util.JwtTokenUtil;
import data.DTO.PostDTO;
import data.entities.Post;
import data.entities.Topic;
import data.entities.User;
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

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public List<Post> readAll(){
        return postService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> newPost(@RequestBody PostDTO post, @RequestHeader(name = "Authorization") String jwt){

        String jwt_token = jwt.substring(7);

        User author = userService.findByEmail(jwtTokenUtil.extractUsername(jwt_token));

        post.setAuthor(author);

        post.setDate(LocalDate.now());

        Post _post = postService.create(post);

        postService.save(_post);

        return new ResponseEntity<>("Post created.", HttpStatus.OK);
    }

    @GetMapping("/topic")
    public List<Post> getPostByTopic(@RequestBody Topic topic){
        return postService.findByTopic(topic.topic);
    }
}

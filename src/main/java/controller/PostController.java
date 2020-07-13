package controller;

import business.PostService;
import business.UserService;
import controller.config.util.JwtTokenUtil;
import data.DTO.PostDTO;
import data.entities.Post;
import data.entities.Subtopic;
import data.entities.Topic;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
        List<Post> response = postService.findAll();
        response.sort((o1, o2) -> {
            if( o1.getDate().before(o2.getDate())) return 1;
            if( o1.getDate().after(o2.getDate())) return -1;
            return 0;
        });
        return response;
    }

    @PostMapping
    public ResponseEntity<?> newPost(@RequestBody PostDTO post, @RequestHeader(name = "Authorization") String jwt){

        String jwt_token = jwt.substring(7);

        User author = userService.findByEmail(jwtTokenUtil.extractUsername(jwt_token));

        post.setAuthor(author);

        post.setDate(new java.util.Date());

        Post _post = postService.create(post);

        postService.save(_post);

        return new ResponseEntity<>("Post created.", HttpStatus.OK);
    }

    @PostMapping("/topic")
    public List<Post> getPostByTopic(@RequestBody Topic topic){
        List<Post> response = postService.findByTopic(topic.getTopic());
        response.sort((o1, o2) -> {
            if( o1.getDate().before(o2.getDate())) return 1;
            if( o1.getDate().after(o2.getDate())) return -1;
            return 0;
        });
        return response;

    }

    @PostMapping("/topic/subtopic")
    public List<Post> getPostBySubtopic(@RequestBody Subtopic subtopic){
        List<Post> response = postService.findBySubtopic(subtopic.getSubtopic());
        response.sort((o1, o2) -> {
            if( o1.getDate().before(o2.getDate())) return 1;
            if( o1.getDate().after(o2.getDate())) return -1;
            return 0;
        });
        return response;
    }

    @PostMapping("/topic/subtopic/rating")
    public List<Post> getPostByRating(@RequestBody Subtopic subtopic){
        List<Post> response = postService.findBySubtopic(subtopic.getSubtopic());
        System.out.println(response.size());
        response.sort(Comparator.comparingInt(Post::getRating).reversed());

        return response;
    }

    @GetMapping("/author")
    public List<Post> getPostByAuthor(@RequestHeader(name = "Authorization") String jwt){
        String jwt_token = jwt.substring(7);

        User author = userService.findByEmail(jwtTokenUtil.extractUsername(jwt_token));

        return postService.findByAuthor(author);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@RequestHeader(name = "Authorization") String jwt, @PathVariable String postId){
        String jwt_token = jwt.substring(7);

        String author = jwtTokenUtil.extractUsername(jwt_token);

        try {
            if(postService.delete(postId, author)){
                return new ResponseEntity<>("Post deleted.",HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid request.",HttpStatus.BAD_REQUEST);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>("Post doesn't exist.",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<?> likePost(@RequestHeader(name = "Authorization") String jwt, @PathVariable String postId){
        String jwt_token = jwt.substring(7);

        String user = jwtTokenUtil.extractUsername(jwt_token);

        try{
            HashMap<String,Integer> response = new HashMap<>();
            response.put("rating", postService.like(postId, user));
            return new ResponseEntity<>(response,HttpStatus.OK);

        }
        catch (NullPointerException e){
            return new ResponseEntity<>("Invalid request.",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dislike/{postId}")
    public ResponseEntity<?> dislikePost(@RequestHeader(name = "Authorization") String jwt, @PathVariable String postId){
        String jwt_token = jwt.substring(7);

        String user = jwtTokenUtil.extractUsername(jwt_token);

        try{
            HashMap<String,Integer> response = new HashMap<>();
            response.put("rating", postService.dislike(postId, user));
            return new ResponseEntity<>(response,HttpStatus.OK);

        }
        catch (NullPointerException e){
            return new ResponseEntity<>("Invalid request.",HttpStatus.BAD_REQUEST);
        }
    }


}

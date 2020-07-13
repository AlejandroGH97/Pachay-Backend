package business;


import data.DTO.PostDTO;
import data.entities.Post;
import data.entities.Subtopic;
import data.entities.Topic;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private SubtopicService subtopicService;

    @Autowired
    private UserService userService;

    public List<Post> findAll(){
        List<Post> response = postRepository.findAll();
        for(Post post: response){
            post.author.password = null;
            post.author.favorites = null;
        }
        return response;
    }

    public Post findOne(String postid){
        return postRepository.findByPostId(postid);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> findByTopic(String topic){

        Topic _topic = topicService.findByTopic(topic);

        List<Post> response = postRepository.findByTopic(_topic);

        for(Post post: response){
            post.author.password = null;
            post.author.favorites = null;
        }
        return response;
    }

    public List<Post> findBySubtopic(String subtopic){

        Subtopic _subtopic = subtopicService.findBySubtopic(subtopic);

        List<Post> response = postRepository.findBySubtopic(_subtopic);

        for(Post post: response){
            post.author.password = null;
            post.author.favorites = null;
        }
        return response;
    }

    public List<Post> findByAuthor(User author){

        List<Post> response = postRepository.findByAuthor(author);

        for(Post post: response){
            post.author.password = null;
            post.author.favorites = null;
        }
        return response;
    }

    public Post create(PostDTO _post){
        Post post = new Post();

        post.setAuthor(_post.getAuthor());
        post.setDate(_post.getDate());
        post.setDescription(_post.getDescription());
        post.setRating(0);
        post.setTitle(_post.getTitle());
        post.setTopic(topicService.findByTopic(_post.getTopic()));
        post.setSubtopic(subtopicService.findBySubtopic(_post.getSubtopic()));
        post.setVideos(_post.getVideos());
        post.setEjercicios(_post.getEjercicios());
        post.setSolucionario(_post.getSolucionario());
        post.setSoporte(_post.getSoporte());

        return post;
    }

    public Boolean delete(String postId, String author){

        Post _post = findOne(postId);

        if(_post.getAuthor().getEmail().equals(author)){
            postRepository.deleteByPostId(_post.getPostId());
            return true;
        }
        else{
            return false;
        }
    }

    public Integer like(String postId, String userEmail){
        Post post = findOne(postId);
        User _user = userService.findByEmail(userEmail);
        post.like(_user.getId());
        postRepository.save(post);
        return post.getRating();
    }

    public Integer dislike(String postId, String userEmail){
        Post post = findOne(postId);
        User _user = userService.findByEmail(userEmail);
        post.dislike(_user.getId());
        postRepository.save(post);
        return post.getRating();
    }

    public List<Post> findFavorites(String email){
        User user = userService.findByEmail(email);

        List<Post> favorites = new ArrayList<>();

        for(String postId : user.getFavorites()){
            favorites.add(findOne(postId));
        }

        for(Post post : favorites){
            post.getAuthor().password = null;
            post.getAuthor().favorites = null;
        }

        return favorites;

    }
}

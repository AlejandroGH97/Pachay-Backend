package business;


import data.DTO.PostDTO;
import data.entities.Post;
import data.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> findByTopicsTopic(Topic topic){
        return postRepository.findByTopicsTopic(topic);
    }

    public Post create(PostDTO _post){
        Post post = new Post();

        post.setAuthor(_post.getAuthor());
        post.setDate(_post.getDate());
        post.setDescription(_post.getDescription());
        post.setRating(0);
        post.setRatingCount(0);
        post.setTitle(_post.getTitle());
        post.setTopics(_post.getTopics());
        post.setVideos(_post.getVideos());

        return post;
    }
}

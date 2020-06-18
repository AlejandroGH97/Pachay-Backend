package business;


import entities.Post;
import entities.Topic;
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
}

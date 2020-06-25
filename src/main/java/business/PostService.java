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

    @Autowired
    private TopicService topicService;

    @Autowired
    private SubtopicService subtopicService;

    public List<Post> findAll(){
        List<Post> response = postRepository.findAll();
        for(Post post: response){
            post.author.password = null;
        }
        return response;
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> findByTopic(String topic){

        Topic _topic = topicService.findByTopic(topic);

        List<Post> response = postRepository.findByTopic(_topic);

        for(Post post: response){
            post.author.password = null;
        }
        return response;
    }

    public Post create(PostDTO _post){
        Post post = new Post();

        post.setAuthor(_post.getAuthor());
        post.setDate(_post.getDate());
        post.setDescription(_post.getDescription());
        post.setRating(0);
        post.setRatingCount(0);
        post.setTitle(_post.getTitle());
        post.setTopic(topicService.findByTopic(_post.getTopic()));
        post.setSubtopic(subtopicService.findBySubtopic(_post.getSubtopic()));
        post.setVideos(_post.getVideos());

        return post;
    }
}

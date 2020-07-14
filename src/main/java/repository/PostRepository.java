package repository;

import data.entities.Post;
import data.entities.Subtopic;
import data.entities.Topic;
import data.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    public List<Post> findByTopic(Topic topic);
    public List<Post> findBySubtopic(Subtopic topic);
    public List<Post> findByAuthor(User author);
    public Post findByPostId(String postid);
    public void deleteByPostId(String postid);
    public List<Post> findByValidated(Boolean status);
    public List<Post> findByValidatedAndSubtopic(Boolean status, Subtopic subtopic);
    public List<Post> findByValidatedAndAuthor(Boolean status, User user);

}

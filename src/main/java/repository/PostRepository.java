package repository;

import entities.Post;
import entities.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    public List<Post> findByTopicsTopic(Topic topic);
}

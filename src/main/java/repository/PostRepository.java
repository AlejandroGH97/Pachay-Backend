package repository;

import data.entities.Post;
import data.entities.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    public List<Post> findByTopic(Topic topic);
}

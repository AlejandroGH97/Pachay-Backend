package repository;

import data.entities.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
    public Topic findByTopic(String topic);
}

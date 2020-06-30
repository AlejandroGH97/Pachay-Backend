package repository;

import data.entities.Subtopic;
import data.entities.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubtopicRepository extends MongoRepository<Subtopic, String> {
    public Subtopic findBySubtopic(String subtopic);
    public List<Subtopic> findByTopic(Topic topic);
}

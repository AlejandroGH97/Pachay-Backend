package repository;

import data.entities.Subtopic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubtopicRepository extends MongoRepository<Subtopic, String> {
    public Subtopic findBySubtopic(String subtopic);
}

package business;

import data.entities.Subtopic;
import data.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SubtopicRepository;

import java.util.List;

@Service
public class SubtopicService {

    @Autowired
    private SubtopicRepository subtopicRepository;

    public List<Subtopic> findAll(){
        return subtopicRepository.findAll();
    }

    public Subtopic save(Subtopic subtopic){
        return subtopicRepository.save(subtopic);
    }

    public Subtopic findBySubtopic(String subtopic){
        return subtopicRepository.findBySubtopic(subtopic);
    }
}

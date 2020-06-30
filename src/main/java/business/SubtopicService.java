package business;

import data.DTO.SubtopicDTO;
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

    @Autowired
    private TopicService topicService;

    public List<Subtopic> findAll(){
        return subtopicRepository.findAll();
    }

    public Subtopic save(Subtopic subtopic){
        if(subtopic.getSubtopic() == null) return null;
        return subtopicRepository.save(subtopic);
    }

    public Subtopic findBySubtopic(String subtopic){
        return subtopicRepository.findBySubtopic(subtopic);
    }

    public List<Subtopic> findByTopic(String topic){
        Topic _topic = topicService.findByTopic(topic);

        return subtopicRepository.findByTopic(_topic);
    }

    public Subtopic create(SubtopicDTO subtopicDTO){
        Subtopic subtopic = new Subtopic();

        subtopic.setTopic(topicService.findByTopic(subtopicDTO.getTopic()));

        subtopic.setSubtopic(subtopicDTO.getSubtopic());

        return subtopic;
    }
}

package business;

import data.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TopicRepository;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public Topic save(Topic topic){
        if(topic.getTopic() == null) return null;
        return topicRepository.save(topic);
    }

    public Topic findByTopic(String topic){
        return topicRepository.findByTopic(topic);
    }
}

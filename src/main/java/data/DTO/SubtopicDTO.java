package data.DTO;

import java.io.Serializable;

public class SubtopicDTO implements Serializable {

    public String id;

    public String subtopic;

    public String topic;

    public SubtopicDTO() {
    }

    public SubtopicDTO(String id, String subtopic, String topic) {
        this.id = id;
        this.subtopic = subtopic;
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

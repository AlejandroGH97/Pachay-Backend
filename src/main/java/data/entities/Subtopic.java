package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subtopic")
public class Subtopic {

    @Id
    public String id;

    @Indexed(unique = true, sparse = true)
    public String subtopic;

    @DBRef
    public Topic topic;

    public Subtopic() {
    }

    public Subtopic(String id, String subtopic, Topic topic) {
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subtopic")
public class Subtopic {

    @Id
    public String id;

    @Indexed(unique = true, sparse = true)
    public String subtopic;

    public Subtopic() {
    }

    public Subtopic(String id, String subtopic) {
        this.id = id;
        this.subtopic = subtopic;
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
}

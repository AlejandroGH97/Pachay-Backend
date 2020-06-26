package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(collection = "posts")
public class Post {

    @Id
    public String postId;

    public String title;

    public String description;

    public int rating = 0;

    public Date date;

    private int ratingCount = 0;

    @DBRef
    public User author;

    @DBRef
    public Topic topic;

    @DBRef
    public Subtopic subtopic;

    public List<String> videos;

    public Post() {
    }

    public Post(String postId, String title, String description, int rating, Date date, int ratingCount, User author, Topic topic, Subtopic subtopic, List<String> videos) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.ratingCount = ratingCount;
        this.author = author;
        this.topic = topic;
        this.subtopic = subtopic;
        this.videos = videos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Subtopic getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(Subtopic subtopic) {
        this.subtopic = subtopic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

}

package data.DTO;

import data.entities.Topic;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PostDTO implements Serializable {

    public String postId;

    public String title;

    public String description;

    public int rating = 0;

    public LocalDate date;

    private int ratingCount = 0;

    public User author;

    public List<Topic> topics;

    public List<String> videos;

    public PostDTO() {
    }

    public PostDTO(String postId, String title, String description, int rating, LocalDate date, int ratingCount, List<Topic> topics, List<String> videos) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.ratingCount = ratingCount;
        this.topics = topics;
        this.videos = videos;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

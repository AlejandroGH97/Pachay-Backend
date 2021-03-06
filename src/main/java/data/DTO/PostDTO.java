package data.DTO;

import data.entities.Topic;
import data.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PostDTO implements Serializable {

    public String postId;

    public String title;

    public String description;

    public int rating = 0;

    public Date date;

    private int ratingCount = 0;

    public User author;

    public String topic;

    public String subtopic;

    public List<String> videos;

    public List<String> ejercicios;

    public List<String> solucionario;

    public List<String> soporte;

    public PostDTO() {
    }

    public PostDTO(String postId, String title, String description, int rating, Date date, int ratingCount, User author, String topic, String subtopic, List<String> videos, List<String> ejercicios, List<String> solucionario, List<String> soporte) {
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
        this.ejercicios = ejercicios;
        this.solucionario = solucionario;
        this.soporte = soporte;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public List<String> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<String> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public List<String> getSolucionario() {
        return solucionario;
    }

    public void setSolucionario(List<String> solucionario) {
        this.solucionario = solucionario;
    }

    public List<String> getSoporte() {
        return soporte;
    }

    public void setSoporte(List<String> soporte) {
        this.soporte = soporte;
    }
}

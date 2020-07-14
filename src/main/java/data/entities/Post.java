package data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Document(collection = "posts")
public class Post {

    @Id
    public String postId;

    public String title;

    public String description;

    public int rating = 0;

    public Date date;

    @DBRef
    public User author;

    @DBRef
    public Topic topic;

    @DBRef
    public Subtopic subtopic;

    public Boolean validated = false;

    public List<String> videos;

    public HashMap<String, Integer> likes = new HashMap<>();

    public HashMap<String, Integer> favorited = new HashMap<>();

    public List<String> ejercicios;

    public List<String> solucionario;

    public List<String> soporte;

    public Post() {
    }

    public Post(String postId, String title, String description, int rating, Date date, User author, Topic topic, Subtopic subtopic, List<String> videos, List<String> ejercicios, List<String> solucionario, List<String> soporte) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.author = author;
        this.topic = topic;
        this.subtopic = subtopic;
        this.videos = videos;
        this.ejercicios = ejercicios;
        this.solucionario = solucionario;
        this.soporte = soporte;
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

    public HashMap<String, Integer> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String, Integer> likes) {
        this.likes = likes;
    }

    public HashMap<String, Integer> getFavorited() {
        return favorited;
    }

    public void setFavorited(HashMap<String, Integer> favorited) {
        this.favorited = favorited;
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

    public Boolean like(String userId){
        if(likes.containsKey(userId)){
            if(likes.get(userId).equals(-1)){
                likes.replace(userId,1);
                setRating(getRating()+2);
                return true;
            }
            else if(likes.get(userId).equals(0)){
                likes.replace(userId,1);
                setRating(getRating()+1);
                return true;
            }
            else if(likes.get(userId).equals(1)){
                likes.replace(userId,0);
                setRating(getRating()-1);
                return false;
            }
        }
        else{
            likes.put(userId,1);
            setRating(getRating()+1);
            return true;
        }
        return false;
    }

    public Boolean dislike(String userId){
        if(likes.containsKey(userId)){
            if(likes.get(userId).equals(1)){
                likes.replace(userId,-1);
                setRating(getRating()-2);
                return true;
            }
            else if(likes.get(userId).equals(0)){
                likes.replace(userId,-1);
                setRating(getRating()-1);
                return true;
            }
            else if(likes.get(userId).equals(-1)){
                likes.replace(userId,0);
                setRating(getRating()+1);
                return false;
            }
        }
        else{
            likes.put(userId,-1);
            setRating(getRating()-1);
            return true;
        }
        return false;
    }

    public void favorite(String userId){
        if(favorited.containsKey(userId)){
            if(favorited.get(userId).equals(1)){
                favorited.put(userId,0);
            }
            else {
                favorited.put(userId,1);
            }
        }
        else{
            favorited.put(userId,1);
        }
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}

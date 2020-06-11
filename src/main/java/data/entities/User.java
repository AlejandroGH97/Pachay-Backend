package data.entities;


import org.springframework.data.annotation.Id;
import com.google.common.hash.Hashing;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.charset.StandardCharsets;

@Document(collection = "users")
public class User {

    @Id
    public String id;

    public String email;

    public String firstName;

    public String lastName;

    public String password;

    public int role;

    public User() { }

    public User(String id, String email, String firstName, String lastName, String password, int role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean verifyPassword(String password){
        return this.password.equals(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
    }
}

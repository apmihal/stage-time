package com.andrewmihalevich.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Invalid username")
    private String username;

    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//    @ManyToMany
//    private List<Dog> dogs;

    @OneToMany
    @JoinColumn(name = "uid")
    private List<ComedyShow> comedyShows;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = hashPassword(password);
    }

    public String getUsername() {
        return username;
    }

    private static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

//    public List<Dog> getDogs() {
//        return dogs;
//    }

//    public void setDogs(List<Dog> dogs) {
//        this.dogs = dogs;
//    }

//    public void addDog(Dog dog) {
//        this.dogs.add(dog);
//    }

    public List<ComedyShow> getComedyShows() {
        return comedyShows;
    }

    public void addComedyShow(ComedyShow comedyShow) {
        this.comedyShows.add(comedyShow);
    }


}

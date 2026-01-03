package it.cookie.utils.network.user;

import java.sql.Date;

import javafx.scene.image.Image;

public class user {
    String username;
    Image user_image;
    String user_image_path;
    String email;
    String JWT;
    public Date dateCreated;

    public user(String username, Image user_image, String user_image_path, String email, String JWT, Date dateCreated) {
        this.username = username;
        this.user_image = user_image;
        this.user_image_path = user_image_path;
        this.email = email;
        this.JWT = JWT;
        this.dateCreated = dateCreated;
    }

    public String GetUsername() {
        return username;
    }

    public Image GetUserImage() {
        return user_image;
    }

    public String GetUserImagePath() {
        return user_image_path;
    }

    public String GetEmail() {
        return email;
    }

    public String GetJWT() {
        return JWT;
    }

    public Date GetDateCreated() {
        return dateCreated;
    }

    public void SetJWT(String JWT) {
        this.JWT = JWT;
    }   

    public void SetUsername(String username) {
        this.username = username;
    }

    public void SetUserImage(Image user_image) {
        this.user_image = user_image;
    }

    public void SetUserImagePath(String user_image_path) {
        this.user_image_path = user_image_path;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    
}

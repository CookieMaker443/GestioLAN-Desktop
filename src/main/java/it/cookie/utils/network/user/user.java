package it.cookie.utils.network.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// serve per dire a jackson di ignorare i campi non presenti in questa classe
@JsonIgnoreProperties(ignoreUnknown = true)
public class user {
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("JWT")
    private String JWT;

    // Mappato 'createTime' (dal JSON creato dall API C#) su 'dateCreated' (qui in Java)
    @JsonProperty("createTime")
    private LocalDateTime dateCreated;

    public user() {}

    public user(String username, String email, String JWT, LocalDateTime dateCreated) {
        this.username = username;
        this.email = email;
        this.JWT = JWT;
        this.dateCreated = dateCreated;
    }

    // Getter e Setter standard (usato la minuscola per seguire gli standard Java)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getJWT() { return JWT; }
    public void setJWT(String JWT) { this.JWT = JWT; }

    public LocalDateTime getDateCreated() { return dateCreated; }
    public void setDateCreated(LocalDateTime dateCreated) { this.dateCreated = dateCreated; }

    
}

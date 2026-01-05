package it.cookie.utils.network.managers;

import it.cookie.utils.interfaces.observer.Observer;
import it.cookie.utils.interfaces.observer.Subject;
import it.cookie.utils.network.user.user;
import javafx.scene.image.Image;

public class SessionManager implements Observer{

    user current_user;
    private static SessionManager instance;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String GetUsername() {
        return current_user.GetUsername();
    }

    private void SetUsername(String username) {
        current_user.SetUsername(username);
    }

    public Image GetUserImage() {
        return current_user.GetUserImage();
    }

    private void SetUserImage(Image user_image) {
        current_user.SetUserImage(user_image);
    }

    public String GetUserImagePath() {
        return current_user.GetUserImagePath();
    }

    private void SetUserImagePath(String user_image_path) {
        current_user.SetUserImagePath(user_image_path);
    }

    public String GetEmail() {
        return current_user.GetEmail();
    }

    private void SetEmail(String email) {
        current_user.SetEmail(email);
    }

    public String GetJWT() {
        return current_user.GetJWT();
    }

    private void SetJWT(String JWT) {
        current_user.SetJWT(JWT);
    }

    @Override
    public void Update(Subject subject, Object state) {
        // Aggiorna tutti i valori dopo aver aggiornato il database
        // # TODO: Fixxare questa parte, aggiorare i dati per bene
        if(state instanceof user) {
            user u = (user) state;
            SetUsername(u.GetUsername());
            SetUserImage(u.GetUserImage());
            SetUserImagePath(u.GetUserImagePath());
            SetEmail(u.GetEmail());
            SetJWT(u.GetJWT());
        }
    }    
}

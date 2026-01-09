package it.cookie.utils.network.managers;

import it.cookie.utils.interfaces.observer.Observer;
import it.cookie.utils.interfaces.observer.Subject;
import it.cookie.utils.network.user.user;

public class SessionManager extends Subject implements Observer{

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
        return current_user.getUsername();
    }

    private void SetUsername(String username) {
        current_user.setUsername(username);
    }

    public String GetEmail() {
        return current_user.getEmail();
    }

    private void SetEmail(String email) {
        current_user.setEmail(email);
    }

    public String GetJWT() {
        return current_user.getJWT();
    }

    private void SetJWT(String JWT) {
        current_user.setJWT(JWT);
    }

    @Override
    public void Update(Subject subject, Object state) {
        // Aggiorna tutti i valori dopo aver aggiornato il database
        // # TODO: Fixxare questa parte, aggiorare i dati per bene
        if(state instanceof user u) {
            this.current_user = u;
            Notify(u); // Successo
        } else {
            this.current_user = null;
            Notify(null); // Fallimento: sveglia il LoginController per riabilitare il tasto!
        }
    }

    public void ClearSession() {
        current_user = null;
    }

    public boolean IsUserLogged() {
        return current_user != null;
    }
}

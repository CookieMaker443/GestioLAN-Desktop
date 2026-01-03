package it.cookie.utils.network.user;
import it.cookie.utils.interfaces.observer.Subject;

public class UserNetController extends Subject {

    // Salva i dati nel SessionManager
    public void UserLogin() {}    
    
    public void UserRegister() {}

    public void UserDelete() {}

    public void UserModify(user user) {
        // #TODO: qui chiama il notify per aggiornare i dati
        Notify(user);
    }
}

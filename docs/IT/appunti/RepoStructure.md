# JavaFX con il Pattern MVC

> Questo documento spiega come organizzare e strutturare un'applicazione JavaFX utilizzando il pattern Model-View-Controller (MVC), focalizzandosi sulla separazione della logica dal GUI.

## Layer Controller
Il layer controller contiene tutte le logiche per l'applicazione. La struttura del directory è la seguente:

- `src/main/java/it/cookie/progetti/controllers`

## Layer View
Il layer view consiste di tutti i componenti dell'interfaccia utente grafica. La struttura del directory è la seguente:

- `src/main/resources/FXML`

Per trovare file correlati, puoi navigare utilizzando lo stesso percorso sia per la logica che per il GUI. Ad esempio:

- `src/main/java/it/cookie/progetti/controllers/MainMenu/MainMenuController.java`
- `src/main/resources/FXML/MainMenu/MainMenuView.fxml`

## Layer Utility
Certi file di utilità sono localizzati nella directory "utils", come ad esempio:

- `src/main/java/it/cookie/utils/network/user/UserNetController.java`

Questi file sono progettati per essere riutilizzabili in diverse piattaforme, inclusa la versione desktop e quella mobile. Ad esempio, se sia un'app desktop che un'app smartphone devono avere una funzionalità di login, la logica dovrebbe rimanere consistente. Il `LoginController` dell'app desktop potrebbe essere simile al seguente:

```java
@FXML
@SuppressWarnings("unused")
private void handleLoginButtonAction() throws IOException {
    if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
        System.out.println("Username o Password vuoti!");
        SceneManager.getInstance().showAlert(bundle.getString("login.Warning"), bundle.getString("login.MISSING_FIELDS"), AlertType.WARNING);
        return;
    }
    // Disabilita il pulsante per prevenire lo spam
    loginButton.setDisable(true);

    String username = usernameField.getText();
    String password = passwordField.getText();
    System.out.println("Provo a fare il login con Username: " + username);

    // Una volta che SessionManager è pronto, chiama questo update()
    SessionManager.getInstance().Attach(this);

    // Passa la richiesta di login allo UserNetController e esegui il login
    userNetController.UserLogin(username, password);
}
```

Questo metodo include controlli e poi delega la logica dell'accesso effettiva a `UserNetController`.

Il `LoginController` per un'app smartphone implementerà controlli simili e passerà infine la richiesta di accesso allo `UserNetController`. La stessa logica si applica ai file all'interno della directory "utils".
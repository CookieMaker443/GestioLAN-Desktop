# JavaFX with MVC Pattern

> This document explains how to organize and structure a JavaFX application using the Model-View-Controller (MVC) pattern, focusing on the separation of logic from GUI components.

## Controller Layer
The controller layer contains all the logic for the application. The directory structure is as follows:

- `src/main/java/it/cookie/progetti/controllers`

## View Layer
The view layer consists of the graphical user interface components. The directory structure is as follows:

- `src/main/resources/FXML`

To find related files, you can navigate using the same path for both the logic and the GUI. For example:

- `src/main/java/it/cookie/progetti/controllers/MainMenu/MainMenuController.java`
- `src/main/resources/FXML/MainMenu/MainMenuView.fxml`

## Utility Layer
Certain utility files are located in the "utils" directory, such as:

- `src/main/java/it/cookie/utils/network/user/UserNetController.java`

These files are designed to be reusable across different platforms, including desktop and mobile applications. For instance, if both a desktop and a smartphone app need a login feature, the logic should remain consistent. The `LoginController` in the desktop application might look like this:

```java
@FXML
@SuppressWarnings("unused")
private void handleLoginButtonAction() throws IOException {
    if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
        System.out.println("Username or Password is empty!");
        SceneManager.getInstance().showAlert(bundle.getString("login.Warning"), bundle.getString("login.MISSING_FIELDS"), AlertType.WARNING);
        return;
    }
    // Disable the button to prevent spam
    loginButton.setDisable(true);

    String username = usernameField.getText();
    String password = passwordField.getText();
    System.out.println("Attempting login with Username: " + username);

    // Once SessionManager is ready, call this update()
    SessionManager.getInstance().Attach(this);

    // Pass the login request to UserNetController and execute the login
    userNetController.UserLogin(username, password);
}
```

This method includes checks and then delegates the actual login logic to `UserNetController`.

The `LoginController` for a smartphone application would implement similar controls and delegate the login request to `UserNetController`. The same logical flow applies to files within the "utils" directory.
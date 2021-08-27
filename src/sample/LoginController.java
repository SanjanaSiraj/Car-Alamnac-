package sample;

import Utils.LoginData;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginController {
    private Socket s;
    public TextField userNameField;
    public PasswordField passwordField;
    public Button resetButton;
    public Button submitButton;
    public void setSocket(Socket s){
        this.s = s;
    }

    public void resetLogin(ActionEvent actionEvent) {
        userNameField.clear();
        passwordField.clear();
    }

    public void submitLogin(ActionEvent actionEvent) {
        LoginData ld = new LoginData();
        String str = userNameField.getText();
        if (str.equalsIgnoreCase("viewer")) ld.setUsername("viewer");
        else ld.setUsername(str);
        ld.setPassword(passwordField.getText());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(ld);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

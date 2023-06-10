package lk.udith.chatapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public Button btnLoginId;
    public TextField txtUserName;
    public AnchorPane pane;
    static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName=txtUserName.getText();
        if (userName.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "User Name is required !!",
                    ButtonType.OK).show();
        }else {
            Stage window = (Stage) pane.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/udith/chatapp/view/ChatForm.fxml"))));
            window.setTitle("Live Chat");
        }

    }
}

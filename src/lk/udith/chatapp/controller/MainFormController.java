package lk.udith.chatapp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.udith.chatapp.util.Navigation;
import lk.udith.chatapp.util.Route;

import java.io.IOException;

public class MainFormController {
    public Button btnLoginId;
    public TextField txtUserName;
    public AnchorPane pane;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "User Name is required !!",
                    ButtonType.OK).show();
        } else {
            Navigation.navigate(Route.CHATROOM, pane);
        }

    }
}

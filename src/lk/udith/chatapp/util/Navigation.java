package lk.udith.chatapp.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Route route,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window=(Stage)Navigation.pane.getScene().getWindow();


        switch (route){
            case LOGIN:
                window.setTitle("Login Form");
                initUI("MainForm.fxml");
                break;

            case CHATROOM:
                window.setTitle("Live Chat");
                window.setMaxWidth(400.00);
                window.setMaxHeight(600.00);
                window.centerOnScreen();
                initUI("ChatForm.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/udith/chatapp/view/"+location)));
    }
}

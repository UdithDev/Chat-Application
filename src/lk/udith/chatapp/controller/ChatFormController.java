package lk.udith.chatapp.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatFormController extends Thread {
    public TextField txtMessage;
    public Label lblClientName;
    public VBox vBox;
    public AnchorPane pane;


    PrintWriter printWriter;
    Socket socket;
    private BufferedReader bufferedReader;
    private String username;


    private FileChooser fileChooser;
    private File filePath;

    public void sendImageMouseOnClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);

        printWriter.println(lblClientName.getText() + " " + "img" + filePath.getPath());
    }


    public void btnSendOnAction(MouseEvent mouseEvent) {
        String msg = txtMessage.getText();
        printWriter.println(lblClientName.getText() + ": " + msg);

        txtMessage.clear();

        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public void initialize() {
        String userName = MainFormController.userName;
        lblClientName.setText(userName);
        try {
            socket = new Socket("localhost", 2000);
            System.out.println("Socket is connected with server..!");
            bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

                StringBuilder fullMessage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMessage.append(tokens[i] + " ");
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }

                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);

                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!cmd.equalsIgnoreCase(lblClientName.getText())) {

                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lblClientName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12

                    if (!cmd.equalsIgnoreCase(lblClientName.getText() + ":")) {


                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2 = new Text(fullMessage + ": Me");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void txtSendMessageOnAction(ActionEvent actionEvent) {
    }
}

package com.INF122.TMGE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginportalController{

    @FXML
    private Button SigninBtn;
    @FXML
    private TextField pc1NameInput;
    @FXML
    private TextField pc2NameInput;
    @FXML
    private Label pc1NameDisplay;
    @FXML
    private Label pc2NameDisplay;
    @FXML
    private ImageView backBtn;
    @FXML
    private ImageView tetrisIcon;
    @FXML
    private ImageView drMarioIcon;
    @FXML
    private Pane pnSelectGame;
    @FXML
    private Pane pnSignIn;


    public void loginButtonClicked(ActionEvent event){
        System.out.println("You clicked signin!");
        if(event.getSource().equals(SigninBtn)) {
            if (!pc1NameInput.getText().isEmpty() || !pc2NameInput.getText().isEmpty()) {
                pc1NameDisplay.setText(pc1NameInput.getText());
                pc2NameDisplay.setText(pc2NameInput.getText());
                pnSelectGame.toFront();
            }
        }

    }

    public void handleMouseEvent(MouseEvent event) throws IOException {
        if(event.getSource().equals(backBtn)){
            pnSignIn.toFront();
        }
        if(event.getSource().equals(tetrisIcon)){
            System.out.println("tetris selected");

            try {
                Stage tetrisStage = new Stage();
                tetrisStage.setTitle("Tetris");
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = fxmlLoader.load(getClass().getResource("gameStage.fxml").openStream());

                if(!pc1NameInput.getText().isEmpty() && !pc2NameInput.getText().isEmpty()) {
                    Scene sceneMP = TetrisScene.generateMultiplayerScene();
                    tetrisStage.setScene(sceneMP);
                }
                else {
                    Scene sceneSP = TetrisScene.generateSingleplayerScene();
                    tetrisStage.setScene(sceneSP);
                }
                tetrisStage.show();
            }catch (IOException e){
                e.printStackTrace();
            }


        }
        if(event.getSource().equals(drMarioIcon)){
            System.out.println("Dr.Mario selected");
        }
    }

}

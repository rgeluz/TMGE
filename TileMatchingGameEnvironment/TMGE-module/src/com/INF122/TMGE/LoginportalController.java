package com.INF122.TMGE;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


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
    private Pane pnSelectGame;
    @FXML
    private Pane pnSignIn;


    public void loginButtonClicked(ActionEvent event){
        System.out.println("You clicked signin!");
        if(event.getSource().equals(SigninBtn)) {
            if (!pc1NameInput.getText().isEmpty()) {
                pc1NameDisplay.setText(pc1NameInput.getText());
            }
            if (!pc2NameInput.getText().isEmpty()) {
                pc2NameDisplay.setText(pc2NameInput.getText());
            }
            pnSelectGame.toFront();
        }

    }

    public void handleMouseEvent(MouseEvent event){
        if(event.getSource().equals(backBtn)){
            pnSignIn.toFront();
        }
    }

}

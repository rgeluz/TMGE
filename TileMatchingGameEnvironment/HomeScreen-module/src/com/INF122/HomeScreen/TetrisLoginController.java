package com.INF122.HomeScreen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.INF122.Tetris.TetrisMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TetrisLoginController {
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
    @FXML
    private Pane leftPane;
    @FXML
    private ImageView TMGE_logo;
    @FXML
    private ImageView tetrisIcon;
    @FXML
    private ImageView drMarioIcon;
    
    @FXML
    public void initialize() {
    	// load logo and icons
    	Image TMGELogo = null;
        try{
        	TMGELogo = new Image(new FileInputStream("resources/icons/icons8_game_controller_500px.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TMGE_logo.setImage(TMGELogo);
        
        Image backIcon = null;
        try{
        	backIcon = new Image(new FileInputStream("resources/icons/icons8_back_52px.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backBtn.setImage(backIcon);
        
        Image tetrisImage = null;
        try{
        	tetrisImage = new Image(new FileInputStream("resources/icons/icons8_tetris_100px_1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tetrisIcon.setImage(tetrisImage);
        
        Image drMarioImage = null;
        try{
        	drMarioImage = new Image(new FileInputStream("resources/icons/icons8_pill_48px.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        drMarioIcon.setImage(drMarioImage);	
        
    	leftPane.setStyle("-fx-background-color: linear-gradient(to bottom left, #4AB8BF,#2D75E8)");
    	SigninBtn.setStyle("-fx-background-color: #1E4E90;"+ "-fx-text-fill: #ffffff;");
    	SigninBtn.setOnMouseEntered(e -> SigninBtn.setStyle("-fx-background-color: #1E4E90;" + "-fx-text-fill: #73A3E5;"));
    	SigninBtn.setOnMouseExited(e -> SigninBtn.setStyle("-fx-background-color: #1E4E90;" + "-fx-text-fill: #ffffff;"));
    }
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
            //try {
                Stage tetrisStage = new Stage();
                tetrisStage.setTitle("Tetris");
                FXMLLoader fxmlLoader = new FXMLLoader();
                //Pane root = fxmlLoader.load(getClass().getResource("GameStage.fxml").openStream());
                Parent root = fxmlLoader.load(getClass().getResource("loginportal.fxml"));
                
                TetrisMain.testGame(tetrisStage);
                tetrisStage.show();
                /*
                if(!pc1NameInput.getText().isEmpty() && !pc2NameInput.getText().isEmpty()) {
                	TetrisGUI gui = new TetrisGUI();
                    Scene sceneMP = gui.generateMultiplayerScene();
                    tetrisStage.setScene(sceneMP);
                }
                else {
                	TetrisGUI gui = new TetrisGUI();
                	Scene sceneSP = gui.generateSingleplayerScene();
                    tetrisStage.setScene(sceneSP);
                }
                
            }catch (IOException e){
                e.printStackTrace();
            }
            */
        }
        if(event.getSource().equals(drMarioIcon)){
            System.out.println("Dr.Mario selected");
        }
    }

}

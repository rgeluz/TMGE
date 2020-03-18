package com.INF122.TMGE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    //For JavaFX test
    Stage window;
    Scene scene1, scene2;
    Button button;

    public static void main(String[] args){
        System.out.println("Hello World");
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        //testGame(stage);
/**/
        Parent root = FXMLLoader.load(getClass().getResource("loginportal.fxml"));
        stage.setTitle("TMGE");
        
        Scene scene = new Scene(root, 671, 400);
        stage.setScene(scene);
        stage.show();

        
    }

}

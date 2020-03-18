package com.INF122.HomeScreen;

import com.INF122.TMGE.Board;
import com.INF122.TMGE.Direction;
import com.INF122.Tetris.TetrisController;
//import com.INF122.Tetris.TetrisGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeScreenMain extends Application {

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

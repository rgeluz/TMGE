package com.INF122.TMGE;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        //Tutorial - 4: Switching Scene
        //tutorial4(stage);
        testGame(stage);
    }

    //Simple method to test JavaFx
    /*
        Tutorial 4 - Switching Scenes
     */
    private void tutorial4(Stage primaryStage){
        window = primaryStage;

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button 2
        Button button2= new Button("Go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("Title here");
        window.show();
    }

    public void testGame(Stage stage){


        int gridHeight = 20;
        int gridWidth = 12;
        int tileSize = 30;

        //Board Player1
        Board board1 = new Board(gridHeight,gridWidth, tileSize);
        int board1Width = gridWidth*tileSize;
        int board1Height = gridHeight*tileSize;

        Controller controller1 = new Controller(board1);
        Group group1 = controller1.create();

        //Board Player2
        Board board2 = new Board(gridHeight,gridWidth, tileSize);
        int board2Width = gridWidth*tileSize;
        int board2Height = gridHeight*tileSize;

        Controller controller2 = new Controller(board2);
        Group group2 = controller2.create();


        //Master window
        HBox hbox = new HBox(group1,group2);
        int masterWindowWidth = board1Width * 2; //need to double width
        int masterWindowHeight = board1Height;
        Color backgroundColor = Color.BLACK;



        //Scene scene = new Scene(group, gridWidth * tileSize, gridHeight * tileSize, backgroundColor);
        Scene scene = new Scene(hbox, masterWindowWidth, masterWindowHeight, backgroundColor);

        //Key event handler
        scene.setOnKeyPressed(e -> {

            //Player 1
            if (e.getCode() == KeyCode.W) { //ROTATE SHAPE CLOCKWISE
                controller1.rotateShape();
            } else if (e.getCode() == KeyCode.A) {
                controller1.moveShape(Direction.LEFT);
            } else if (e.getCode() == KeyCode.D) {
                controller1.moveShape(Direction.RIGHT);
            } else if (e.getCode() == KeyCode.S) {
                controller1.moveShape(Direction.DOWN);
            }
            controller1.render();

            //Player 2
            if (e.getCode() == KeyCode.UP) { //ROTATE SHAPE CLOCKWISE
                controller2.rotateShape();
            } else if (e.getCode() == KeyCode.LEFT) {
                controller2.moveShape(Direction.LEFT);
            } else if (e.getCode() == KeyCode.RIGHT) {
                controller2.moveShape(Direction.RIGHT);
            } else if (e.getCode() == KeyCode.DOWN) {
                controller2.moveShape(Direction.DOWN);
            }
            controller2.render();
        });

        stage.setScene(scene);
        stage.show();

    }
}

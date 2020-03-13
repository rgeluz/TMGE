package com.INF122.TMGE;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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


        int gridHeight = 24;
        int gridWidth = 11;
        int tileSize = 25;
        Board board = new Board(gridHeight,gridWidth, tileSize);
        Controller controller = new Controller(board);
        Group group = controller.create();
        Scene scene = new Scene(group,gridWidth*tileSize, gridHeight*tileSize);

        //Key event handler
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) { //ROTATE SHAPE CLOCKWISE
                controller.rotateShape();
            } else if (e.getCode() == KeyCode.LEFT) {
                controller.moveShape(Direction.LEFT);
            } else if (e.getCode() == KeyCode.RIGHT) {
                controller.moveShape(Direction.RIGHT);
            } else if (e.getCode() == KeyCode.DOWN) {
                controller.moveShape(Direction.DOWN);
            }

            controller.render(group);

        });

        stage.setScene(scene);
        stage.show();





    }
}

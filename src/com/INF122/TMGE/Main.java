package com.INF122.TMGE;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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


        //Center
        int centerWidth = 300;
        int fieldWidth = 100;
        GridPane gridPaneCenter = new GridPane();
        gridPaneCenter.setPrefWidth(centerWidth);
        gridPaneCenter.setAlignment(Pos.CENTER);


        //TODO important to note, the first coordinate is

        //Player 1
        Label player1Label = new Label("Player1");
        player1Label.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1Label, 0, 10);

        TextField player1NameField = new TextField();
        player1NameField.setDisable(true);
        player1NameField.setMaxWidth(fieldWidth);
        player1NameField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1NameField, 1,10);


        Label player1Score = new Label("Score: ");
        player1Score.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1Score, 0, 11);


        TextField player1ScoreField = new TextField();
        player1ScoreField.setDisable(true);
        player1ScoreField.setMaxWidth(fieldWidth);
        player1ScoreField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1ScoreField, 1,11);

        Label player1LineCount = new Label("Lines:");
        player1LineCount.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1LineCount, 0, 12);

        TextField player1LineCountField = new TextField();
        player1LineCountField.setDisable(true);
        player1LineCountField.setMaxWidth(fieldWidth);
        player1LineCountField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player1LineCountField, 1,12);





        //Player 2
        Label player2Label = new Label("Player2");
        player2Label.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2Label, 0,  13);

        TextField player2NameField = new TextField();
        player2NameField.setDisable(true);
        player2NameField.setMaxWidth(100);
        player2NameField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2NameField, 1,13);

        Label player2Score = new Label("Score: ");
        player2Score.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2Score, 0, 14);

        TextField player2ScoreField = new TextField();
        player2ScoreField.setDisable(true);
        player2ScoreField.setMaxWidth(100);
        player2ScoreField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2ScoreField, 1,14);

        Label player2LineCount = new Label("Lines: ");
        player2LineCount.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2LineCount, 0, 15);

        TextField player2LineCountField = new TextField();
        player2LineCountField.setDisable(true);
        player2LineCountField.setMaxWidth(100);
        player2LineCountField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2LineCountField, 1,15);


        //gridPaneCenter.setBackground(background);
        //Text player1Name = new Text("Player1");
        //player1Name.setFont();
        //gridPaneCenter.add(player1Name,0,0);

        //Text player2Name = new Text("Player2");
        //gridPaneCenter.add(player2Name,0,0);



        //Master window



        //Player1
        VBox vbox1 = new VBox(group1);
        BackgroundFill backgroundFill1 = new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY);
        Background background1 = new Background(backgroundFill1);
        vbox1.setBackground(background1);

        //Center
        VBox vboxCenter = new VBox(gridPaneCenter);
        /*vboxCenter.getChildren().addAll(player1Label, player1Score, player1LineCount,
                player2Label, player2Score, player2LineCount);*/
        BackgroundFill backgroundFillCenter = new BackgroundFill(Color.AQUA,CornerRadii.EMPTY, Insets.EMPTY);
        Background backgroundCenter = new Background(backgroundFillCenter);
        vboxCenter.setBackground(backgroundCenter);

        //Player2
        VBox vbox2 = new VBox(group2);
        BackgroundFill backgroundFill2 = new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY);
        Background background2 = new Background(backgroundFill2);
        vbox2.setBackground(background2);


        HBox hbox = new HBox(vbox1,vboxCenter,vbox2);
        int masterWindowWidth = (board1Width * 2)+centerWidth; //need to double width
        int masterWindowHeight = board1Height;
        Color backgroundColor = Color.BLUE;




        /*
            TEST AREA
         */
        /*Button left = new Button("left");
        Button center = new Button("center");
        Button top = new Button("top");

        //top
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(10, 10, 10, 10));
        flow.setStyle("-fx-background-color: DAE6F3;");
        flow.setHgap(5);
        flow.getChildren().addAll(left, center);

        //center
        StackPane root = new StackPane();
        Button btn1 = new Button(" 1 ");
        Button btn2 = new Button("22222222");
        root.getChildren().addAll(btn2, btn1);
        root.setStyle("-fx-background-color: #87CEFA;");

        //bottom
        TilePane tile = new TilePane();
        tile.setPadding(new Insets(10, 10, 10, 10));
        tile.setPrefColumns(2);
        tile.setStyle("-fx-background-color: #CD5C5C;");
        HBox hbox2 = new HBox(8); // spacing = 8
        hbox2.getChildren().addAll(top, left, center);
        tile.getChildren().add(hbox2);

        //Pane
        BorderPane pane = new BorderPane();
        pane.setLeft(vbox1);
        pane.setCenter(root);
        pane.setRight(vbox2);
        pane.setTop(flow);
        pane.setBottom(tile);
*/


        //Scene scene = new Scene(group, gridWidth * tileSize, gridHeight * tileSize, backgroundColor);
        Scene scene = new Scene(hbox, masterWindowWidth, masterWindowHeight, backgroundColor);
        //Scene scene = new Scene(pane,masterWindowWidth+100, masterWindowHeight+100,backgroundColor);




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

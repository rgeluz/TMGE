package com.INF122.DrMario;

import com.INF122.TMGE.Board;
import com.INF122.TMGE.Direction;
import com.INF122.TMGE.GameEnum;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DrMarioMain extends Application{

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
        testGame(stage);
    }

    public void testGame(Stage stage)
    {

        /*
            Toggle the value below to switch between games
            eg. GameEnum.DRMARIO <----> Game.TETRIS
        */
        final GameEnum GAME_TO_TEST = GameEnum.DRMARIO;

        int gridHeight = 20;
        int gridWidth = 12;
        int tileSize = 30;

        //Center
        int centerWidth = 300;
        int fieldWidth = 100;
        GridPane gridPaneCenter = new GridPane();
        gridPaneCenter.setPrefWidth(centerWidth);
        gridPaneCenter.setAlignment(Pos.CENTER);


        //TODO important to note, the first coordinate is the column index,
        //     and the second is the row index of the grid pane

        //TODO very hacky, but don't have to time to be picky!
        //TODO this adds spaces above player 1
        Label emptyLabel1 = new Label("");
        gridPaneCenter.add(emptyLabel1, 0, 1);
        Label emptyLabel2 = new Label("");
        gridPaneCenter.add(emptyLabel2, 0, 2);
        Label emptyLabel3 = new Label("");
        gridPaneCenter.add(emptyLabel3, 0, 3);
        Label emptyLabel4 = new Label("");
        gridPaneCenter.add(emptyLabel4, 0, 4);
        Label emptyLabel5 = new Label("");

        //Game Logo
        //Image imageTetrisLogo = null;
        Image imageDrMarioLogo = null;
        try{
            imageDrMarioLogo = new Image(new FileInputStream("DrMario-module/resources/DrMarioLogo.png"));
            //imageTetrisLogo = new Image(new FileInputStream("TMGE-module/resources/TetrisLogo.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView imageViewDrMario = new ImageView(imageDrMarioLogo);
        //ImageView imageViewTetris = new ImageView(imageTetrisLogo);
        imageViewDrMario.setFitWidth(tileSize*5);
        imageViewDrMario.setFitHeight(tileSize*2);
        //imageViewTetris.setFitWidth(tileSize*5);
        //imageViewTetris.setFitHeight(tileSize*2);

        Color gameColor = null;
        /*if(GAME_TO_TEST==GameEnum.DRMARIO){
            gridPaneCenter.getChildren().add(imageViewDrMario);
            gameColor = Color.AQUAMARINE;
        } else if (GAME_TO_TEST==GameEnum.TETRIS){
            gridPaneCenter.getChildren().add(imageViewTetris);
            gameColor = Color.AQUA;
        }*/


        gridPaneCenter.getChildren().add(imageViewDrMario);
        gameColor = Color.AQUAMARINE;


        gridPaneCenter.add(emptyLabel5, 0, 5);
        Label emptyLabel6 = new Label("");
        gridPaneCenter.add(emptyLabel6, 0, 6);

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


        //TODO this adds four space between player 1 and player 2
        Label emptyLabel7 = new Label("");
        gridPaneCenter.add(emptyLabel7, 0, 13);
        Label emptyLabel8 = new Label("");
        gridPaneCenter.add(emptyLabel8, 0, 14);
        Label emptyLabel9 = new Label("");
        gridPaneCenter.add(emptyLabel9, 0, 15);
        Label emptyLabel10 = new Label("");
        gridPaneCenter.add(emptyLabel10, 0, 16);


        //Player 2
        Label player2Label = new Label("Player2");
        player2Label.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2Label, 0,  17);

        TextField player2NameField = new TextField();
        player2NameField.setDisable(true);
        player2NameField.setMaxWidth(100);
        player2NameField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2NameField, 1,17);

        Label player2Score = new Label("Score: ");
        player2Score.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2Score, 0, 18);

        TextField player2ScoreField = new TextField();
        player2ScoreField.setDisable(true);
        player2ScoreField.setMaxWidth(100);
        player2ScoreField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2ScoreField, 1,18);

        Label player2LineCount = new Label("Lines: ");
        player2LineCount.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2LineCount, 0, 19);

        TextField player2LineCountField = new TextField();
        player2LineCountField.setDisable(true);
        player2LineCountField.setMaxWidth(100);
        player2LineCountField.setPadding(new Insets(10, 10, 10, 10));
        gridPaneCenter.add(player2LineCountField, 1,19);


        //Board Player1
        //Board board1 = new Board(gridHeight,gridWidth, tileSize);
        //int board1Width = gridWidth*tileSize;
        //int board1Height = gridHeight*tileSize;

        //TextField player1NameField = new TextField();
        //TextField player1ScoreField = new TextField();
        //TextField player1LineCountField = new TextField();

        //final GameEnum GAME_TO_TEST = GameEnum.DRMARIO;

//        Controller controller1 = new Controller(board1);
        //DRMController controller1 = new DRMController(GAME_TO_TEST,
        //       board1,
        //     player1NameField,
        //      player1ScoreField,
        //        player1LineCountField);
        //Group group1 = controller1.create();

//        //Board Player2
//        Board board2 = new Board(gridHeight,gridWidth, tileSize);
//        int board2Width = gridWidth*tileSize;
//        int board2Height = gridHeight*tileSize;
//
////        Controller controller2 = new Controller(board2);
//        DRMController controller2 = new DRMController(board2);
//        Group group2 = controller2.create();


        //Master window
        //HBox hbox = new HBox(group1);
//      //HBox hbox = new HBox(group1,group2);
        //int masterWindowWidth = board1Width * 2; //need to double width
        //int masterWindowHeight = board1Height;
        //Color backgroundColor = Color.BLACK;

        //________________Master window______________________//

        //Board Player1
        Board board1 = new Board(gridHeight,gridWidth, tileSize);
        int board1Width = gridWidth*tileSize;
        int board1Height = gridHeight*tileSize;

        DRMController controller1 = new DRMController(board1,
                player1NameField,
                player1ScoreField,
                player1LineCountField);
        Group group1 = controller1.create();


        //Board Player2
        Board board2 = new Board(gridHeight,gridWidth, tileSize);
        int board2Width = gridWidth*tileSize;
        int board2Height = gridHeight*tileSize;

        DRMController controller2 = new DRMController(
                board2,
                player2NameField,
                player2ScoreField,
                player2LineCountField);
        Group group2 = controller2.create();


        //Player1
        VBox vbox1 = new VBox(group1);
        BackgroundFill backgroundFill1 = new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY);
        Background background1 = new Background(backgroundFill1);
        vbox1.setBackground(background1);

        //Center
        VBox vboxCenter = new VBox(gridPaneCenter);
        /*vboxCenter.getChildren().addAll(player1Label, player1Score, player1LineCount,
                player2Label, player2Score, player2LineCount);*/
        BackgroundFill backgroundFillCenter = new BackgroundFill(gameColor,CornerRadii.EMPTY, Insets.EMPTY);
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
            if (e.getCode() == KeyCode.UP)  //ROTATE SHAPE CLOCKWISE
                controller2.rotateShape();
            else if (e.getCode() == KeyCode.LEFT)
                controller2.moveShape(Direction.LEFT);
            else if (e.getCode() == KeyCode.RIGHT)
                controller2.moveShape(Direction.RIGHT);
            else if (e.getCode() == KeyCode.DOWN)
                controller2.moveShape(Direction.DOWN);
            controller2.render();
        });

        stage.setScene(scene);
        stage.show();

    }
}

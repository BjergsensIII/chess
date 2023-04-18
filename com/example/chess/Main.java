package com.example.chess;

   import javafx.application.Application;
   import javafx.scene.Scene;
   import javafx.scene.layout.GridPane;
   import javafx.stage.Stage;

   public class Main extends Application {

       @Override
       public void start(Stage primaryStage) throws Exception {
           GridPane gridPane = new GridPane();
           new ChessBoard(gridPane);
           Scene scene = new Scene(gridPane, 400, 400);
           primaryStage.setScene(scene);
           primaryStage.show();
       }

       public static void main(String[] args) {
           launch(args);
       }
   }
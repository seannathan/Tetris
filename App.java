package Tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * It's time for Tetris! This is the  main class to get things started.
  *
  * The main method of this application calls the start method. You
  * will need to fill in the start method to instantiate your game.
  *
  * Class comments here...
  *
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
    	PaneOrganizer Organizer = new PaneOrganizer();//instantiates new pane organizer
    	Scene scene = new Scene(Organizer.getRoot());//instantiates new scene with pane organizer as parameter
    	stage.setScene(scene);//sets scene
    	stage.setWidth(595);
    	stage.setHeight(840);
    	stage.show();
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}

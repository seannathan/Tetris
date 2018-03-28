package Tetris;


import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class PaneOrganizer {
	
	private BorderPane _BorderPane;
	private Pane _topSquarePane;
	private Pane _bottomPane;
	private Pane _leftSquarePane;
	private Pane _rightSquarePane;
	private Pane _gamePane;
	private Button _b1;
	private Label _scoreLabel;
	private Label _lineLabel;
	private Label _tetrisLabel;
	private Label _storePieceLabel;
	private Label _pauseNotification;
	private Label _nextPiece;
	private Label _instructions;
	
	/*
	 * GUI constructor
	 */
	public PaneOrganizer(){
		
		_BorderPane = new BorderPane();
		_BorderPane.setStyle("-fx-background-color: #FFFFFF");
		_BorderPane.setPrefSize(595, 825);
		this.createTopPane();
		this.bottomPane();
		this.createLeftPane();
		this.createRightPane();
		this.createGamePane();
		
	}
	
	public Pane getRoot(){

		return _BorderPane;
	}
	/*
	 * Top Black Pane
	 */
	public void createTopPane(){
		_topSquarePane = new Pane();
		_BorderPane.setTop(_topSquarePane);
		_topSquarePane.setStyle("-fx-background-color: #000000");
		_topSquarePane.setPrefSize(425, 60);
		
		
	}
	/*
	 * Game canvas 
	 */
	public void createGamePane(){
		
		_gamePane = new Pane();
		_BorderPane.setCenter(_gamePane);
		_gamePane.setStyle("-fx-background-color: #000000");
		_gamePane.setPrefSize(Constants.GAME_PANE_WIDTH, Constants.GAME_PANE_HEIGHT);
		Tetris tetris = new Tetris(_gamePane, _storePieceLabel, _scoreLabel, _lineLabel, _tetrisLabel, _pauseNotification, _nextPiece);
		
	}
	/*
	 * Left Pane
	 */
	public void createLeftPane(){
		_leftSquarePane = new Pane();
		_BorderPane.setLeft(_leftSquarePane);
		_leftSquarePane.setStyle("-fx-background-color: #000000");
		_leftSquarePane.setPrefSize(60, 660);
		
	}
	/*
	 * Right Pane that has all the labels for score, instructions, etc.
	 */
	public void createRightPane(){
		_rightSquarePane = new Pane();
		_BorderPane.setRight(_rightSquarePane);
		_rightSquarePane.setStyle("-fx-background-color: #000000");
		_rightSquarePane.setPrefSize(2Constants.SQUARE_SIDE, 660);
		_instructions = new Label();
		_instructions.setTranslateX(40);
		_instructions.setTranslateY(200);
		_instructions.setTextFill(Color.web("#ffffff"));
		_instructions.setFont(new Font("Arial", 20));
		_instructions.setText("Rotate: Up \nMove: Left/Right \nHard Drop: Space \nStore Piece: S \nPause Game: P");
		_scoreLabel = new Label();
		_scoreLabel.setTranslateX(40);
		_scoreLabel.setTranslateY(650);
		_scoreLabel.setTextFill(Color.web("#ffffff"));
		_scoreLabel.setFont(new Font("Arial", 20));
		_lineLabel = new Label();
		_lineLabel.setTranslateX(40);
		_lineLabel.setTranslateY(550);
		_lineLabel.setTextFill(Color.web("#ffffff"));
		_lineLabel.setFont(new Font("Arial", 20));
		_tetrisLabel = new Label();
		_tetrisLabel.setTranslateX(40);
		_tetrisLabel.setTranslateY(600);
		_tetrisLabel.setTextFill(Color.web("#ffffff"));
		_tetrisLabel.setFont(new Font("Arial", 20));
		_storePieceLabel = new Label();
		_storePieceLabel.setTranslateX(40);
		_storePieceLabel.setTranslateY(500);
		_storePieceLabel.setTextFill(Color.web("#ffffff"));
		_storePieceLabel.setFont(new Font("Arial", 20));
		_nextPiece = new Label();
		_nextPiece.setTranslateX(40);
		_nextPiece.setTranslateY(450);
		_nextPiece.setTextFill(Color.web("#ffffff"));
		_nextPiece.setFont(new Font("Arial", 20));
		_pauseNotification = new Label();
		_pauseNotification.setTranslateX(40);
		_pauseNotification.setTranslateY(400);
		_pauseNotification.setTextFill(Color.web("#ffffff"));
		_pauseNotification.setFont(new Font("Arial", 20));
		_rightSquarePane.getChildren().addAll(_scoreLabel, _lineLabel, _tetrisLabel, _storePieceLabel, _pauseNotification, _nextPiece, _instructions);
		
	}
	
	/*
	 * Bottom pane with quit button
	 */
	public void bottomPane(){
		_bottomPane = new Pane();
		_BorderPane.setBottom(_bottomPane);
		_bottomPane.setStyle("-fx-background-color: #000000");
		_bottomPane.setPrefSize(420, 120);
		_b1 = new Button("Quit");
		_b1.setTranslateX(210);
		_b1.setTranslateY(55);
		_b1.setStyle("-fx-focus-color: transparent;");
		_b1.setFocusTraversable(false);
		_b1.setOnAction(e -> Platform.exit()); //quit functionality
		_bottomPane.getChildren().addAll(_b1);
		
	}
}

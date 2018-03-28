package Tetris;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Tetris {
	
	private Pane _pane;
	private static TetrisSquare[][] _board;
	private static TetrisSquare[][] _board2;
	private static TetrisSquare[][] _logicBoard;
	private Piece _currentPiece = null;
	private Timeline _timeline;
	private int _howManyLines = 0;
	private boolean _clearRow;
	private Label _label;
	private Label _imageLabel;
	private Label _scoreLabel;
	private Label _linesClearedLabel;
	private Label _tetrisLabel;
	private int _score = 0;
	private int _tetrisNum = 0;
	private int _consecutiveLines;
	public Image _smiley;
	private Piece _storePiece = null;
	private Piece _placeHolder = null;
	private Label _storePieceLabel;
	private Label _pauseNotification;
	private Piece _nextPiece;
	private Label _nextPieceLabel;
	private double _duration = 0.5;
	
	public Tetris(Pane pane, Label label4, Label label, Label label2, Label label3, Label label5, Label label6) {
		_pane = pane; //game pane
		_scoreLabel = label; // labels for changing parameters
		_linesClearedLabel = label2;
		_tetrisLabel = label3;
		_storePieceLabel = label4;
		_pauseNotification = label5;
		_nextPieceLabel = label6;
		_pane.requestFocus();//focus for pane
		_pane.setFocusTraversable(true);
		_pane.setOnKeyPressed(new MoveHandler());//new movehandler
		this.buildBoard();//setup Board
		_logicBoard = new TetrisSquare[22][10];//setup logicboard
		_label = new Label();//gameover label
		_label.setTranslateX(75);
		_label.setTranslateY(Constants.SQUARE_SIDE0);
		_label.setFont(new Font("Arial", Constants.SQUARE_SIDE));
		_label.setTextFill(Color.web("#ffffff"));
		_pane.getChildren().addAll(_label);
		_nextPiece = new Piece(_pane, _logicBoard);//next piece instantiation
		_nextPiece.setPieceType();
		this.setupTimeline(); //call timeline
		
	}
	/*
	 * builds the initial board for my game
	 */
	public TetrisSquare[][] buildBoard() {
		_board = new TetrisSquare[22][10]; // create board array
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board[i].length; j++) { // for each index (i, j)
				_board[i][j] = new TetrisSquare();
				_board[i][j].setFill(Color.GRAY);
				_board[i][j].setX(j*Constants.SQUARE_SIDE);
				_board[i][j].setY(i*Constants.SQUARE_SIDE);
				_pane.getChildren().add(_board[i][j].getNode());
			}
		}
		return _board;
	}
	
	/*
	 * creates a new full black board to blackout the screen
	 */
	public TetrisSquare[][]blackoutBoard1(){
		_board2 = new TetrisSquare[22][10]; // create gameOver Array
		for (int i = 0; i < _board2.length; i++) {
			for (int j = 0; j < _board2[i].length; j++) { // for each index (i, j)
				_board2[i][j] = new TetrisSquare();
				_board2[i][j].setFill(Color.BLACK);
				_board2[i][j].setX(j*Constants.SQUARE_SIDE);
				_board2[i][j].setY(i*Constants.SQUARE_SIDE);
				_pane.getChildren().add(_board2[i][j].getNode());
			}
		}
		return _board2;
		
	}
	/*
	 * Self explanatory
	 */
	public void setupTimeline(){
		
		KeyFrame tetris = new KeyFrame(Duration.seconds(_duration), new TimeHandler());//keyframe instantiation that sets a specific duration and my timehandler
		_timeline = new Timeline(tetris);//new timeline that takes in my keyframe as parameter
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}
	


	/*
	 * TimeHandler which handles all of the activity that needs constant monitoring
	 */
	private class TimeHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent event){
			_consecutiveLines = 0;
			while(_currentPiece==null){ //while currentPiece null, get current from next and get a new nextPiece
				_currentPiece = _nextPiece;
				_currentPiece.show();
				_nextPiece = new Piece(_pane, _logicBoard);
				_nextPiece.setPieceType();
			}
			
			if(_currentPiece.dropPiece()==false){//if currentPiece can't fall anymore, set it 
				_currentPiece.setPiece();
				_currentPiece = null;
			}
			
			if(_nextPiece!=null){//show the next available piece
				Tetris.this.showNextPiece();
			}
			
			for(int i =0; i<_logicBoard.length; i++){//check for full lines
				_clearRow = true;
				for(int j=0; j<_logicBoard[i].length; j++){
					if(_logicBoard[i][j]==null){
						_clearRow = false;
						
					}
				}
				if(_clearRow==true){//if line is full, add to count and call clearLine method
					_howManyLines+=1;
					_consecutiveLines+=1;
					Tetris.this.clearLine(i);
	
				}
			}
			if(_howManyLines%12==0){//speed up game every time 12 lines have been cleared
				_duration = _duration*0.85;
			}
			
			switch(_consecutiveLines){//cases for multiple line clears up to a Tetris
				case 0:
					break;
				case 1:
					_score = _score + 100;
					break;
				case 2:
					_score = _score + Constants.SQUARE_SIDE0;
					break;
				case 3:
					_score = _score + 600;
					break;
				case 4:
					_score = _score + 1500;
					_tetrisNum+=1; //count number of Tetris's
			}
			
			_linesClearedLabel.setText("Lines: " + _howManyLines);//updating labels for score
			_scoreLabel.setText("Score: " + _score);
			_tetrisLabel.setText("# Tetris: " +_tetrisNum);
			
			for(int i =0; i<_logicBoard[0].length; i++){//check for end game
				if(_logicBoard[0][i]!=null){//if top line occupied, gg no re
					_timeline.stop();
					if(_currentPiece != null){//remove currentPiece cluttering screen
						_currentPiece.removePiece();
					}
					Tetris.this.blackoutBoard1();//blackout Board
					_label.toFront();
					_label.setText("Game Over");//gameOver Label
				}
			}
	
		}

	}
	/*
	 * Movehandler for the specific moves required for my game
	 */
	private class MoveHandler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event){
			switch(event.getCode()){
				case LEFT:
					if(_currentPiece!=null){
						_currentPiece.moveLeft();
					}
					break;
				case RIGHT:
					if(_currentPiece!=null){
						_currentPiece.moveRight();
					}
					break;
				case UP:
					if(_currentPiece!=null){
						_currentPiece.rotate();
					}
					break;
				case DOWN:
					if(_currentPiece!=null){
						_currentPiece.moveDown();
					}
					break;
				case SPACE:
					if(_currentPiece!=null){
						_currentPiece.fullDrop();
					}
					break;
				case P:
					if(_timeline.getStatus()==Animation.Status.PAUSED){//if paused, play
						_timeline.play();
						_pauseNotification.setText("");
						break;
					}
					else { //else, pause
						_timeline.pause();
						_pauseNotification.setText("PAUSED");
					}
					break;
				case S:
					Tetris.this.storePiece();
			default:
				break;
			}
			event.consume();
		}
	}
	
	/*
	 * Method for clearing a line as well as moving all rows above down
	 */
	public void clearLine(int row){
		for(int j=0; j<_logicBoard[row].length; j++){//remove all nodes graphically and logically
			_pane.getChildren().removeAll(_logicBoard[row][j].getNode());
			_logicBoard[row][j]=null;
		}
		for(int i=row-1; i>=0; i--){
			for(int j=0; j<_logicBoard[i].length; j++){
				if(_logicBoard[i][j] != null) {//set new position graphically
					_logicBoard[i][j].setY(_logicBoard[i][j].getY()+Constants.SQUARE_SIDE);
				}
				_logicBoard[i+1][j] = _logicBoard[i][j];//set new position logically
			}
		}
	}
	
	/*
	 * Method for removing all squares end game. Ended up not needing this method
	 */
	public void removeAllSquares(){
		for (int j=0; j<_logicBoard.length; j++){
			for(int i=0; i<_logicBoard[j].length; i++){
				if(_logicBoard[j][i]!=null){
					_pane.getChildren().remove(_logicBoard[j][i].getSquare());
				}
			}
		}
	}
	
	/*
	 * blackout board method to create an all black pane
	 */
	public void blackoutBoard(){
		for (int j=0; j<_board.length; j++){
			for(int i=0; i<_board[j].length; i++){
				_board[j][i].setFill(Color.BLACK);
			}
		}
	}
	/*
	 * Store Piece method to store and then reuse at any time 
	 */
	public void storePiece(){
		if(_storePiece==null && _currentPiece!=null){//if no piece already being stored
			_currentPiece.removePiece();//remove current piece from screen
			_storePiece = _currentPiece;//store the piece
			_currentPiece=null;//change _currentPiece to null
			switch(_storePiece.getPieceType()){ //switch to tell which piece is stored
				case 0:
					_storePieceLabel.setText("STORED: O");
					break;
				case 1:
					_storePieceLabel.setText("STORED: S");
					break;
				case 2:
					_storePieceLabel.setText("STORED: J");
					break;
				case 3:
					_storePieceLabel.setText("STORED: I");
					break;
				case 4:
					_storePieceLabel.setText("STORED: L");
					break;
				case 5:
					_storePieceLabel.setText("STORED: T");
					break;
				case 6:
					_storePieceLabel.setText("STORED: Z");
					break;
			}
		}
		else if(_currentPiece!=null){//if piece already being stored
			_placeHolder = _currentPiece;//take current and place hold it
			_currentPiece.removePiece();//remove current from screen
			_currentPiece = _storePiece;//get store piece and assign to current
			_currentPiece.show();//show store piece
			_storePiece = _placeHolder;//take placeholder and put it back as stored
			switch(_storePiece.getPieceType()){ //switch to tell which piece is stored
				case 0:
					_storePieceLabel.setText("STORED: O");
					break;
				case 1:
					_storePieceLabel.setText("STORED: S");
					break;
				case 2:
					_storePieceLabel.setText("STORED: J");
					break;
				case 3:
					_storePieceLabel.setText("STORED: I");
					break;
				case 4:
					_storePieceLabel.setText("STORED: L");
					break;
				case 5:
					_storePieceLabel.setText("STORED: T");
					break;
				case 6:
					_storePieceLabel.setText("STORED: Z");
					break;
			}
			_placeHolder = null;//placeholder back to null
			
		}
	}
	/*
	 * switch to set the text label to show what the next piece is
	 */
	public void showNextPiece(){
		switch(_nextPiece.getPieceType()){
			case 0:
				_nextPieceLabel.setText("NEXT PIECE: O");
				break;
			case 1:
				_nextPieceLabel.setText("NEXT PIECE: S");
				break;
			case 2:
				_nextPieceLabel.setText("NEXT PIECE: J");
				break;
			case 3:
				_nextPieceLabel.setText("NEXT PIECE: I");
				break;
			case 4:
				_nextPieceLabel.setText("NEXT PIECE: L");
				break;
			case 5:
				_nextPieceLabel.setText("NEXT PIECE: T");
				break;
			case 6:
				_nextPieceLabel.setText("NEXT PIECE: Z");
				break;
		}	
	}
}

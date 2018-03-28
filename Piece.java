package Tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * Piece class that controls piece generation and piece movement and interaction
 */
public class Piece {

	private TetrisSquare _square;
	private TetrisSquare[] _orientation;
	private TetrisSquare[][] _logicBoard;
	private Pane _board;
	private int _pieceType;

	/*
	 * constructor for piece that takes in board and logic board as parameters
	 */
	public Piece(Pane Board, TetrisSquare[][] logicBoard) {

		_square = new TetrisSquare();
		_orientation = new TetrisSquare[4];
		_logicBoard = logicBoard;
		_board = Board;


	}
	/*
	 * show piece on board using array of TetrisSquares
	 */
	public void show() {
		for (int i = 0; i < _orientation.length; i++) {
			_board.getChildren().add(_orientation[i].getSquare());
		}
	}
	/*
	 * convert pixels to indices
	 */
	public int converttoInd(double location, int distance) {
		double newPosition = location + distance;
		int ind = (int) newPosition / Constants.SQUARE_SIDE;
		return ind;
	}
	/*
	 * switch statement that sets piece type(gave I piece 1 extra chance for game balance
	 */
	public void setPieceType() {
		int rand = (int) (Math.random() * 8);
		switch (rand) {
		case 0:
			int[][] orientation1 = Constants.O_PIECE; //create a 2d array out of constant for O-Piece
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();//create a tetris square for each of the 4
				pieceSquare.setFill(Color.YELLOW);//set color
				for (int j = 0; j < 2; j++) {//based off j value, set x and y for initial position generation on the board
					if (j == 0) {
						pieceSquare.setX((orientation1[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation1[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;//specific square generation
			}
			_pieceType = 0;//piece type number 
			break;

		case 1:
			int[][] orientation2 = Constants.S_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.LIMEGREEN);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation2[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation2[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 1;
			break;

		case 2:
			int[][] orientation3 = Constants.J_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.BLUE);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation3[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation3[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 2;
			break;

		case 3: case 7:
			int[][] orientation4 = Constants.I_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.CYAN);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation4[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation4[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 3;
			break;

		case 4:
			int[][] orientation5 = Constants.L_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.ORANGE);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation5[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation5[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 4;
			break;

		case 5:
			int[][] orientation6 = Constants.T_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.PURPLE);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation6[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation6[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 5;
			break;
		case 6:
			int[][] orientation7 = Constants.Z_PIECE;
			for (int i = 0; i < 4; i++) {
				TetrisSquare pieceSquare = new TetrisSquare();
				pieceSquare.setFill(Color.RED);
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						pieceSquare.setX((orientation7[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 4);
					} else if (j == 1) {
						pieceSquare.setY((orientation7[i][j] * (int) Constants.SQUARE_SIDE)+ (int) Constants.SQUARE_SIDE * 0);
					}
				}
				_orientation[i] = pieceSquare;
			}
			_pieceType = 6;
			break;
		}
	}
	/*
	 * return number of piece type for store methods and nextpiece methods
	 */
	public int getPieceType(){
		return _pieceType;
	}
	/*
	 * rotate method that uses the rotation algorithm from the hand out
	 */
	public void rotate() {
		int centerX = (int) _orientation[0].getSquare().getX();//get x and y original of center square
		int centerY = (int) _orientation[0].getSquare().getY();
		int indI = (int) centerX / Constants.SQUARE_SIDE;//change x position to index
		if (indI > 0 && indI < 9) {//if in between board
			if (_orientation[0].getColor() != Color.YELLOW && this.rotatePossible()) {//if rotation is possible
				for (int i = 1; i < _orientation.length; i++) {
					int oldPosX = (int) _orientation[i].getSquare().getX();//rotation algorithm
					int oldPosY = (int) _orientation[i].getSquare().getY();
					int newPosX = centerX - centerY + oldPosY;
					int newPosY = centerX + centerY - oldPosX;
					_orientation[i].getSquare().setX(newPosX);//set new x and y
					_orientation[i].getSquare().setY(newPosY);
				}
			} else {
				return;
			}
		}
	}
	/*
	 * check if rotation is possible by rotating and seeing if the moves are valid
	 */
	public boolean rotatePossible() {
		int count = 0;
		int centerX = (int) _orientation[0].getSquare().getX();// get center x and y
		int centerY = (int) _orientation[0].getSquare().getY();
		int indI = 0;// create index values
		int indJ = 0;
		for (int i = 1; i < _orientation.length; i++) {//for specific square
			int oldPosX = (int) _orientation[i].getSquare().getX();//rotation algorithm
			int oldPosY = (int) _orientation[i].getSquare().getY();
			int newPosX = centerX - centerY + oldPosY;
			int newPosY = centerX + centerY - oldPosX;
			indI = newPosX / Constants.SQUARE_SIDE;//convert new positions to indices
			indJ = newPosY / Constants.SQUARE_SIDE;
			if (_logicBoard[indJ][indI] == null) {//if the board is open at these indices, count+=1
				count += 1;
			}
		}
		
		if (count == 3) {//if all 3 squares can validly move, rotation is possible. return true;
			return true;
		}
		
		return false;//else false
	}

	/*
	 * move left method that gets x and then sets a new x based off square side distance
	 */
	public void moveLeft() {
		if (this.canMoveLeft()) {
			for (int i = 0; i < _orientation.length; i++) {
				_orientation[i].getSquare().setX(
						(int) _orientation[i].getX() - Constants.SQUARE_SIDE);
			}

		}
	}
	/*
	 * check's the logic board index to the left one square to see whether the move is legit
	 */
	public boolean canMoveLeft() {
		int count = 0;
		for (int i = 0; i < _orientation.length; i++) {
			int indI = this.converttoInd(_orientation[i].getX(),-Constants.SQUARE_SIDE);
			int indJ = this.converttoInd(_orientation[i].getY(), 0);
			if (indI >= 0) {
				if (_logicBoard[indJ][indI] == null) {
					count++;
				}
			}
		}
		
		for (int i = 0; i < _orientation.length; i++) {//if off board, not valid move
			if (_orientation[i].getX() < 1) {
				return false;
			}
		}
		
		if (count == 4) {//if all 4 squares can move, return true
			return true;
		}
		
		return false;// else false
	}

	/*
	 * move right method similar logic to move left
	 */
	public void moveRight() {
		if (this.canMoveRight()) {
			for (int i = 0; i < _orientation.length; i++) {
				_orientation[i].getSquare().setX(
						(int) _orientation[i].getX() + Constants.SQUARE_SIDE);
			}

		} else {
			return;
		}
	}
	/*
	 * move right detection similar to move left detection method
	 */
	public boolean canMoveRight() {
		int count = 0;
		for (int i = 0; i < _orientation.length; i++) {
			int indI = this.converttoInd(_orientation[i].getX(),
					Constants.SQUARE_SIDE);
			int indJ = this.converttoInd(_orientation[i].getY(), 0);
			if (indI < 10) {
				if (_logicBoard[indJ][indI] == null
						&& _orientation[i].getX() + Constants.SQUARE_SIDE < 3Constants.SQUARE_SIDE) {
					count += 1;
				}
			}
		}
		if (count == 4) {
			return true;
		}
		return false;
	}
	/*
	 * moves down each square by the pixels of one square
	 */
	public void moveDown() {
		if (this.canMoveDown()) {
			for (int i = 0; i < _orientation.length; i++) {
				_orientation[i].setY((_orientation[i].getY() + (int) Constants.SQUARE_SIDE));
			}
		}
	}
	/*
	 * checks to see whether the piece can move down by also checking all individual squares
	 */
	public boolean canMoveDown() {
		int count = 0;
		for (int i = 0; i < _orientation.length; i++) {
			int indJ = this.converttoInd(_orientation[i].getY(),
					Constants.SQUARE_SIDE);
			int indI = this.converttoInd(_orientation[i].getX(), 0);
			if (indJ < 22) {
				if (_logicBoard[indJ][indI] == null) {
					count += 1;
				}
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}
	/*
	 * constantly checks whether piece can move down, once fully moved down, the piece is set to the logic board
	 */
	public boolean dropPiece() {
		if (this.canMoveDown()) {
			for (int i = 0; i < _orientation.length; i++) {
				_orientation[i].setY(_orientation[i].getY()
						+ Constants.SQUARE_SIDE);
			}
			return true;
		} else {

			for (int i = 0; i < _orientation.length; i++) {
				int indI = this.converttoInd(_orientation[i].getX(), 0);//get x and y indices
				int indJ = this.converttoInd(_orientation[i].getY(), 0);
				_logicBoard[indJ][indI] = _orientation[i];//set piece at these indices
			}
			return false;
		}
	}
	/*
	 * set piece method similar to that above. not sure why I have two of the same method, but this one gets called for sure
	 */
	public void setPiece() {
		for (int i = 0; i < _orientation.length; i++) {
			int indI = this.converttoInd(_orientation[i].getX(), 0);
			int indJ = this.converttoInd(_orientation[i].getY(), 0);
			if (_logicBoard[indJ][indI] == null) {
				_logicBoard[indJ][indI] = _orientation[i];
			}
		}
	}
	/*
	 * while the piece can move down, drop it until it can't drop any more
	 */
	public void fullDrop() {
		while (this.canMoveDown()) {
			for (int i = 0; i < _orientation.length; i++) {
				_orientation[i].setY(_orientation[i].getY()
						+ Constants.SQUARE_SIDE);
			}
		}
	}

	/*
	 * remove piece from screen square by square
	 */
	public void removePiece(){
		for(int i = 0; i<_orientation.length; i++){
			_board.getChildren().remove(_orientation[i].getSquare());
		}
	}
	

}

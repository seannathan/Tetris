package Tetris;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
/*
 * class for individual squares
 */
public class TetrisSquare {
	
	public Rectangle _tetrisSquare;
	
	/*
	 * constructor for square
	 */
	public TetrisSquare(){
		
		_tetrisSquare = new Rectangle();
		_tetrisSquare.setWidth(Constants.SQUARE_SIDE);
		_tetrisSquare.setHeight(Constants.SQUARE_SIDE);
		_tetrisSquare.setStroke(Color.BLACK);
		
	}
	/*
	 * return square
	 */
	public Node getNode(){
		
		return _tetrisSquare;
	}
	/*
	 * set color
	 */
	public void setFill(Color color){
		_tetrisSquare.setFill(color);
	}
	/*
	 * set Y
	 */
	public void setY(double y){
		
		_tetrisSquare.setY(y);
		
	}
	/*
	 * set X
	 */
	public void setX(double x){
		_tetrisSquare.setX(x);
	}
	
	public double getY(){
		
		return _tetrisSquare.getY();
	}
	/*
	 * get X
	 */
	public double getX(){
		return _tetrisSquare.getX();
	}
	/*
	 * getSquare
	 */
	public Rectangle getSquare(){
		return _tetrisSquare;
	}
	/*
	 * getColor
	 */
	public Paint getColor(){
		return _tetrisSquare.getFill();
	}



}




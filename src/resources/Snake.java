package resources;

import java.awt.Graphics; 
import java.util.LinkedList;


public class Snake {
	
	private LinkedList<SnakePiece> snake = new LinkedList<SnakePiece>();
	
	public Snake(){
		int y = 100;
		for (int i=100;i<200;i++){
			SnakePiece sp = new SnakePiece(i,y);
			this.snake.addLast(sp);
		}
	}
	
	
	public void draw(Graphics g){

		int x1 = (int) snake.getFirst().x;
		int x2 = (int) snake.getLast().x;
		int y = (int) snake.getFirst().y;
		
		for(int x=x1;x<x2;x++){
			drawPoint(x,y,g);
		}
		
	}
	
	public void drawPoint(int x, int y, Graphics g){
		g.drawLine(x, y, x, y);
	}
	
	
	public void moveByOrientation(int index, Directions dir){
		switch (dir){
		case UP:
			snake.get(index).moveFowardBy(10);
			break;
		case DOWN:
			snake.get(index).moveDownBy(10);
			break;
		case LEFT:
			snake.get(index).moveLeftBy(10);
			break;
		case RIGHT:
			snake.get(index).moveRightBy(10);
			break;
		}
	}
	

	public LinkedList<SnakePiece> getSnake() {
		return snake;
	}
	

	
}

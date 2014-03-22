package resources;

import java.awt.Graphics; 
import java.util.LinkedList;

import utils.Directions;


public class Snake {
	
	private LinkedList<SnakePiece> snake = new LinkedList<SnakePiece>();
	
	public Snake(){
		int y = 30;
		int x = 30;
		for (int i=0;i<20;i++){
			SnakePiece sp = new SnakePiece(x,y,Directions.RIGHT);
			snake.addFirst(sp);
		}
	}
	
	
	public void draw(Graphics g){
		for(int i=0;i<snake.size();i++){
			snake.get(i).draw(g);
		}
	}
	
	public LinkedList<SnakePiece> getSnake(){
		return this.snake;
	}
	
	public int getSize(){
		return snake.size();
	}
}

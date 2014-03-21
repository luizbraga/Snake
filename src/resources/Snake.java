package resources;

import java.awt.Graphics; 
import java.util.LinkedList;


public class Snake {
	
	private LinkedList<SnakePiece> snake = new LinkedList<SnakePiece>();
	
	public Snake(){
		int y = 100;
		for (int i=0;i<10;i++){
			SnakePiece sp = new SnakePiece(i*20,y,Directions.RIGHT);
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

}

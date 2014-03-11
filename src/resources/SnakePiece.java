package resources;

import java.util.LinkedList;

public class SnakePiece {
	
	int x;
	int y;
	
	LinkedList<Directions> directions = new LinkedList<Directions>();
	
	public SnakePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void moveFowardBy(int dy){
		y-=dy;
	}
	public void moveDownBy(int dy){
		y+=dy;
	}
	public void moveLeftBy(int dx){
		x-=dx;
	}
	public void moveRightBy(int dx){
		x+=dx;
	}
	
}

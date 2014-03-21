package resources;

import java.awt.Graphics;

public class SnakePiece {
	
	int x;
	int y;
	int w = 20;
	int h = 20;
	Directions direction;
	
	public SnakePiece(int x, int y, Directions dir) {
		this.x = x;
		this.y = y;
		this.direction = dir;
	}
	
	public SnakePiece(SnakePiece snakePiece) {
		this.x = snakePiece.x;
		this.y = snakePiece.y;
		this.direction = snakePiece.direction;
	}

	public void draw(Graphics g){
		g.drawRect(x-w/2, y-h/2, w, h);
	}
	
	public void moveByDirection(){
		switch(direction){
		case UP:
			moveUpBy(20);
			break;
		case DOWN:
			moveDownBy(20);
			break;
		case LEFT:
			moveLeftBy(20);
			break;
		case RIGHT:
			moveRightBy(20);
			break;
		}
	}
	public void moveUpBy(int dy){
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

	public void setLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	
	
}

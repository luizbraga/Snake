package resources;

import java.awt.Graphics;

import utils.Directions;

public class SnakePiece {
	
	int x;
	int y;
	int w = 20;
	int h = 20;
	Directions direction;
	
	public SnakePiece(){
		
	}
	
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
		g.fillRect(x-w/2, y-h/2, w, h);
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
	private void moveUpBy(int dy){
		y-=dy;
	}
	private void moveDownBy(int dy){
		y+=dy;
	}
	private void moveLeftBy(int dx){
		x-=dx;
	}
	private void moveRightBy(int dx){
		x+=dx;
	}

	public void setLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	
	public boolean hasCollidedWIth(SnakePiece sp){
		return (x == sp.x && y == sp.y);
	}
	
	public boolean hasCollidedWith(Prey p){
		return (x == p.x && y == p.y);
	}
	
}

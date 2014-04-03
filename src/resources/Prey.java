package resources;

import java.awt.Graphics;
import java.util.Random;

public class Prey {
	
	int x;
	int y;
	
	int[] xtop = {-3, 3, 3,-3};	
	int[] ytop = {-11,-11,-3,-3};
	
	int[] xleft = {-11,-3,-3,-11};
	int[] yleft = {-3,-3, 3, 3};
	
	int[] xbottom = {-3, 3, 3,-3};
	int[] ybottom = { 3, 3, 11, 11};
	
	int[] xright = { 3, 11, 11, 3};
	int[] yright = {-3,-3, 3, 3};
	
	

	public void draw(Graphics g){
		int[] xd = new int[4];
		int[] yd = new int[4];
		
		for(int i=0;i<4;i++){
			xd[i] = xtop[i] + x;
			yd[i] = ytop[i] + y;
		}
		g.fillPolygon(xd, yd, 4);
		
		for(int i=0;i<4;i++){
			xd[i] = xleft[i] + x;
			yd[i] = yleft[i] + y;
		}
		g.fillPolygon(xd, yd, 4);
		
		for(int i=0;i<4;i++){
			xd[i] = xbottom[i] + x;
			yd[i] = ybottom[i] + y;
		}
		g.fillPolygon(xd, yd, 4);
		
		for(int i=0;i<4;i++){
			xd[i] = xright[i] + x;
			yd[i] = yright[i] + y;
		}
		g.fillPolygon(xd, yd, 4);
	}
	
	public void spawnPrey(Snake snake, boolean hasWalls){
		// TODO:
		// Need to create a random number starting by 30 and increase 20 by 20
		// This number need to be less than 810 for x and 490 for y
		
		// Check if the position match with the SnakePiece x and y
		// if matches, get another number

		Random r = new Random();
		int randomX = 0;
		int randomY = 0;
		
		if(hasWalls){
			randomX = 50+r.nextInt(36)*20;
			randomY = 50+r.nextInt(20)*20;
		}
		else{
			randomX = 30+r.nextInt(37)*20;
			randomY = 30+r.nextInt(21)*20;
		}
		boolean matches = true;
		
		while(matches){
			for(int i=1;i<snake.getSize();i++){
				if(snake.getSnake().get(i).x == randomX && snake.getSnake().get(i).y == randomY){
					matches = true;
					if(hasWalls){
						randomX = 30+r.nextInt(36)*20;
						randomY = 30+r.nextInt(20)*20;
					}
					else{
						randomX = 30+r.nextInt(37)*20;
						randomY = 30+r.nextInt(21)*20;
					}
				}else{
					matches = false;
				}
			}
		}
	
		this.x = randomX;
		this.y = randomY;
	}

	
}

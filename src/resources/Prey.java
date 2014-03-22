package resources;

import java.awt.Graphics;

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
	
	
	public Prey(int x, int y){
		this.x = x;
		this.y = y;
	}

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
	
	public void spawnPrey(Snake snake){
		// TODO:
		// Need to create a random number starting by 30 and increase 20 by 20
		// This number need to be less than 810 for x and 490 for y
		
	}

	
}

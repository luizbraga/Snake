package resources;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Applet implements KeyListener, Runnable{
	
	// Directions queue
	private LinkedList<Directions> direction = new LinkedList<Directions>();

	private ArrayList<PiecePosition> location = new ArrayList<PiecePosition>();
	
	//private int MAX_DIRECTIONS = 3;
	int index = 0;
	
	//Snake line = new Snake(50, 100, 150, 100); Horizontal
	Snake snake = new Snake();
	Thread t;
	
	// Keys
	public static final int _UP = KeyEvent.VK_UP;
	public static final int _DN = KeyEvent.VK_DOWN;
	public static final int _LF = KeyEvent.VK_LEFT;
	public static final int _RG = KeyEvent.VK_RIGHT;
	
	public void init(){
		setLayout(null);
		
		direction.addLast(Directions.RIGHT);
		addKeyListener(this);
		setFocusable(true);
		t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(true){
			inGameLoop();
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void inGameLoop() {
		updateGame();
		updateSnake();
	}


	public void paint(Graphics g){
		snake.draw(g);
	}

	public void keyPressed(int key) {
		Directions last = direction.peekLast();
		switch(key){
		case _UP:
			
			if(last != Directions.UP && last != Directions.DOWN){
				direction.addLast(Directions.UP);
			}	
			break;
		case _DN:
		
			if(last != Directions.UP && last != Directions.DOWN){
				direction.addLast(Directions.DOWN);
			}	
			break;
		case _LF:
			
			if(last != Directions.LEFT && last != Directions.RIGHT){
				direction.addLast(Directions.LEFT);
			}	
			break;
		case _RG:
			if(last != Directions.LEFT && last != Directions.RIGHT){
				direction.addLast(Directions.RIGHT);
			}	
			break;
		}

	}
	@Override
	public void keyPressed(KeyEvent e){
		keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	private void updateGame() {
		
	}
	
	private void updateSnake() {
		
		SnakePiece newhead = new SnakePiece(snake.getSnake().peekFirst());
		SnakePiece oldhead = snake.getSnake().peekFirst();
		
		newhead.direction = direction.peekFirst();
		newhead.moveByDirection();
		
		if(location.size() < snake.getSnake().size()-1){
			location.add(index, new PiecePosition(oldhead.x, oldhead.y));
		}else{
			location.set(index, new PiecePosition(oldhead.x, oldhead.y));
		}
		
		for(int i=0;i<location.size();i++){
			snake.getSnake().get(i+1).setLocation(location.get(i).x,location.get(i).y);
		}
		snake.getSnake().set(0,newhead);
		
		
		if(index == snake.getSnake().size()-2){
			index = 0;
		}else{
			index++;
		}
		
		if(direction.size() > 1){
			direction.poll();
		}
		
	}
	
}

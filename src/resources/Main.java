package resources;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Main extends Applet implements KeyListener, Runnable{
	
	// Directions queue
	private LinkedList<Directions> direction = new LinkedList<Directions>();
	private int MAX_DIRECTIONS = 3;
	
	
	
	//Snake line = new Snake(50, 100, 150, 100); Horizontal
	Snake snake = new Snake();
	Thread t;
	SnakePiece head;
	
	// Keys
	public static final int _UP = KeyEvent.VK_UP;
	public static final int _DN = KeyEvent.VK_DOWN;
	public static final int _LF = KeyEvent.VK_LEFT;
	public static final int _RG = KeyEvent.VK_RIGHT;
	
	public void init(){
		setLayout(null);
		requestFocus();
		
		head = snake.getSnake().peekFirst();
		
		addKeyListener(this);
		t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(true){
			inGameLoop();
			repaint();
			try {
				Thread.sleep(15);
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
		switch(key){
		case _UP:
			if (direction.size() < MAX_DIRECTIONS){
				Directions last = head.directions.peekLast();
				if(last != Directions.UP && last != Directions.DOWN){
					direction.addLast(Directions.UP);
				}	
			}

			break;
		case _DN:
			if (direction.size() < MAX_DIRECTIONS){
				Directions last = direction.peekLast();
				if(last != Directions.UP && last != Directions.DOWN){
					direction.addLast(Directions.DOWN);
				}	
			}
			

			break;
		case _LF:
			if (direction.size() < MAX_DIRECTIONS){
				Directions last = direction.peekLast();
				if(last != Directions.LEFT && last != Directions.RIGHT){
					direction.addLast(Directions.LEFT);
				}	
			}
			
			break;
		case _RG:
			if (direction.size() < MAX_DIRECTIONS){
				Directions last = direction.peekLast();
				if(last != Directions.LEFT && last != Directions.RIGHT){
					direction.addLast(Directions.RIGHT);
				}	
			}
			
			break;
		}

	}
	@Override
	public void keyPressed(KeyEvent e){
		keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	private void updateGame() {
		
	}
	
	private void updateSnake() {
		Directions dir = direction.peekFirst();
		
		snake.getSnake();
		
	}
	
}

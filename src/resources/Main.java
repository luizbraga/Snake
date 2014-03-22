package resources;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

import utils.Directions;

public class Main extends Applet implements KeyListener, Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4940501782671450799L;
	
	// Screen variables
	int BLOCK_SIZE = 20;
	int SCREEN_HEIGHT = 24*BLOCK_SIZE;// 480
	int SCREEN_WIDTH = 40*BLOCK_SIZE; // 800
	
	// Directions queue
	private LinkedList<Directions> direction = new LinkedList<Directions>();
	private ArrayList<PiecePosition> location = new ArrayList<PiecePosition>();
	int index = 0;
	
	// Snake creation
	Snake snake = new Snake();
	Thread t;
	Prey prey = new Prey(30,50);
	
	// Keys
	public static final int _UP = KeyEvent.VK_UP;
	public static final int _DN = KeyEvent.VK_DOWN;
	public static final int _LF = KeyEvent.VK_LEFT;
	public static final int _RG = KeyEvent.VK_RIGHT;
	public static final int _P = KeyEvent.VK_P;
	public static final int _Q = KeyEvent.VK_Q;
	public static final int _ENTER = KeyEvent.VK_ENTER;
	
	// Game status
	private int score = 0;
	private boolean isPaused = false;
	private boolean isEnded = false;
	private boolean hasCollidedWithBody = false;
	
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
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void inGameLoop() {
		if(!hasCollidedWithBody){
			if(!isPaused){
				updateSnake();
				updateGame();
			}
		}

	}
	
	public void paint(Graphics g){
		snake.draw(g);
		g.drawRect(20, 20, SCREEN_WIDTH, SCREEN_HEIGHT);
		prey.draw(g);
		g.drawString("Score: "+score, 20, 20);
		
/*		g.drawString("Snake: ("+snake.getSnake().peekFirst().x+","+snake.getSnake().peekFirst().y+")", 60, 20);
		g.drawString("Prey: ("+prey.x+","+prey.y+")", 60, 9);*/
		
		if(!hasCollidedWithBody){
			if(isPaused){
				g.drawString("PAUSED", 240, 240);
			}
		}else{
			g.drawString("GAME OVER", 240, 40);
			g.drawString("Press ENTER to start a new game", 240, 60);
		}
	}

	public void keyPressed(int key) {
		Directions last = direction.peekLast();
		switch(key){
		case _UP:
			if(!hasCollidedWithBody){
				if(!isPaused){
					if(last != Directions.UP && last != Directions.DOWN){
						direction.addLast(Directions.UP);
					}
				}
			}
			break;
		case _DN:
			if(!hasCollidedWithBody){
				if(!isPaused){
					if(last != Directions.UP && last != Directions.DOWN){
						direction.addLast(Directions.DOWN);
					}
				}
			}
			break;
		case _LF:
			if(!hasCollidedWithBody){
				if(!isPaused){
					if(last != Directions.LEFT && last != Directions.RIGHT){
						direction.addLast(Directions.LEFT);
					}
				}
			}
			break;
		case _RG:
			if(!hasCollidedWithBody){
				if(!isPaused){
					if(last != Directions.LEFT && last != Directions.RIGHT){
						direction.addLast(Directions.RIGHT);
					}
				}
			}
			break;
		case _P:
			if(!hasCollidedWithBody){
				if(isPaused){
					isPaused = false;
				}else{
					isPaused = true;
				}
			}
			break;
		case _ENTER:
			if(hasCollidedWithBody){
				hasCollidedWithBody = false;
				snake = new Snake();
				direction = new LinkedList<Directions>();
				direction.addLast(Directions.RIGHT);
			}break;
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
		SnakePiece head = snake.getSnake().peekFirst();
		
		// Simple Collision Detection
		for(int i=1;i<snake.getSnake().size(); i++){
			SnakePiece bodyPiece = snake.getSnake().get(i);
			if(head.hasCollidedWIth(bodyPiece)){
				hasCollidedWithBody = true;
			}
		}
		
		if(head.hasCollidedWith(prey)){
			SnakePiece lastPiece = snake.getSnake().peekLast();
			snake.getSnake().add(new SnakePiece(lastPiece.x,lastPiece.y, lastPiece.direction));
			score += 10;
			prey.spawnPrey(snake);
		}
		
	}
	
	private void updateSnake() {
		
		SnakePiece newhead = new SnakePiece(snake.getSnake().peekFirst());
		SnakePiece oldhead = snake.getSnake().peekFirst();
		
		newhead.direction = direction.peekFirst();
		newhead.moveByDirection();
		
		// 10 is the difference between (x,y) with (width,height)
		if(newhead.x <= BLOCK_SIZE-10){
			newhead.x = SCREEN_WIDTH+10;
		}else if(newhead.y <= BLOCK_SIZE-10){
			newhead.y = SCREEN_HEIGHT+10;
		}else if(newhead.x > SCREEN_WIDTH+10){
			newhead.x = BLOCK_SIZE+10;
		}else if(newhead.y > SCREEN_HEIGHT+10){
			newhead.y = BLOCK_SIZE+10;
		}
		
		
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

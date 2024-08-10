package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int ScreenWidth = tileSize * maxScreenCol;
	final int ScreenHeight = tileSize * maxScreenRow;
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);	
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}


	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.0166666
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			//System.out.println("The game loop is running");
			
			// 1 UPDATE: updates information such as character information
			update();
			
			// 2 DRAW: draw the screen with updated information
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep( (long) remainingTime);

				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		
		if (keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if (keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if (keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
	

}

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import enitiy.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int playerSize = 32 * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int ScreenWidth = tileSize * maxScreenCol;
	public final int ScreenHeight = tileSize * maxScreenRow;
		
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	
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
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS; // 0.0166666
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			
//			//System.out.println("The game loop is running");
//			
//			// 1 UPDATE: updates information such as character information
//			update();
//			
//			// 2 DRAW: draw the screen with updated information
//			repaint();
//
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//
//				Thread.sleep( (long) remainingTime);
//
//				nextDrawTime += drawInterval;
//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	public void  run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			timer += (currentTime - lastTime);
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0; 
			}
			
		}
		
	}
	
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	

}

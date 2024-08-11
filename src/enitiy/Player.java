package enitiy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Enitiy{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_4.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_5.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/up_walk_6.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_4.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_5.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/down_walk_6.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/left_walk_6.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/right_walk_6.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		if (keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		else if (keyH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		else if (keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		else if (keyH.rightPressed == true) {
			direction = "right";
			x += speed;
			
		}
		
		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 4;
			}
			else if (spriteNum == 4) {
				spriteNum = 5;
			}
			else if (spriteNum == 5) {
				spriteNum = 6;
			}
			else if (spriteNum == 6) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1)
				image = up1;
			if (spriteNum == 2)
				image = up2;
			if (spriteNum == 3)
				image = up3;
			if (spriteNum == 4)
				image = up4;
			if (spriteNum == 5)
				image = up5;
			if (spriteNum == 6)
				image = up6;
			break;
		case "down":
			if (spriteNum == 1)
				image = down1;
			if (spriteNum == 2)
				image = down2;
			if (spriteNum == 3)
				image = down3;
			if (spriteNum == 4)
				image = down4;
			if (spriteNum == 5)
				image = down5;
			if (spriteNum == 6)
				image = down6;
			break;
		case "left":
			if (spriteNum == 1)
				image = left1;
			if (spriteNum == 2)
				image = left2;
			if (spriteNum == 3)
				image = left3;
			if (spriteNum == 4)
				image = left4;
			if (spriteNum == 5)
				image = left5;
			if (spriteNum == 6)
				image = left6;
			break;
		case "right":
			if (spriteNum == 1)
				image = right1;
			if (spriteNum == 2)
				image = right2;
			if (spriteNum == 3)
				image = right3;
			if (spriteNum == 4)
				image = right4;
			if (spriteNum == 5)
				image = right5;
			if (spriteNum == 6)
				image = right6;
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
		
	}
	
}

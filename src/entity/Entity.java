package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	//public BufferedImage up1, up2, up3, up4, up5, up6, down1, down2, down3, down4, down5, down6, left1, left2, left3, left4, left5, left6, right1, right2, right3, right4, right5, right6;
    // Arrays to hold the animation frames for walking and idle
    public BufferedImage[] upImages, downImages, leftImages, rightImages;
    public BufferedImage[] upIdle, downIdle, leftIdle, rightIdle;
	public String direction;
	public boolean isWalking;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
}

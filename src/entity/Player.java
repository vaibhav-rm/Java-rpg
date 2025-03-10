package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;									
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.ScreenWidth / 2 - (gp.playerSize / 2);
        screenY = gp.ScreenHeight / 2 - (gp.playerSize / 2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        loadPlayerImages();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void loadPlayerImages() {
        try {
            // Load walking images into arrays
            upImages = loadAnimation("/player/up_walk_", 6);
            downImages = loadAnimation("/player/down_walk_", 6);
            leftImages = loadAnimation("/player/left_walk_", 6);
            rightImages = loadAnimation("/player/right_walk_", 6);

            // Load idle images into arrays
            upIdle = loadAnimation("/player/up_idle_", 6);
            downIdle = loadAnimation("/player/down_idle_", 6);
            leftIdle = loadAnimation("/player/left_idle_", 6);
            rightIdle = loadAnimation("/player/right_idle_", 6);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility method to load a sequence of images
    private BufferedImage[] loadAnimation(String pathPrefix, int frameCount) throws IOException {
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = ImageIO.read(getClass().getResourceAsStream(pathPrefix + (i + 1) + ".png"));
        }
        return frames;
    }

    public void update() {
        boolean isMoving = false;

        if (keyH.upPressed) {
            direction = "up";
            worldY -= speed;
            isMoving = true;
        } else if (keyH.downPressed) {
            direction = "down";
            worldY += speed;
            isMoving = true;
        } else if (keyH.leftPressed) {
            direction = "left";
            worldX -= speed;                     
            isMoving = true;
        } else if (keyH.rightPressed) {
            direction = "right";
            worldX += speed;
            isMoving = true;
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum % 6) + 1;  // Cycle through 1 to 6
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage[] currentImages;
        BufferedImage[] idleImages;

        switch (direction) {
            case "up":
                currentImages = upImages;
                idleImages = upIdle;
                break;
            case "down":
                currentImages = downImages;
                idleImages = downIdle;
                break;
            case "left":
                currentImages = leftImages;
                idleImages = leftIdle;
                break;	
            case "right":
                currentImages = rightImages;
                idleImages = rightIdle;
                break;
            default:
                currentImages = downImages;  // Default to down if something goes wrong
                idleImages = downIdle;
                break;
        }

        BufferedImage image = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed
                ? currentImages[spriteNum - 1]  // Use walking animation
                : idleImages[spriteNum - 1];  // Use idle animation

        g2.drawImage(image, screenX, screenY, gp.playerSize, gp.playerSize, null);
    }
}

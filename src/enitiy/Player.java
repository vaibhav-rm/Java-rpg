package enitiy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Enitiy {
    
    GamePanel gp;									
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        loadPlayerImages();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
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
            y -= speed;
            isMoving = true;
        } else if (keyH.downPressed) {
            direction = "down";
            y += speed;
            isMoving = true;
        } else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;                     
            isMoving = true;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
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

        g2.drawImage(image, x, y, gp.playerSize, gp.playerSize, null);
    }
}

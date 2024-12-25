package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity
{
    private GamePanel gp;
    private KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        this.setDefaultValues();
        this.loadImages();
    }

    public void setDefaultValues()
    {
        x = gp.SCREEN_WIDTH/2;
        y = gp.SCREEN_HEIGHT/2;
        speed = gp.SCALE;
        direction = "stopped";
    }

    private void loadImages()
    {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/right_2.png"));
            stopped1 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/stopped_1.png"));
            stopped2 = ImageIO.read(getClass().getResourceAsStream("/resources/images/player/stopped_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed) {
            direction = "down";
            y += speed;
        }
        else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        } else {
            direction = "stopped";
        }
    }

    public void draw(Graphics2D g)
    {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = switchImage ? up1:up2;
                break;
            case "down":
                image = switchImage ? down1:down2;
                break;
            case "left":
                image = switchImage ? left1:left2;
                break;
            case "right":
                image = switchImage ? right1:right2;
                break;
            default:
                image = switchImage ? stopped1:stopped2;
                break;
        }
        
        countFrame();

        g.drawImage(image, x, y, gp.SPRITE_SIZE, gp.SPRITE_SIZE, null);
        g.dispose();
    }
}

package entity;

import java.awt.image.BufferedImage;

public class Entity
{
    public int x, y, speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stopped1, stopped2;
    public String direction;
    public final int framesPerImage = 12;
    public int countFrames = 0;
    public boolean switchImage = false;

    public void countFrame()
    {
        if (countFrames >= framesPerImage) {
            countFrames = 0;
            switchImage = !switchImage;
        } else {
            countFrames++;
        }
    }
}

package main;
import javax.swing.JPanel;

import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable
{
    // Game Constants 
    public final int ORIGINAL_SPRITE_SIZE = 16;
    public final int SCALE = 5;

    public final int SPRITE_SIZE = ORIGINAL_SPRITE_SIZE * SCALE;

    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;

    public final int SCREEN_WIDTH = MAX_SCREEN_COL * SPRITE_SIZE;
    public final int SCREEN_HEIGHT = MAX_SCREEN_ROW * SPRITE_SIZE;

    final int FPS = 60;

    // Game Composion
    private TileManager tileManager;
    private KeyHandler keyHandler;
    private Thread gameThread;

    // Game Entities
    private Player player;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);

        player = new Player(this, keyHandler);
        
        tileManager = new TileManager(this);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double frameInterval = 1_000_000_000/FPS;
        double nextFrame = System.nanoTime() + frameInterval;
        
        while (gameThread != null) {

            update();
            repaint();

            try {
                double remaningTime = nextFrame - System.nanoTime();
                remaningTime /= 1_000_000;

                if(remaningTime < 0) {
                    remaningTime = 0;
                }

                Thread.sleep((long) remaningTime);

                nextFrame += frameInterval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}

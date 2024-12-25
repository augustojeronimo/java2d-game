package main;
import javax.swing.JFrame;

public class App
{
    public static void main(String[] args) throws Exception
    {
        JFrame window = new JFrame("Blue Boy - Java 2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.startGameThread();

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}

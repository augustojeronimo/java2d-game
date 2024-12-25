package main;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager
{
    private GamePanel gp;
    private Tile[] tile;
    private int[][] map;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        loadTileTypes();
        loadMap("/resources/maps/001.map");
    }

    public void loadTileTypes()
    {
        String[] tileTypes = {
            "grass",
            "water",
            "tree",
            "wall",
            "earth",
            "sand",
        };

        this.tile = new Tile[tileTypes.length];
        
        try {
            for (int i = 0; i < tileTypes.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/resources/images/tiles/"+tileTypes[i]+".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath)
    {
        map = new int[gp.MAX_SCREEN_ROW][gp.MAX_SCREEN_COL];
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.MAX_SCREEN_ROW; row++) {
                String[] mapRow = reader.readLine().split(" ");

                for (int col = 0; col < gp.MAX_SCREEN_COL; col++) {
                    map[row][col] = Integer.parseInt(mapRow[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g)
    {
        int col = 0, row = 0, x =0, y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
            int type = map[row][col];
            g.drawImage(tile[type].image, x, y, gp.SPRITE_SIZE, gp.SPRITE_SIZE, null);
            col++;
            x += gp.SPRITE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                row++;
                x = 0;
                y += gp.SPRITE_SIZE;
            }
        }
    }
}

package main;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int[][] map;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        loadTileTypes();
        loadMap("/resources/maps/002.map");
    }

    public void loadTileTypes()
    {
        String[][] tileTypes = {
            // name, colision
            {"grass", "off"}, // 0
            {"water", "on"}, // 1
            {"tree", "on"}, // 2
            {"wall", "on"}, // 3
            {"earth", "off"}, // 4
            {"sand", "off"}, // 5
        };

        this.tile = new Tile[tileTypes.length];
        
        try {
            for (int i = 0; i < tileTypes.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/resources/images/tiles/"+tileTypes[i][0]+".png"));
                tile[i].colision = tileTypes[i][1].equals("on");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath)
    {
        map = new int[gp.MAX_WORLD_ROW][gp.MAX_WORLD_COL];
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {
                String[] mapRow = reader.readLine().split(" ");

                for (int col = 0; col < gp.MAX_WORLD_COL; col++) {
                    map[row][col] = Integer.parseInt(mapRow[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g)
    {
        for (int wRow = 0; wRow < gp.MAX_WORLD_ROW; wRow++) {
            for (int wCol = 0; wCol < gp.MAX_WORLD_COL; wCol++) {
                int type = map[wRow][wCol];

                int worldX = wCol * gp.SPRITE_SIZE;
                int worldY = wRow * gp.SPRITE_SIZE;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (
                    worldX + gp.SPRITE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.SPRITE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.SPRITE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.SPRITE_SIZE < gp.player.worldY + gp.player.screenY
                ) {
                    g.drawImage(tile[type].image, screenX, screenY, gp.SPRITE_SIZE, gp.SPRITE_SIZE, null);
                }
            }
        }
    }
}

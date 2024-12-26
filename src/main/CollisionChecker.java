package main;

import entity.Entity;

public class CollisionChecker
{
    GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int leftX = entity.worldX + entity.solidArea.x;
        int rightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int topY = entity.worldY + entity.solidArea.y;
        int bottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int leftCol = leftX/gp.SPRITE_SIZE;
        int rightCol = rightX/gp.SPRITE_SIZE;
        int topRow = topY/gp.SPRITE_SIZE;
        int bottomRow = bottomY/gp.SPRITE_SIZE;

        int nextTile = 0, tile1 = 0, tile2 = 0;

        switch (entity.direction) {
            case "up":
                nextTile = (topY - entity.speed) / gp.SPRITE_SIZE;
                tile1 = gp.tileManager.map[nextTile][leftCol];
                tile2 = gp.tileManager.map[nextTile][rightCol];
                break;
            case "down":
                nextTile = (bottomY + entity.speed) / gp.SPRITE_SIZE;
                tile1 = gp.tileManager.map[nextTile][leftCol];
                tile2 = gp.tileManager.map[nextTile][rightCol];
                break;
            case "left":
                nextTile = (leftX - entity.speed) / gp.SPRITE_SIZE;
                tile1 = gp.tileManager.map[topRow][nextTile];
                tile2 = gp.tileManager.map[bottomRow][nextTile];
                break;
            case "right":
                nextTile = (rightX + entity.speed) / gp.SPRITE_SIZE;
                tile1 = gp.tileManager.map[topRow][nextTile];
                tile2 = gp.tileManager.map[bottomRow][nextTile];
                break;
            default:
                break;
        }

        if (gp.tileManager.tile[tile1].colision || gp.tileManager.tile[tile2].colision) {
            entity.colisionOn = true;
        }
    }
}

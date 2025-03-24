package main;

import characters.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(Entity characters){

        int charactersLeftWorldX= characters.worldX + characters.solidArea.x;
        int charactersRightWorldX= characters.worldX + characters.solidArea.x + characters.solidArea.width;
        int charactersTopWorldY= characters.worldY + characters.solidArea.y;
        int charactersBottomWorldY= characters.worldY + characters.solidArea.y + characters.solidArea.height;

        int charactersLeftCol=charactersLeftWorldX/gp.tileSize;
        int charactersRightCol=charactersRightWorldX/gp.tileSize;
        int charactersTopRow=charactersTopWorldY/gp.tileSize;
        int charactersBottomRow=charactersBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(characters.direction){
            case "up":
                charactersTopRow = (charactersTopWorldY - characters.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charactersLeftCol][charactersTopRow];
                tileNum2 = gp.tileM.mapTileNum[charactersRightCol][charactersTopRow];
                if(gp.tileM.ambient[tileNum1].collision || gp.tileM.ambient[tileNum2].collision){
                    characters.collisionOn = true;
                }
                break;

            case "down":
                charactersBottomRow = (charactersBottomWorldY + characters.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charactersLeftCol][charactersBottomRow];
                tileNum2 = gp.tileM.mapTileNum[charactersRightCol][charactersBottomRow];
                if(gp.tileM.ambient[tileNum1].collision || gp.tileM.ambient[tileNum2].collision){
                    characters.collisionOn = true;
                }
                break;

            case "left":
                charactersLeftCol = (charactersLeftWorldX - characters.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charactersLeftCol][charactersTopRow];
                tileNum2 = gp.tileM.mapTileNum[charactersLeftCol][charactersBottomRow];
                if(gp.tileM.ambient[tileNum1].collision || gp.tileM.ambient[tileNum2].collision){
                    characters.collisionOn = true;
                }
                break;

            case "right":
                charactersRightCol = (charactersRightWorldX + characters.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[charactersRightCol][charactersTopRow];
                tileNum2 = gp.tileM.mapTileNum[charactersRightCol][charactersBottomRow];
                if(gp.tileM.ambient[tileNum1].collision || gp.tileM.ambient[tileNum2].collision){
                    characters.collisionOn = true;
                }
                break;
        }
    }
}

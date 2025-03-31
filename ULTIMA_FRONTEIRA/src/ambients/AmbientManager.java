package ambients;

import main.GamePanel;
import main.UtilityTool;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class AmbientManager {

    GamePanel gp;
    public Ambient[] ambient;
    public int mapTileNum[][];

    public AmbientManager(GamePanel gp){ //Construtor

        this.gp=gp; //O this se refere ao atributo da instância da classe e não ao parâmetro do método

        ambient = new Ambient[100]; //Quantidade dos tipos de bloco

        mapTileNum= new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage(); //Chamando o método no construtor
        loadMap("/maps/world01.txt",0);
    }

    public void getTileImage(){//Construtor

        setup(0,"grass00", false);
        setup(1,"grass01", false);
        setup(2,"grass02", false);
        setup(3,"grass03", false);

        setup(4,"water00", true);
        setup(5,"water01", true);
        setup(6,"water02", true);
        setup(7,"water03", true);
        setup(8,"water04", true);
        setup(9,"water05", true);
        setup(10,"water06", true);
        setup(11,"water07", true);
        setup(12,"water08", true);
        setup(13,"water09", true);
        setup(14,"water10", true);
        setup(15,"water11", true);
        setup(16,"water12", true);
        setup(17,"water13", true);

        setup(18,"sand00", false);

        setup(19,"stone00" ,true);

        setup(20,"tree01", true);

        setup(21,"way01", false);
        setup(22,"way02", false);
        setup(23,"way03", false);
        setup(24,"way04", false);
        setup(25,"way05", false);
        setup(26,"way06", false);
        setup(27,"way07", false);
        setup(28,"way08", false);
        setup(29,"way09", false);
        setup(30,"way10", false);
        setup(31,"way11", false);
        setup(32,"way12", false);
        setup(33,"way13", false);
        setup(34,"way14", false);
        setup(35,"way15", false);
        setup(36,"way16", false);
        setup(37,"way17", false);
        setup(38,"way18", false);
        setup(39,"way19", false);
        setup(40,"way20", false);

        setup(41,"river00", true);
        setup(42,"river01", true);
        setup(43,"river02", true);

        setup(44,"shrub01", true);

        setup(45,"bwater00", true);
        setup(46,"bwater01", true);
        setup(47,"bwater02", true);
        setup(48,"bwater03", true);
        setup(49,"bwater04", true);
        setup(50,"bwater05", true);
        setup(51,"bwater06", true);
        setup(52,"bwater07", true);
        setup(53,"bwater08", true);

    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool= new UtilityTool();

        try{

            ambient[index]=new Ambient();
            ambient[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            ambient[index].image=uTool.scaleImage(ambient[index].image, gp.tileSize, gp.tileSize);
            ambient[index].collision = collision; //Atribuindo o valor de colisão

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void loadMap (String filePath, int map){

        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row< gp.maxWorldRow){

                String line= br.readLine();

                while(col<gp.maxWorldCol){

                    String numbers[]=line.split(" ");

                    int num=Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();;

        } catch (Exception e) {

        }
    }
    public void draw(Graphics2D g2) {

        int worldCol=0;
        int worldRow=0;

        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){

            int tileNum= mapTileNum[worldCol][worldRow];

            int worldX= worldCol * gp.tileSize;
            int worldY= worldRow * gp.tileSize;
            int screenX= worldX - gp.player.worldX + gp.player.screenX;
            int screenY= worldY - gp.player.worldY + gp.player.screenY;

            //Parar de mover câmera no final
            if(gp.player.screenX>gp.player.worldX){
                screenX = worldX;
            }
            if(gp.player.screenY>gp.player.worldY){
                screenY = worldY;
            }
            int rightOffset=gp.screenWidth-gp.player.screenX;
            if(rightOffset>gp.worldWidth-gp.player.worldX){
                screenX = gp.screenWidth-(gp.worldWidth-worldX);
            }
            int bottomOffset=gp.screenHeight-gp.player.screenY;
            if(bottomOffset>gp.worldHeight-gp.player.worldY){
                screenY = gp.screenHeight-(gp.worldHeight-worldY);
            }

            if (worldX + gp.tileSize >gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(ambient[tileNum].image, screenX, screenY, null); //add gp.tileSize
            }
            else if(gp.player.screenX>gp.player.worldX || gp.player.screenY > gp.player.worldY || rightOffset>gp.worldWidth-gp.player.worldX || bottomOffset>gp.worldHeight-gp.player.worldY){
                g2.drawImage(ambient[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }

        }
    }
}

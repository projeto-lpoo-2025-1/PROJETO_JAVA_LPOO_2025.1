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

        ambient = new Ambient[50]; //Quantidade dos tipos de bloco

        mapTileNum= new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage(); //Chamando o método no construtor
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){//Construtor

        setup(0,"grass00", false);
        setup(1,"grass01", false);
        setup(2,"grass02", false);
        setup(3,"grass03", false);

        setup(4,"water00", true);
        setup(5,"water01", true);

        setup(6,"sand00", false);

        setup(7,"stone00" ,true);

        setup(8,"tree00", true);

        setup(9,"way01", false);
        setup(10,"way02", false);
        setup(11,"way03", false);
        setup(12,"way04", false);
        setup(13,"way05", false);
        setup(14,"way06", false);
        setup(15,"way07", false);
        setup(16,"way08", false);
        setup(17,"way09", false);
        setup(18,"way10", false);
        setup(19,"way11", false);
        setup(20,"way12", false);
        setup(21,"way13", false);
        setup(22,"way14", false);
        setup(23,"way15", false);
        setup(24,"way16", false);
        setup(25,"way17", false);
        setup(26,"way18", false);
        setup(27,"way19", false);
        setup(28,"way20", false);
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
    public void loadMap (String filePath){

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

            int worldX= worldCol* gp.tileSize;
            int worldY= worldRow*gp.tileSize;
            int screenX= worldX - gp.player.worldX + gp.player.screenX;
            int screenY= worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize >gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(ambient[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); //add gp.tileSize
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;}

        }
    }
}

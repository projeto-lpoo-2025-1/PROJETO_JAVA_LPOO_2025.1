package characters;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp; //gp é uma referência para o painel de jogo onde o jogador será desenhado e onde o jogo será atualizado.
    KeyHandler keyH; //keyH é uma referência para o manipulador de teclas, que controla as teclas pressionadas para movimentação.

    public final int screenX;
    public final int screenY;

    int counter2=0;

    public Player(GamePanel gp, KeyHandler keyH) { //Construtor da classe que recebe o painel de jogo e manipulador de teclas
        this.gp = gp;
        this.keyH = keyH;

        screenX=gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues(); //Chama o método que vai configurar valores padrão para o jogador
        getPlayerImage(); //Aqui garantimos que as imagens do jogador sejam carregadas
    }

    public void setDefaultValues() { //Este método definie valores iniciais para a posição do jogador, sua velocidade e direção inicial
        worldX = gp.tileSize*49;
        worldY = gp.tileSize*49;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage() {}

    public void update() { //Este método atualiza a posição e a animação do jogador com base nas teclas pressionadas

        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true){

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);


            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        if (worldY - speed >= 0) { //Verifica se não vai além do topo
                            worldY -= speed;
                        }
                        break;
                    case "down":
                        if (worldY + speed <= gp.worldHeight - gp.tileSize) { //Verifica se não vai além do fundo
                            worldY += speed;
                        }
                        break;
                    case "left":
                        if (worldX - speed >= 0) { //Verifica se não vai além da esquerda
                            worldX -= speed;
                        }
                        break;
                    case "right":
                        if (worldX + speed <= gp.worldWidth - gp.tileSize) { //Verifica se não vai além da direita
                            worldX += speed;
                        }
                        break;
                }
            }


            spriteCounter++; //Conta quantas vezes o jogador se move e, quando atinge 20, troca a animação
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void draw(Graphics2D g2) { //Este método desenha o jogador na tela
        BufferedImage image = null;

        switch (direction) { //Verifica a direção atural e escolhe a imagem correspondente
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        int x = screenX;
        int y = screenY;

        if(screenX>worldX){
            x = worldX;
        }
        if(screenY>worldY){
            y = worldY;
        }
        int rightOffset=gp.screenWidth - screenX;
        if(rightOffset>gp.worldWidth - worldX){
            x=gp.screenWidth-(gp.worldWidth - worldX);
        }
        int bottomOffset=gp.screenHeight - screenY;
        if(bottomOffset>gp.worldHeight - worldY){
            y=gp.screenHeight-(gp.worldHeight - worldY);
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); //O jogador é desenhado na posição (x, y) com o tamanho da célula (tileSize), que está definido no GamePanel.
    }
}

package characters;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Survivor extends Player {

    public Survivor(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        getPlayerImage();
    }

    @Override
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/survivor/survivor_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package characters;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Detective extends Player { //

    public Detective(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        getPlayerImage();
    }

    @Override
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/detective/detective_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package characters;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Doctor extends Player {

    public Doctor(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        getPlayerImage();
    }

    @Override
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/doctor/doctor_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

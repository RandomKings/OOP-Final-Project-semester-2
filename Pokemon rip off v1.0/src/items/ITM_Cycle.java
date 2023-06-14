package items;

import entity.Entity;
import game2d.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ITM_Cycle extends Entity {

    public ITM_Cycle(GamePanel gp) {
        super(gp);

        name = "cycle";

        image = setUp("Items", "cycle");

        collision = false;
    }
}

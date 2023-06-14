package items;

import entity.Entity;
import game2d.GamePanel;

import javax.imageio.ImageIO;

    public class ITM_Pokeball extends Entity {

    public ITM_Pokeball(GamePanel gp){
        super(gp);

        name = "pokeball";
        image = setUp("items","pokeball" );



    }
}

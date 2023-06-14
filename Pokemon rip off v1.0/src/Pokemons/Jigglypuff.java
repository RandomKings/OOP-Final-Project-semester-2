package Pokemons;

import entity.Entity;
import game2d.GamePanel;

public class Jigglypuff  extends Entity {

    public Jigglypuff(GamePanel gp) {
        super(gp);

        name = "Jigglypuff";
        pHP = 150;
        pLevel = 1;
        image = setUp("pokemon","jigglypuff");
        image1 = setUp("playerPokemon","Jigglypuff back");
        collision = true;
        description = (("name: " + name) + ("\nHP: " + pHP) + ("\nLevel: " +pLevel));
    }

}

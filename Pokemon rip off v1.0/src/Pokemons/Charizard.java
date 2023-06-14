package Pokemons;

import entity.Entity;
import game2d.GamePanel;



public class Charizard extends Entity {

    public Charizard(GamePanel gp) {
        super(gp);


        name = "Charizard";
        pHP = 150;
        pLevel = 1;
        image = setUp("pokemon","Charizard");
        image1 = setUp("playerPokemon","charizard back");
        collision = true;
        description = (("name: " + name) + ("\nHP: " + pHP) + ("\nLevel: " +pLevel));


    }
}



package Pokemons;

import entity.Entity;
import game2d.GamePanel;

public class Pikachu extends Entity {

    public Pikachu(GamePanel gp) {
        super(gp);


        name = "Pikachu";
        image = setUp("pokemon", "pikachu");
        image1 = setUp("playerPokemon", "pikachu back");
        collision = true;
        pHP = 150;
        pLevel = 1;
        description = (("name: " + name) + ("\n HP: " + pHP) + ("\n Level: " +pLevel));
    }
}


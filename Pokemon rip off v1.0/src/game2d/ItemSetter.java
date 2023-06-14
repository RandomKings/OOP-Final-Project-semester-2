package game2d;

import items.ITM_Cycle;
import items.ITM_Pokeball;
import Pokemons.*;

import java.util.ArrayList;
import java.util.Random;

public class ItemSetter {

    GamePanel gp;

    public ItemSetter(GamePanel gp){
        this.gp = gp;


    }
    //set items on the map
    public void setItem(){
//        int spotX = generateRandomSpotX();
//        int spotY = generateRandomSpotY();
//        int tileNum1 = getTileSpot();

        //gp.item[0] = new pikachu(gp);
       // System.out.println(spotX + " " + spotY);

        //if (gp.tileM.tile[].collision == false){
        gp.item[0] = new Pikachu(gp);
        gp.item[1] = new ITM_Pokeball(gp);
        gp.item[2] = new ITM_Pokeball(gp);
        gp.item[3] = new ITM_Cycle(gp);
        gp.item[4] = new Charizard(gp);
        gp.item[5] = new Jigglypuff(gp);


        // int tileNum = gp.tileM.mapTileNum[spotX][spotY];

                //generate random location for items
               for (int i = 0; i < gp.item.length ; i++){
                   Random generator = new Random();
                   ArrayList<Integer> plsWork = gp.tileM.allCords.get(generator.nextInt(gp.tileM.allCords.size()));

                   System.out.println(gp.item.length);

                   gp.item[i].worldX = plsWork.get(0) * gp.tileSize;
                   gp.item[i].worldY = plsWork.get(1) * gp.tileSize;
               }
            }



}

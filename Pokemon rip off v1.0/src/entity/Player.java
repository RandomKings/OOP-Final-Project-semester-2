package entity;


import game2d.GamePanel;
import game2d.KeyHandler;


import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int numBalls = 0;
    private int newNumBalls;

    private boolean onCycle = false;
    private boolean hasCycle = false;
    boolean inBattle = false;

    public ArrayList<Entity> playerPokemons = new ArrayList<>();
    public int playerPoke = 0;
    public ArrayList<Entity> wildPokemon = new ArrayList<>();


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        //player location
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);


        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        //default values for player
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        // walking image
        up1 = setUp("player", "up1");
        up2 = setUp("player", "up2");
        down1 = setUp("player", "down1");
        down2 = setUp("player", "down2");
        left1 = setUp("player", "left1");
        left2 = setUp("player", "left2");
        right1 = setUp("player", "right1");
        right2 = setUp("player", "right2");

        //cycling image
        upc1 = setUp("playercycling", "upc1");
        upc2 = setUp("playercycling", "upc2");
        downc1 = setUp("playercycling", "downc1");
        downc2 = setUp("playercycling", "downc2");
        leftc1 = setUp("playercycling", "leftc1");
        leftc2 = setUp("playercycling", "leftc2");
        rightc1 = setUp("playercycling", "rightc1");
        rightc2 = setUp("playercycling", "rightc2");

    }

    public void update() {
        //cycle logic
        if (hasCycle == true && onCycle == false && keyH.zPressed == true) {
            onCycle = true;
            speed += 2;
        } else if (onCycle == true && keyH.zPressed == false) {
            System.out.println("lalala");
            onCycle = false;
            speed = 4;
        }


        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }


            //check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int itemIndex = gp.cChecker.checkObject(this, true);
            pickUpIItem(itemIndex);

            //getPokemon();

            //if collision is false player can move
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            //animation for player
            spriteCounter++;
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
    //get items
    public void pickUpIItem(int i) {
        if (i != 999) {

            String itemName = gp.item[i].name;

            switch (itemName) {
                case "pokeball":
                    gp.item[i] = null;
                    numBalls++;
                    gp.ui.showMessage("Obtained 1 Pokeball :D");
                    break;
                case "cycle":
                    hasCycle = true;
                    gp.item[i] = null;
                    gp.ui.showMessage("click Z to get on or off cycle");
                    break;

                case "Charizard", "Jigglypuff", "Pikachu":
                    if (playerPokemons.size() < 2) {
                        if (numBalls > 0) {
                            gp.player.playerPokemons.add(gp.item[i]);

                            gp.item[i] = null;
                            numBalls--;
                            for (Entity element : playerPokemons) {
                                gp.ui.showMessage("you got " + element.getInfo());
                            }
                        } else{
                            gp.ui.showMessage("YOU NEED A POKEBALL");
                        }
                    }else{
                        wildPokemon.add(gp.item[i]);
                        gp.item[i] = null;
                        gp.gameState = gp.battleState;
                        }break;
                    }

            }
         }
         //battle logic
         public void attack(){
            Random ran = new Random();
            int damage = ran.nextInt(30);
            int wildDamage = ran.nextInt(20);
            if (gp.player.playerPokemons.get(0).pHP >= 0 && gp.player.playerPokemons.get(1).pHP >= 0) {

                wildPokemon.get(0).pHP -= damage;
                playerPokemons.get(playerPoke).pHP -= wildDamage;
                System.out.println(wildPokemon.get(0).pHP);
            }else{
                gp.gameState  = gp.loseState;
            }

         }

        public void draw (Graphics2D g2){

            BufferedImage image = null;
            if (onCycle == false) {
                switch (direction) {
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
            } else if (onCycle == true) {
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {
                            image = upc1;
                        }
                        if (spriteNum == 2) {
                            image = upc2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = downc1;
                        }
                        if (spriteNum == 2) {
                            image = downc2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = leftc1;
                        }
                        if (spriteNum == 2) {
                            image = leftc2;
                        }
                        break;

                    case "right":
                        if (spriteNum == 1) {
                            image = rightc1;
                        }
                        if (spriteNum == 2) {
                            image = rightc2;
                        }
                        break;

                }

            }
            g2.drawImage(image, screenX, screenY, null);

        }
    }


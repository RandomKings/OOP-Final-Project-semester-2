package entity;

import game2d.GamePanel;
import game2d.scaleImages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, upc1, upc2, downc1, downc2, leftc1, leftc2, rightc1, rightc2;

    public String direction = "down";
    public int spriteCounter =0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public BufferedImage image , image1;
    public String name;
    public String description;
    public boolean collision = false;
    //pokemon details
    public int pHP;
    public int pLevel;



    public Entity(GamePanel gp){
        this.gp = gp;
    }
    //GET IMAGE
    public BufferedImage setUp(String imagePath, String imageName){
        scaleImages sImages = new scaleImages();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/"+ imagePath +"/"+ imageName+".png"));
            image = sImages.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    //GET pokemon details
    public String getInfo() {
        return "Name: " + name + ", Level: " + pLevel + ", hp :" + pHP;
    }
//draw entities items , pokemon and player
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }


    }
}

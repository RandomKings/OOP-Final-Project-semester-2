package tiles;

import game2d.GamePanel;
import game2d.scaleImages;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;

    public int[][] mapTileNum;
    public ArrayList<ArrayList<Integer>> allCords = new ArrayList<>();

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];


        getTileImage();
        loadMap("/Maps/world01.txt");
    }

    public void getTileImage() {

            setUp(0,"grass",false);
            setUp(1,"wall",true);
            setUp(2,"water",true);
            setUp(3,"earth",false);
            setUp(4,"tree",true);
            setUp(5,"sand",false);
    }
    public void setUp(int index, String imageName, boolean collision){

        scaleImages sImages = new scaleImages();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
            tile[index].image = sImages.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        //reads map from a text file and put its into an array
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    System.out.println(num);
                    //filters out grass and sand
                    if (num == 0 || num == 5) {
                        ArrayList<Integer> coor = new ArrayList<>();
                        coor.add(col);
                        coor.add(row);
                        allCords.add(coor);
                        System.out.println(col);
                        System.out.println(row);
                    }
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            System.out.println(allCords);
            br.close();


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol &&  worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

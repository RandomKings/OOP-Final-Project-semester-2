package game2d;


import entity.Entity;
import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16*16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final  int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight  = tileSize * maxScreenRow; //576 pixels

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldHeight = tileSize*maxScreenRow;
    public final int worldWidth = tileSize*maxScreenCol;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);

    public ItemSetter iSetter = new ItemSetter(this);
    public UI ui = new UI(this,keyH);

    //entity and object
    public Player player = new Player(this,keyH);
    public Entity item[] = new Entity[6];
    ArrayList<Entity> entityList = new ArrayList<>();


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int battleState = 2;
    public final int bagState = 3;
    public final int winState = 4;
    public final int loseState = 5;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        iSetter.setItem();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //GAME LOOP
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta>= 1){
                //update information
                update();
                //draw with update information
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println(drawCount);
                drawCount = 0;
                timer = 0;
            }



        }
    }
    public void update(){
        if (gameState == playState) {
            player.update();
        }
        if (gameState == battleState){
            //nothing for now
        }
        if (gameState == titleState){

        }
        if (gameState == bagState){

        }
        if (gameState == winState){

        }
        if (gameState == loseState){

        }


    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;


        //title screen
        if (gameState == titleState) {

            }

        //battle screen
        if (gameState == battleState){

        }
        if (gameState == loseState){

        }
        if (gameState == winState){

        }



        //draw playstate
        else{

            //tile
            tileM.draw(g2);

            //add entity to the list

            //Items
            for(int i = 0; i< item.length;i++){
                if (item[i] != null){
                    entityList.add(item[i]);
                }
            }
            entityList.add(player);

            //draw entities
            for (int i = 0; i< entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            // empty entity list
            for (int i =0; i < entityList.size();i++){
                entityList.remove(i);
            }
        }
        //ui
        ui.draw(g2);


        g2.dispose();
    }
}

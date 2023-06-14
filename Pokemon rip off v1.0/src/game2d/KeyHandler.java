package game2d;


import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;


public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, leftPressed, downPressed, rightPressed, zPressed, enterPressed, pPresseed, aPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        //Battle state
        //in key released

        //win state
        if(gp.gameState == gp.winState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        //bag state
        if (gp.gameState == gp.bagState) {

                if (code == KeyEvent.VK_W) {
                    if (gp.ui.spotRow != 0) {
                        gp.ui.spotRow--;
                    }
                }
                if (code == KeyEvent.VK_A) {
                    if (gp.ui.spotCol != 0) {
                        gp.ui.spotCol--;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    if (gp.ui.spotRow != 3) {
                        gp.ui.spotRow++;
                    }
                }
                if (code == KeyEvent.VK_D) {
                    if (gp.ui.spotCol != 4) {
                        gp.ui.spotCol++;
                    }
                }
                if (code == KeyEvent.VK_P){
                    gp.gameState = gp.playState;
                }
            }

        //play state
            else{
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P){
            gp.gameState = gp.bagState;
        }

    }
}

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }

        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_S){
            downPressed = false;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_Z){
            zPressed = !zPressed;
        }

        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if (gp.gameState == gp.bagState) {
            if (code == KeyEvent.VK_P) {
                pPresseed = false;
            }
        }
        if (gp.gameState == gp.battleState){
            if (gp.player.wildPokemon.get(0).pHP > 0) {
                if (code == KeyEvent.VK_A) {
                    gp.player.attack();
                    if (gp.player.playerPokemons.get(gp.player.playerPoke).pHP <= 0){
                        gp.player.playerPoke++;
                    }
                }
            }else {
                gp.gameState = gp.winState;
            }
            if (code == KeyEvent.VK_S){
                if (gp.player.playerPoke == 0) {
                    gp.player.playerPoke++;
                }else{
                    gp.player.playerPoke = 0;
                }
            }
            if (code == KeyEvent.VK_R){
                gp.gameState = gp.playState;
            }
        }
    }
}


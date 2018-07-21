package com.danewbiecoder.game;

import com.danewbiecoder.engine.AbstractGame;
import com.danewbiecoder.engine.GameContainer;
import com.danewbiecoder.engine.Render;
import com.danewbiecoder.engine.gfx.Image;
import com.danewbiecoder.engine.gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    private Image scope;
    private Image background;
    private ImageTile tileOne;
    private ImageTile megaMan;
    private boolean blink;


    public GameManager() {
        scope = new Image("/scope.png");
        background = new Image("/background.png");
        tileOne = new ImageTile("/tileset.png", 16, 16);
        megaMan = new ImageTile("/megaman.png", 32, 32);
    }

    @Override
    public void update(GameContainer gameContainer, float delta) {
        String facing;
        if (gameContainer.getInput().isKeyDown(KeyEvent.VK_LEFT)) {
            System.out.println("Left was pressed");
            facing = "left";
        } else if (gameContainer.getInput().isKeyDown(KeyEvent.VK_RIGHT)) {
            System.out.println("Right was pressed");
            facing = "right";
        }
        frame += delta * 15;
        if (frame >= 30) {
            System.out.println("Blinked!");
            blink = true;
            frame = 0;
        } else if (frame == 0) {
            blink =  false;
        }

    }
    float frame = 0;
    @Override
    public void render(GameContainer gameContainer, Render render) {
//        render.drawImage(scope, gameContainer.getInput().getMouseX()-31, gameContainer.getInput().getMouseY()-31);
        render.drawImageTile(tileOne, gameContainer.getInput().getMouseX() - 9, gameContainer.getInput().getMouseY() - 9, (int) frame, 0);
        System.out.println("Frame: " + (int)frame);
        if (gameContainer.getInput().isKey(KeyEvent.VK_LEFT)) {
            //stand left
            System.out.println("Youre1 moving left.");
        } else if (gameContainer.getInput().isKeyDown(KeyEvent.VK_RIGHT)) {
            //animate right
            System.out.println("Youre moving right.");
        } else if (gameContainer.getInput().isKey(KeyEvent.VK_RIGHT)) {
            //stand right
            System.out.println("Youre standing right");
        }
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}

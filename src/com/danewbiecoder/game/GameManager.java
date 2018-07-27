package com.danewbiecoder.game;

import com.danewbiecoder.engine.AbstractGame;
import com.danewbiecoder.engine.GameContainer;
import com.danewbiecoder.engine.Render;
import com.danewbiecoder.engine.audio.SoundClip;
import com.danewbiecoder.engine.gfx.Image;
import com.danewbiecoder.engine.gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    private Image scope;
    private Image background;
    private ImageTile tileOne;
    private ImageTile megaMan;
    private int blink;
    private SoundClip hail;


    public GameManager() {
        scope = new Image("/scope.png");
        background = new Image("/background.png");
        tileOne = new ImageTile("/tileset.png", 16, 16);
        megaMan = new ImageTile("/megaman.png", 32, 32);
        hail = new SoundClip("/audio/hail.wav");
    }

    @Override
    public void update(GameContainer gameContainer, float delta) {
        frame += delta * 15;
        if (frame == 60) {
            blink = 1;
            System.out.println("Blinked");
            frame = 0;
        } else if (frame == 1) {
            blink = 0;
        }
    }

    float frame = 0;

    @Override
    public void render(GameContainer gameContainer, Render render) {
        render.drawImage(background, 0, 0);

        render.drawImageTile(megaMan, 112, 152, 0, blink);

    }

    public static void main(String[] args) {
        GameContainer gameContainer = new GameContainer(new GameManager());
        gameContainer.setWidth(256);
        gameContainer.setHeight(224);
        gameContainer.setScale(3);
        gameContainer.start();
    }
}

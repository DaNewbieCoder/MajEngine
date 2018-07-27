package com.danewbiecoder.engine;


import java.awt.*;

public class GameContainer implements Runnable {
    private Thread thread;
    private GameWindow gameWindow;
    private Render renderer;
    private Input input;
    private AbstractGame game;
    private boolean running = false;

    private final double UPDATE_CAP = 1.0 / 60.0d;
    private int width = 320, height = 240;
    private float scale = 2;
    private String title = "MajGame Engine 1.0";

    public GameContainer(AbstractGame game) {
        this.game = game;

    }

    public void start() {
        gameWindow = new GameWindow(this);
        input = new Input(this);
        renderer = new Render(this);
        thread = new Thread(this);
        thread.run();
    }

    public void stop() {

    }

    public void run() {
        running = true;
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1E9;
        double passedTime = 0;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;
            firstTime = System.nanoTime() / 1E9;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;
            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                game.update(this, (float) UPDATE_CAP);
                input.update();

                if (frameTime >= 1) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }
            }
            if (render) {
                renderer.clear();
                game.render(this, renderer);
                renderer.drawText("FPS: " + fps, 0, 0, 0xFF00FFFF);
                renderer.drawText("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG.", 0, 100, -1);
                renderer.drawText("1234567890", 0, 111, -1);
                renderer.drawText("Bully the quick brown fox jumps over the lazy dog.", 0, 122, -1);
                renderer.drawText("!@#$%^&*()_+{}[]-=;':<>,.?/ ducking!", 0, 133, -1);
                renderer.drawFilledRect(50, 50, 19, 29, -1);
                renderer.drawRect(50, 50, 19, 29, 255);
                gameWindow.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose() {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public Input getInput() {
        return input;
    }
}

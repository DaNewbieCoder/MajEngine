package com.danewbiecoder.engine;


import com.danewbiecoder.engine.gfx.Image;
import com.danewbiecoder.engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;

public class Render {
    private int pixelWidth, pixelHeight;
    private int[] pixels;

    public Render(GameContainer gameContainer) {
        pixelWidth = gameContainer.getWidth();
        pixelHeight = gameContainer.getHeight();
        pixels = ((DataBufferInt) gameContainer.getGameWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void cycleColors() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] += i;
        }
    }

    public void setPixels(int x, int y, int value) {
        if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffa6a6a6) {
            return;
        }
        pixels[x + y * pixelWidth] = value;
    }
    public void drawImageTile(ImageTile image, int offsetX, int offsetY, int tileX, int tileY) {
        if (offsetX < -image.getTileWidth()) {return;}
        if (offsetX >= pixelWidth) {return;}
        if (offsetY < -image.getTileHeight()) {return;}
        if (offsetY >= pixelHeight) {return;}

        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileWidth();
        int newHeight = image.getTileHeight();

        if (newX + offsetX < 0) {
            newX -= offsetX;
        }
        if (newY + offsetY < 0) {
            newY -= offsetY;
        }
        if (newWidth + offsetX > pixelWidth) {
            newWidth -= newWidth + offsetX - pixelWidth;
        }
        if (newHeight + offsetY > pixelHeight) {
            newHeight -= newHeight + offsetY - pixelHeight;
        }
        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixels(x + offsetX, y + offsetY, image.getPixels()[(x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight()) * image.getWidth()]);
            }
        }
    }


    public void drawImage(Image image, int offsetX, int offsetY) {

        //Don't render code
        if (offsetX < -image.getWidth()) {return;}
        if (offsetX >= pixelWidth) {return;}
        if (offsetY < -image.getHeight()) {return;}
        if (offsetY >= pixelHeight) {return;}
        int newX = 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();


        //clipping code
        if (offsetX < 0) {
            newX -= offsetX;
        }
        if (offsetY < 0) {
            newY -= offsetY;
        }
        if (newWidth + offsetX >= pixelWidth) {
            newWidth -= newWidth + offsetX - pixelWidth;
        }
        if (newHeight + offsetY >= pixelHeight) {
            newHeight -= newHeight + offsetY - pixelHeight;
        }
        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixels(x + offsetX, y + offsetY, image.getPixels()[x + y * image.getWidth()]);
            }
        }

    }
}

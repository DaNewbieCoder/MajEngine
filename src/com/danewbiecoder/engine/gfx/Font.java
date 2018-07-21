package com.danewbiecoder.engine.gfx;

public class Font {
    private Image fontImage;
    private int[] offsets;
    private int[] width;

    public Font(String path) {
        fontImage = new Image(path);

    }
}

package com.danewbiecoder.engine.gfx;

public class Font {
    public static final Font STANDARD = new Font("/fonts/standardfont.png");
    private Image fontImage;
    private int[] offset;
    private int[] width;

    public Font(String path) {
        fontImage = new Image(path);
        offset = new int[59];
        width = new int[59];

        int unicode = 0;
        for (int i = 0; i < fontImage.getWidth() ; i++) {
            if (fontImage.getPixels()[i] == 0xFF0000FF) {
                offset[unicode] = i;
            }
            if (fontImage.getPixels()[i] == 0xFFFFFF00) {
                width[unicode] = i - offset[unicode];
                unicode++;
            }
        }
    }

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getOffset() {
        return offset;
    }

    public int[] getWidth() {
        return width;
    }
}

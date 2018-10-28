package com.pinakpani.graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("res/textures/spritesheet.png",256);

    public SpriteSheet(String path, int size){
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load();
    }
    private void load(){
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            int w = image.getWidth();
            int h = image.getHeight();
            //loading image into pixels
            image.getRGB(0,0,w,h,pixels,0,w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.pinakpani.graphics;

import static com.pinakpani.graphics.SpriteSheet.tiles;

public class Sprite {
    public final int SIZE;
    private int x,y;
    public int[] pixels;
    private SpriteSheet sheet;

    //creating my sprite
    public static Sprite grass = new Sprite(16,0,0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16,0x339BF5);
    public static Sprite player_forward = new Sprite(32,0,5,SpriteSheet.tiles);
    public static Sprite player_backward = new Sprite(32,2,5,SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(32,1,5,SpriteSheet.tiles);

    public static Sprite player_forward_1 = new Sprite(32,0,6,SpriteSheet.tiles);
    public static Sprite player_backward_1 = new Sprite(32,2,6,SpriteSheet.tiles);
    public static Sprite player_side_1 = new Sprite(32,1,6,SpriteSheet.tiles);

    public static Sprite player_forward_2 = new Sprite(32,0,7,SpriteSheet.tiles);
    public static Sprite player_backward_2 = new Sprite(32,2,7,SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32,1,7,SpriteSheet.tiles);




    //constructor method
    public Sprite(int size,int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.x = x*size;
        this.y = y*size;
        this.sheet = sheet;
        load();
    }
    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for(int i=0;i<SIZE*SIZE;i++){
            pixels[i] = color;
        }
    }

    private void load(){
            for (int y = 0; y<SIZE; y++){
                for(int x=0;x<SIZE;x++){
                    pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
                }
            }
    }
}

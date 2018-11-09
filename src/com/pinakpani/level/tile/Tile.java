package com.pinakpani.level.tile;

import com.pinakpani.graphics.Screen;
import com.pinakpani.graphics.Sprite;

public class Tile {
    public int x,y;
    public Sprite sprite;

    //making a tile
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){
    }
    public boolean solid(){
        return false;
    }
}

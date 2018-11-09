package com.pinakpani.level.tile;
import com.pinakpani.graphics.Screen;

import com.pinakpani.graphics.Sprite;

public class VoidTile extends Tile {
    public VoidTile(Sprite sprite) {
        super(sprite);
        }
        public void render(int x, int y, Screen screen){
            //You have to write your render code here
            screen.renderTile(x << 4, y << 4, this);

        }

}

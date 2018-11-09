package com.pinakpani.entity;

import com.pinakpani.graphics.Screen;
import com.pinakpani.level.Level;

import java.util.Random;

public class Entity {
    public int x,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){

    }
    public void render(Screen screen){

    }
    public void remove(){
        //to remove from level
        removed = true;
    }
    public boolean isRemoved(){
        return removed;
    }

}

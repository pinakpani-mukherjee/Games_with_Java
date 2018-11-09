package com.pinakpani.entity.mob;

import com.pinakpani.entity.Entity;
import com.pinakpani.graphics.Sprite;

public abstract class Mob extends Entity {
    protected Sprite sprite;
    //zero is usually north for direction
    protected  int dir = 0;
    protected boolean moving = false;

    protected void move(){

    }
    public void update(){

    }
    private boolean collision(){
        return false;
    }
}

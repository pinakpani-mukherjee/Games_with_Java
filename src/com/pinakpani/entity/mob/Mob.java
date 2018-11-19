package com.pinakpani.entity.mob;

import com.pinakpani.entity.Entity;
import com.pinakpani.graphics.Sprite;

public abstract class Mob extends Entity {
    protected Sprite sprite;
    //zero is usually north for direction
    protected  int dir = 0;
    protected boolean moving = false;

    protected void move(int xa, int ya){
        if(xa > 0 ) dir = 1;
        if(xa < 0 ) dir = 3;
        if(ya > 0 ) dir = 2;
        if(ya < 0 ) dir = 0;
        if(!collision()){
            x += xa;
            y += ya;
        }



    }
    public void update(){
    }
    private boolean collision(){
        return false;
    }
    public void render(){
    }
}

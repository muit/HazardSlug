/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.data.DataBase;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.Sprite;

/**
 *
 * @author Miguel
 */
public class Item extends GameObject {
    
    protected String name;
    protected static DataBase db = new DataBase();
    protected float gSpeed = 0;
    protected int id;
    
    public void pickUp()
    {
        
    }
    
    protected void init(String name, float x, float y, float r, float g, float b, float sx,float sy)
    {
        this.name = name;
        this.type = 1;
        this.x = x;
        this.y = y;
        this.spr = new Sprite(r,g,b,sx,sy);
    }
    
    public void update()
    {
    }
    
    protected void fisic(float magy)
    {
        y+=getGSpeed()*magy;
    }
    protected float getGSpeed()
    {
        if(gSpeed<5)
            gSpeed+=db.getItemGravity(id);
        return gSpeed;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.data.DataBase;
import com.base.engine.GameObject;
import com.base.engine.Physics;
import com.base.engine.ItemSprite;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Miguel
 */
public class Item extends GameObject {
    
    protected String name;
    protected static DataBase db = new DataBase();
    protected float gSpeed = 0;
    protected int id;
    protected ItemSprite spr;
    
    public void pickUp()
    {
        
    }
    
    protected void init(int id, float x, float y, float r, float g, float b, float sx,float sy)
    {
        this.id = id;
        this.name = db.getItemName(id);
        this.type = 3;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        spr = new ItemSprite(id,sx,sy);
    }
    
    @Override
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    
    protected void fisic()
    {
        y-=getGSpeed()/16;
    }
    
    protected float getGSpeed()
    {
        if(gSpeed<5)
            gSpeed+=db.getItemGravity(id);
        else 
            gSpeed = 5;
            
        return gSpeed;
    }

    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public float getSX()
    {
        return spr.getSX();
    }
    public float getSY()
    {
        return spr.getSY();
    }
}

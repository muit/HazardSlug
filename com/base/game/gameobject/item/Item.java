/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.data.DataBase;
import com.base.engine.GameObject;
import com.base.engine.ItemSprite;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;

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
        vectors = new Vector2f[] {
            new Vector2f(x, y),
            new Vector2f(x, y + sy),
            new Vector2f(x + sx, y + sy),
            new Vector2f(x + sx, y)
        };
        spr = new ItemSprite(id,sx,sy);
    }
    
    @Override
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(getX()*16, getY()*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    
    protected void fisic()
    {
        setY(getY()-getGSpeed()/16);
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

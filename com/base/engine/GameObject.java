/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Miguel
 */
public abstract class GameObject 
{
    protected float x,y;
    protected int type = 0;
    protected Sprite spr;
    protected boolean remove = false;
    
    public void update()
    {
        
    }
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public float getSX()
    {
        return spr.getSX();
    }
    public float getSY()
    {
        return spr.getSY();
    }
    
    public int getType()
    {
        return type;
    }
    
    public boolean getRemove()
    {
        return remove;
    }
    
    protected void init(int type, float x, float y, float r, float g, float b, float sx,float sy)
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.spr = new Sprite(r,g,b,sx,sy);
    }
}

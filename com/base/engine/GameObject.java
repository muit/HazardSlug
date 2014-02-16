/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author Miguel
 */
public abstract class GameObject 
{
    protected float x, y, sx, sy;
    protected int type = 0;
    protected boolean remove = false;
    
    public void update()
    {
        
    }
    public void render()
    {
    }
    
    public float getSX()
    {
        return sx;
    }
    public float getSY()
    {
        return sy;
    }
    public float getWidth()
    {
        return sx;
    }
    public float getHeight()
    {
        return sy;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    
    public int getType()
    {
        return type;
    }
    
    public boolean getRemove()
    {
        return remove;
    }
    
    protected void init(int type, float x, float y, float sx,float sy)
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }
}

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
public class Sprite 
{
    private float r,g,b;
    private float sx,sy;    
    
    public Sprite(float r, float g, float b, float sx, float sy)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sx = sx;
        this.sy = sy;
    }
    
    
    public void render()
    {
        glColor3f(r,g,b);
        glBegin(GL_QUADS);
        {
            glVertex2f(0,0);
            glVertex2f(0,sy);
            glVertex2f(sx,sy);
            glVertex2f(sx,0);
        }
        glEnd();
    }
    public float getSX()
    {
        return sx;
    }
    public float getSY()
    {
        return sy;
    }
    public void setSX(float sx)
    {
        this.sx = sx;
    }
    public void setSY(float sy)
    {
        this.sy = sy;
    }
}

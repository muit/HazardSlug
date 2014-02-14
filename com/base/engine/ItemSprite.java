/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.data.DataBase;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
/**
 *
 * @author Miguel
 */
public class ItemSprite 
{
    private float sx,sy;
    private int frame;
    private int id;
    
    public ItemSprite(int id, float sx, float sy)
    {
        this.sx = sx;
        this.sy = sy;
        this.id = id;
        frame = 1;
    }
    
    public void update()
    {
        //eventos para frames
    }
    
    public void render()
    {
        DataBase db = new DataBase();
        Color.white.bind();
	db.getItemTexture(id).bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0.25f*frame,1);
            glVertex2f(0,0);
            glTexCoord2f(0,1);
            glVertex2f(sx,0);
            glTexCoord2f(0,0);
            glVertex2f(sx,sy);
            glTexCoord2f(0.25f*frame,0);
            glVertex2f(0,sy);
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

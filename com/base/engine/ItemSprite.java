/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Texture tex;
    private float sx,sy;
    
    public ItemSprite(int id, float sx, float sy)
    {
        this.sx = sx;
        this.sy = sy;
        loadTexture(id);
    }
    
    
    public void render()
    {
        Color.white.bind();
	tex.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2f(0, 0);
            glTexCoord2f(0.25f,0);
            glVertex2f(sx, 0);
            glTexCoord2f(0.25f,1);
            glVertex2f(sx,sy);
            glTexCoord2f(0,1);
            glVertex2f(0, sy);
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
    private void loadTexture(int id)
    {
        String path = "com/resources/tileset/"+id+".bmp";
        try {
            tex = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream(path));
        } catch (Exception ex) {
            System.out.println("Textura: "+id+" no se pudo cargar.");
        }
    }
}

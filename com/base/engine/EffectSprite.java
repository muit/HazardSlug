/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Miguel
 */
public class EffectSprite 
{
    private Texture tex;
    private float sx,sy;
    private final int id;
    private int frame;
    
    public EffectSprite(int id)
    {
        this.id = id;
        this.sx = 16;
        this.sy = 16;
        tex = null;
        loadTexture(id);
    }
    
    public void update()
    {
        //eventos para frames
    }
    
    public void render()
    {
        Color.white.bind();
	tex.bind();
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
    
    private void loadTexture(int id)
    {
        String path = "com/resources/effect/"+id+".bmp";
        try {
            tex = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println("Textura: "+id+" no se pudo cargar.");
        }
    }
    public void nextFrame()
    {
        frame++;
    }
    public void lastFrame()
    {
        frame--;
    }
}

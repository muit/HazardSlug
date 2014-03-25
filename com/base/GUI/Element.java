package com.base.GUI;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.base.engine.Main;


public class Element {
    protected float x, y, sx, sy;
    protected Texture tex;
    protected int frame;

    protected Menu menu;
    protected GUI gui;

    public Element(float x, float y, float sx, float sy)
    {
            this.x = x;
            this.y = y;
            this.sx = sx;
            this.sy = sy;
            frame = 0;
            gui = null;
            menu = null;
    }

    protected void loadTexture(String name)
    {
            tex = getTexture(name);
    }

    public Texture getTexture(String name)
    {
        if(tex == null)
        {
            String path = "com/resources/gui/"+name+".png";
            try {
                tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            } catch (IOException ex) {
                System.out.println("Textura: "+name+" no se pudo cargar.");
                Main.heavyClose();
                
            }
            if(tex == null)
            {
                System.out.println("Textura: "+name+" no se pudo cargar.");
                Main.heavyClose();
            }
        }
        
        sx = tex.getWidth();
	sy = tex.getHeight();
		
        return tex;
    }
	
    protected void update()
    {

    }

    protected void render()
    {
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
        return sx;
    }
    public float getSY()
    {
        return sy;
    }

    protected void setFrame(int frame)
    {
        this.frame = frame;
    }
    protected void doAction()
    {

    }
}

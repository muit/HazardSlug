package com.base.GUI;


import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.base.engine.Main;


public class Element {
    protected float x, y, sx, sy;
    protected Texture tex;
    protected int frame;

    protected Menu menu = null;
    protected GUI gui = null;

    public Element(float x, float y, Menu menu)
    {
            this.x = x;
            this.y = y;
            frame = 0;
            this.gui = null;
            this.menu = menu;
    }
    
    public Element(float x, float y, GUI gui)
    {
            this.x = x;
            this.y = y;
            frame = 0;
            this.gui = gui;
            this.menu = null;
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
        /*
        sx = tex.getWidth();
	sy = tex.getHeight();
        */
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
    
    public void setSX(float sx)
    {
        this.sx = sx;
    }
    public void setSY(float sy)
    {
        this.sy = sy;
    }
    protected void setFrame(int frame)
    {
        this.frame = frame;
    }
    protected void doAction(String action)
    {

    }
}

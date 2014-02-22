/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

import com.base.engine.Main;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Miguel
 */
public class BaseItem {
    private int id;
    private String name;
    private float gravity;
    private float heavy;
    private Texture tex;
    
    public BaseItem(int id, String name, float gravity, float heavy)
    {
        this.id = id;
        this.name = name;
        this.gravity = gravity;
        this.heavy = heavy;
        loadTexture();
    }
    public BaseItem(int id, String name, double gravity, float heavy)
    {
        this.id = id;
        this.name = name;
        this.gravity = (float)gravity;
        this.heavy = heavy;
        loadTexture();
    }
    
    private void loadTexture()
    {
        String path = "com/resources/tileset/"+id+".png";
        try {
                tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            } catch (RuntimeException ex) {
                System.out.println("Textura: "+ex);
                Main.heavyClose();   
            } catch (IOException ex) {
                System.out.println("Textura(2): "+id+" no se pudo cargar.");
                Main.heavyClose();
            }
    
            if(tex == null)
            {
                System.out.println("Textura(3): "+id+" no se pudo cargar.");
                Main.heavyClose();
            }
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getGravity()
    {
        return gravity;
    }
    
    public float getHeavy()
    {
        return heavy;
    }
    public Texture getTexture()
    {
        return tex;
    }
}

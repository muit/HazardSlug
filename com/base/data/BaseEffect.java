/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Miguel_F
 */
public class BaseEffect {
    private int id;
    private Texture tex = null;
    
    public BaseEffect(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public Texture getTexture()
    {
        if(tex == null)
        {
            String path = "com/resources/effect/"+id+".png";
            try {
                tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            } catch (IOException ex) {
                System.out.println("Textura: "+id+" no se pudo cargar.");
                Main.Close();
                
            }
            if(tex == null)
            {
                System.out.println("Textura: "+id+" no se pudo cargar.");
                Main.Close();
            }
        }
        return tex;
    }
}

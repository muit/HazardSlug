/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

/**
 *
 * @author Miguel_F
 */
public class Map {
    private Chunk chunks[];
    private GeneratorMidpoint mp = new GeneratorMidpoint();
    public Map(int Size)
    {
        mp.generate(Size);
    }
    
    public void load()
    {
        
    }
    public void save()
    {
        
    }
    public void generateChunk()
    {
        
    }
    
    public void update()
    {
        /*
        for (Chunk chunk : chunks) 
        {
            if(chunk!=null)
                chunk.update();
        }
        */
    }
    public void render()
    {
        /*
        for (Chunk chunk : chunks) {
            if(chunk!=null)
                chunk.render();
        }
        */
    }
}

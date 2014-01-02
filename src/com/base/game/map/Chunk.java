/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import java.util.ArrayList;

/**
 *
 * @author Miguel_F
 */
public class Chunk {
    private final int chunkSizeX;
    private final int chunkSizeY;
    private Block cubes[][];
    
    public Chunk()
    {
        this.chunkSizeX = 32;
        this.chunkSizeY = 200;
        cubes = new Block[chunkSizeX][chunkSizeY];
    }
    public void update()
    {
        
    }
    public void render()
    {
        for (Block[] cubex : cubes) //X
        {
            for (Block cube : cubex) //Y
            {
                cube.render();
            }
        }
    }
    public Block[] getXLineWhereY(int y)
    {
        Block[] blocks = new Block[cubes.length];
        for(int i = 0; i<cubes.length; i++)
        {
            blocks[i] = cubes[i][y];
        }
        return blocks;
    }
    public Block[] getYLineWhereX(int x)
    {
        return cubes[x];
    }
}

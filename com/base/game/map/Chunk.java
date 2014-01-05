/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.game.Game;
import java.util.ArrayList;

/**
 *
 * @author Miguel_F
 */
public class Chunk {
    private final int chunkSizeX;
    private final int chunkSizeY;
    private Block cubes[][];
    private Game game;
    
    public Chunk()
    {
        this.chunkSizeX = 32;
        this.chunkSizeY = 200;
        cubes = new Block[chunkSizeX][chunkSizeY];
    }
    public Chunk(Block cubes[][])
    {
        this.chunkSizeX = 32;
        this.chunkSizeY = 200;
        this.cubes = cubes;
    }
    public void update()
    {
        for(int x = 0; x<cubes.length; x++)
        {
            for(int y = 0; y<cubes.length;y++)
            { 
                if(cubes[x][y]!=null)
                    if(cubes[x][y].getId()!=0)
                    {
                        cubes[x][y].update();
                    }
            }
        }
    }
    public void render()
    {
        for (Block[] cubex : cubes) //X
        {
            for (Block cube : cubex) //Y
            {
                if(cube!=null)
                    cube.render();
            }
        }
    }
    public Block[][] getBlocks()
    {
        return cubes;
    }
    public void setBlock(int x, int y, int id)
    {
        cubes[x][y].setId(id);
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

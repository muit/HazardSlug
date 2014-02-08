/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.game.Game;
import com.base.game.Util;
import com.base.game.gameobject.Player;

/**
 *
 * @author Miguel_F
 */
public class Chunk {
    private final int chunkSizeX;
    private final int chunkSizeY;
    private final Block cubes[][];
    private boolean initialiced=false;
    private int firstPoint=0;
    
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
    public void update(Player player)
    {
        for (Block[] cubex : cubes) //X
            for (Block cube : cubex) //Y{
                if (cube != null && cube.getId() != 0) 
                    cube.update(player);
    }
    public void render()
    {
        for (Block[] cubex : cubes) //X
            for (Block cube : cubex) //Y
                if(cube!=null && cube.getId() != 0)
                    cube.render();
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
    
    public void generate(Map map, int seed, int id, int maxid)
    {
        initialiced = true;
        int space = map.getMaxAlt()-map.getFloorAlt();
        
        float[] point = new float[33];
        
        point[0] = generateFpoint(seed, id, space, maxid);
        point[32] = generateFpoint(seed, id+1, space, maxid);
        
        
        point[16] = genPoint(point[0],  point[32], space, 2);
        
        point[8]  = genPoint(point[0],  point[16], space, 4);
        point[24] = genPoint(point[16], point[32], space, 4);
        
        point[4]  = genPoint(point[0],  point[8],  space, 8);
        point[12] = genPoint(point[8],  point[16], space, 8);
        point[20] = genPoint(point[16], point[24], space, 8);
        point[28] = genPoint(point[24], point[32], space, 8);
        
        point[2]  = genPoint(point[0],  point[4],  space, 16);
        point[6]  = genPoint(point[4],  point[8],  space, 16);
        point[10] = genPoint(point[8],  point[12], space, 16);
        point[14] = genPoint(point[12], point[16], space, 16);
        point[18] = genPoint(point[16], point[20], space, 16);
        point[22] = genPoint(point[20], point[24], space, 16);
        point[26] = genPoint(point[24], point[28], space, 16);
        point[30] = genPoint(point[28], point[32], space, 16);
        
        
        point[1]  = genPoint(point[0],  point[2],  space, 32);
        point[3]  = genPoint(point[2],  point[4],  space, 32);
        point[5]  = genPoint(point[4],  point[6],  space, 32);
        point[7]  = genPoint(point[6],  point[8],  space, 32);
        point[9]  = genPoint(point[8],  point[10], space, 32);
        point[11] = genPoint(point[10], point[12], space, 32);
        point[13] = genPoint(point[12], point[14], space, 32);
        point[15] = genPoint(point[14], point[16], space, 32);
        point[17] = genPoint(point[16], point[18], space, 32);
        point[19] = genPoint(point[18], point[20], space, 32);
        point[21] = genPoint(point[20], point[22], space, 32);
        point[23] = genPoint(point[22], point[24], space, 32);
        point[25] = genPoint(point[24], point[26], space, 32);
        point[27] = genPoint(point[26], point[28], space, 32);
        point[29] = genPoint(point[28], point[30], space, 32);
        point[31] = genPoint(point[30], point[32], space, 32);
        
        for(int i=0; i<32;i++)
            cubes[i][(int)point[i]] = new Block(i+(id-maxid/2)*32, (int)point[i]+map.getFloorAlt(), 4);
    }
    public boolean getInit()
    {
        return initialiced;
    }
    
    public int getFirstPoint()
    {
        return firstPoint;
    }
    
    public void setFirstPoint(int firstPoint)
    {
        this.firstPoint = firstPoint;
    }
    
    private float generateFpoint(int cifSeed, int id, int space, int maxid)
    {
        //implementar variacion por id******************************************
        //mejorar el cifrado de id para randomizarlo
        //(cifSeed/999999999*space)...
        double pointer = (double)Util.encriptChunkId(id)/maxid*space;
        return (float)pointer;
    }
    
    private float genPoint(float y1, float y2, int multiplier, int space)
    {
        float C = (y1 + y2) / 2 + (float)(Math.random()*space /multiplier);
        return C;
    }
    
    public int getChunkSizeX()
    {
        return chunkSizeX;
    }
    
    public int getChunkSizeY()
    {
        return chunkSizeY;
    }
}

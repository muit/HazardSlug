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
    private int ChunkMapSize;
    private int maxAlt = 240;
    private int floorAlt = 200;
    private GeneratorMidpoint mp = new GeneratorMidpoint();
    public Map(int Size)
    {
        ChunkMapSize = 1024*(int)Math.pow(2,Size)+1024;;
        chunks = new Chunk[ChunkMapSize];
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
    
    public void update(float playerx, float playerWidth)
    {
        for(int i =(int) ((playerx + 0.5 - playerWidth/16/2) /32 + ChunkMapSize/2-1); i<=(int) ((playerx + 0.5 + playerWidth/16/2) /32 + ChunkMapSize/2+1);i++)
            if(chunks[i]!=null && chunks[i].getInit())
            {
                chunks[i].update();
            }
            else
            {
                if(chunks[i]==null)
                    chunks[i] = new Chunk();
                chunks[i].generate(this, 645376435, i, ChunkMapSize);
            }
    }
    public void render(float playerx, float playerWidth)
    {
        for(int i =(int) ((playerx + 0.5 - playerWidth/16/2) /32 + ChunkMapSize/2-1); i<=(int) ((playerx + 0.5 + playerWidth/16/2) /32 + ChunkMapSize/2+1);i++)
            if(chunks[i]!=null && chunks[i].getInit())
                chunks[i].render();
    }
    public void generateChunk(int chunkId)
    {
        
    }
    public Chunk getChunk(int id)
    {
        return chunks[id];
    }
    public int getMaxAlt()
    {
        return maxAlt;
    }
    public int getFloorAlt()
    {
        return floorAlt;
    }
}


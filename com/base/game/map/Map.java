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
    
    public void update(float camx, float camWidth)
    {
        for(int i =Math.round(camx/36)+ChunkMapSize/2; i<=Math.round(camx/36)+Math.round(camWidth/16/32)+ChunkMapSize/2;i++)
            if(chunks[i]!=null)
                chunks[i].update();
            else
                chunks[i] = new Chunk();
    }
    public void render(float camx, float camWidth)
    {
        for(int i =Math.round(camx/36)+ChunkMapSize/2; i<=Math.round(camx/36)+Math.round(camWidth/16/32)+ChunkMapSize/2;i++)
            if(chunks[i]!=null)
                chunks[i].render();
    }
    public void generateChunk(int chunkId)
    {
        
    }
}

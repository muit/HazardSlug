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
public class Map {
    private Chunk chunks[];
    private int ChunkMapSize;
    private int maxAlt = 240;
    private int floorAlt = 200;
    private Game game;
    private ArrayList<Chunk> chunkIdsLoaded = new ArrayList<>();
    
    public Map(int Size, Game game)
    {
        ChunkMapSize = 1024*(int)Math.pow(2,Size)+1024;
        chunks = new Chunk[ChunkMapSize];
        this.game = game;
    }
    
    public void load()
    {
        
    }
    public void save()
    {
        
    }
    
    public void update(float playerx, float playerWidth)
    {
        chunkIdsLoaded.clear();
        game.getPlayer().setMapColision(false);
        for(int i =(int) ((playerx + 0.5 - playerWidth/32) /32 + ChunkMapSize/2-1); i<=(int) ((playerx + 0.5 + playerWidth/32) /32 + ChunkMapSize/2+1);i++)
            if(chunks[i]!=null && chunks[i].getInit())
            {
                chunkIdsLoaded.add(chunks[i]);
                chunks[i].update(game.getPlayer());
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
        for(int i =0; i < chunkIdsLoaded.size(); i++)
            chunkIdsLoaded.get(i).render();
    }
    
    public Chunk getChunk(int id)
    {
        return chunks[id];
    }
    
    public Chunk[] getLoadedChunks()
    {
        return (Chunk[])chunkIdsLoaded.toArray();
    }
    
    public int getMaxAlt()
    {
        return maxAlt;
    }
    
    public int getFloorAlt()
    {
        return floorAlt;
    }
    //**Acabar***********************************************************************
    public ArrayList<Block> sphereMapCollide(float x, float y, float radius)
    {
    	for(int i =0; i < chunkIdsLoaded.size(); i++)
    	{
    		
    		
    	}
        return null;
    }
}


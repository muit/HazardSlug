/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.engine.Main;
import com.base.game.Game;
import com.base.game.Util;
import com.base.game.gameobject.npc.Babosa_Azul;
import java.io.Serializable;

import java.util.ArrayList;
import javax.swing.text.html.parser.Entity;

/**
 *
 * @author Miguel_F
 */
public class Map implements Serializable {
    private final Chunk chunks[];
    private final int ChunkMapSize;
    private final int maxAlt = 240;
    private final int floorAlt = 200;
    private final Game game;
    private final ArrayList<Chunk> chunkIdsLoaded = new ArrayList<>();
    
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
                
                for(int e = 0; e < Util.random(0, 3); e++){
                    int xSelected = Util.random(e*chunks[i].getChunkSizeX(), (e+1)*chunks[i].getChunkSizeX());
                    Main.getGame().addEntity(
                        new Babosa_Azul(xSelected, 245/*chunks[i].getFirstBlockIn(xSelected)*/, 1)
                    );
                }
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
    	ArrayList<Block> allBlocks = new ArrayList<>();
    	
    	for(int i =0; i < chunkIdsLoaded.size(); i++)
    	{
    		ArrayList<Block> chunk = new ArrayList<>();
    		
    		chunk = chunkIdsLoaded.get(i).sphereMapCollide(x, y, radius);
    		for(int e = 0; e < chunk.size(); e++)
    		{
    			allBlocks.add(chunk.get(e));
    		}
    		chunk = null;
    	}
    	ArrayList<Block> res = new ArrayList<>();
        for(Block bl : allBlocks)
        {
            if(bl!=null && Util.dist(bl.getX(), bl.getY(), x, y) <= radius)
                res.add(bl);
        }
        return res;
    }
}


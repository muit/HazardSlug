/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Game;
import com.base.game.Time;
import com.base.game.gameobject.item.Item;
import com.base.game.map.Block;
import com.base.game.map.Chunk;
import org.lwjgl.input.Keyboard;
 
/**
 *
 * @author Miguel
 */
public class Player extends Unit
{
    public static final float SIZE = 16;
    protected double gSpeed = 0;
    private final Inventory inv = new Inventory(16);
    private Game game;
    private Chunk actualChunk;
    private boolean mapColision = false;
    public Player(float X, float Y, Game game)
    {
        this.game = game;
        init(type, X, Y, 0.1f,1f,0.25f,SIZE,SIZE);
        stats = new Stats(0, true);
        stats.setName("Muit");
        toPlayer();
    }
    
    public void getInput()
    {
        //if(!chatMode){
            if(Keyboard.isKeyDown(Keyboard.KEY_A))
                move(-1);
            else if(Keyboard.isKeyDown(Keyboard.KEY_D))
                move(1);
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
                if(mapColision)
                    stats.setJumping(true);
        //}
        //else{
            
        //}
    }
    @Override
    public void update()
    {
        if(y<=-16)
        {
            //die();
        }
        
        if(y<=-16)//futura mapColision aqui
        {
            y=-16;
            mapColision = true;
        }
        mapColision();
    }
    
    public void fisic()
    {
        y+=getGSpeed()/16;
    }
    
    private void move(float magx)
    {
        x+=getSpeed() * magx * getDelta();
    }
    
    protected double getGSpeed()
    {
        if(gSpeed<=-5)
            gSpeed=-5;
        else
            gSpeed-=0.2;
        return gSpeed;
    }
    public float getSpeed()
    {
        return stats.getSpeed();
    }
    public int getLevel()
    {
        return stats.getLevel();
    }
    
    public int getMaxHealth()
    {
        return getLevel() * 10;
    }
    
    public int getCurrentHealth()
    {
        int max = getMaxHealth();
        if(stats.getHealth() > max)
            stats.setHealth(max);
        
        return stats.getHealth();
    }
    
    public float getStrength()
    {
        return stats.getLevel() * 2.5f;
    }
    
    public void addXp(int amt)
    {
        stats.addXp(amt);
    }
    
    public boolean addItem(Item item)
    {
        return inv.add(item);
    }
    public void mapColision()
    {
        if(mapColision == true)
        {
            if(stats.getJumping())
            {
                gSpeed=5;
                stats.setJumping(false);
                mapColision = false;
                fisic();
            }
            else
            {
                gSpeed=0;
            }
        }
        else
            fisic();
    }
    public void setMapColision(boolean mapColision)
    {
        this.mapColision = mapColision;
    }
    public void setY(int y)
    {
    this.y = y;
    }
    public void setX(int x)
    {
    this.x = x;
    }
}

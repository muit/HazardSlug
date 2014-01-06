/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Game;
import com.base.game.gameobject.item.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Miguel
 */
public class Player extends GameObject
{
    public static final float SIZE = 16;
    protected double gSpeed = 0;
    private final Stats stats = new Stats(0, true);
    private final Inventory inv = new Inventory(16);
    private Game game;
    private boolean colision = false;
    public Player(float X, float Y, Game game)
    {
        this.game = game;
        init(1, X, Y, 0.1f,1f,0.25f,SIZE,SIZE);
    }
    
    public void getInput()
    {
            if(Keyboard.isKeyDown(Keyboard.KEY_A))
                move(-1);
            else if(Keyboard.isKeyDown(Keyboard.KEY_D))
                move(1);
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            if(colision)
            stats.setJumping(true);
    }
    @Override
    public void update()
    {
        if(y<=-16)
        {
            //die();
        }
        
        if(y<=227)//futura colision aqui
        {
            y=227;
            colision = true;
            if(stats.getJumping())
            {
                gSpeed=5;
                stats.setJumping(false);
                fisic();
                colision = false;
            }
            else
            {
                gSpeed=0;
            }
        }
        else
        {  
            fisic();
        }
    }
    
    public void fisic()
    {
        y+=getGSpeed()/16;
    }
    
    private void move(float magx)
    {
        x+=getSpeed()*magx;
    }
    
    protected double getGSpeed()
    {
        if(gSpeed>=-5)
            gSpeed-=0.2;
        else if(gSpeed<-5)
            gSpeed=-5;
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
}

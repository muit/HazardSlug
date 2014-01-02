/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.gameobject.item.Item;
import com.base.game.gameobject.player.Inventory;
import com.base.game.gameobject.player.Stats;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Miguel
 */
public class Player extends GameObject
{
    public static final float SIZE = 16;
    
    private final Stats stats = new Stats();
    private final Inventory inv = new Inventory(16);
    
    
    public Player(float x, float y)
    {
        init(0, x, y, 0.1f,1f,0.25f,SIZE,SIZE);
    }
    
    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0,1);
        else if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0,-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1,0);
        else if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1,0);
    }
    @Override
    public void update()
    {
        if(x<=-100)
        {
            System.out.println(stats.getName()+"");
            //die();
        }
    }
    private void move(float magx, float magy)
    {
        x+=getSpeed()*magx;
        y+=getSpeed()*magy;
    }
    public float getSpeed()
    {
        return 6;
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

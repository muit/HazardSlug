/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.game.Time;
import com.base.game.gameobject.item.Item;
import com.base.game.map.Block;

import org.lwjgl.input.Keyboard;
 
/**
 *
 * @author Miguel
 */
public class Player extends Unit
{
    @SuppressWarnings("unused")
	private static final int 
        MOVE_NULL  = 0,
        MOVE_RIGHT = 1,
        MOVE_LEFT  = -1;
    
    private static final float SIZE = 16;
    protected double gSpeed = 0;
    private final Inventory inv = new Inventory(16);
    private boolean justJump;
	private boolean mapColision;
    private boolean inGround;
    private int movx;
    private Block groundBlock;
    
    public Player(float X, float Y)
    {
        init(X, Y, 0.1f,1f,0.25f,SIZE,SIZE);
        stats = new Stats(0, true);
        stats.setName("Muit");
        inGround = false;
        justJump = false;
        mapColision = false;
        groundBlock = null;
        stats.setSpeed(0.20f);
        toPlayer();
    }
    
    public void getInput()
    {
        //if(!chatMode){
            if(getStats().isAlive())
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_A))
                    movx = -1;
                else if(Keyboard.isKeyDown(Keyboard.KEY_D))
                    movx = 1;
                else
                    movx = 0;
                if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
                    justJump = true;
            }
            else
            {
                movx = 0;
            }
        //}
        //else{
            
        //}
    }
    @Override
    public void update()
    {
        move(movx);
        fisic();
        
        if(y<=-16)//futura mapColision aqui
        {
            y=-16;
            inGround = true;
        }
        if(inGround)
        {
            
        }
        
        if(groundBlock != null)
        {
            y=Math.round(y);
            if(x-groundBlock.getX()<-1 || x-groundBlock.getX()>1)
                groundBlock=null;
            
            gSpeed=0;
            
            if(justJump)
            {
                stats.setJumping(true);
                gSpeed=0.28;
                justJump = false;
                groundBlock = null;
            }
            else if(stats.getJumping())
            {
                stats.setJumping(false);
            }
        }
        else
        {
        	if(gSpeed <= -0.4375)
        		gSpeed=-0.4375;
        	else
        		gSpeed += stats.getGravity()*Time.getDelta();
        	
        	y += gSpeed;
        }
        inGround = false;
    }
    
    public void fisic()
    {
        y+=gSpeed;
    }
    
    private void move(float magx)
    {
        if(magx != 0)
            x+=getSpeed() * magx * getDelta();
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
    
    public void setMapColision(boolean mapColision)
    {
        this.mapColision = mapColision;
    }
    public void inGround(boolean inGround)
    {
        this.inGround = inGround;
    }
    public void setY(int y)
    {
    this.y = y;
    }
    
    public void setX(int x)
    {
    this.x = x;
    }
    public void setGroundBlock(Block groundBlock)
    {
        this.groundBlock = groundBlock;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import java.util.ArrayList;

import com.base.engine.Main;
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
    
    private static final float SIZE = 16;
    protected double gSpeed = 0;
    private final Inventory inv = new Inventory(16);
    private boolean justJump;
	private boolean mapColision;
    private boolean inGround;
    private Block groundBlock;
    private ArrayList<Block> nearMapBlocks;
    public Player(float X, float Y)
    {
    	modX = 1.5f;
    	modY = 1.5f;
        init(0, X, Y, SIZE*modX,SIZE*modY);
        stats = new Stats(0, true);
        stats.setName("Muit");
        inGround = false;
        justJump = false;
        mapColision = false;
        groundBlock = null;
        nearMapBlocks = new ArrayList<>();
        stats.setSpeed(0.20f);
        toPlayer();
    }
    
    public void getInput()
    {
    	while (Keyboard.next()) 
    	{
	        //if(!chatMode){
	            if(getStats().isAlive())
	            {
	                if(Keyboard.isKeyDown(Keyboard.KEY_A))
	                {
	                    movx = MOVE_LEFT;
	                    spr.setAnimation(1);
	                }
	                else if(Keyboard.isKeyDown(Keyboard.KEY_D))
	                {
	                    movx = MOVE_RIGHT;
	                    spr.setAnimation(0);
	                }
	                else
	                {
	                    movx = MOVE_NULL;
	                }
	                
	                if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
	                    justJump = true;
	            }
	            else
	            {
	                Main.spawnGUI(0);
	                movx = MOVE_NULL;
	            }
	        //}
	        //else{
	            
	        //}  
	    }
    }
    @Override
    public void update()
    {
        move();
        fisic();
        ArrayList<Block> nearMapBlocks = Main.sphereMapCollide(x, y, 7);
        for(Block bl : nearMapBlocks)
        	bl.updateSpr();
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
                gSpeed=0.25;
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
        spr.update();
    }
    
    public void fisic()
    {
        y+=gSpeed;
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

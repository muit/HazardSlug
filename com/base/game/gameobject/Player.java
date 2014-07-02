/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.Camera;
import java.util.ArrayList;

import com.base.engine.Main;
import com.base.game.Time;
import com.base.game.gameobject.item.Item;
import com.base.game.map.Block;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
 
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
    
    @SuppressWarnings("unused")
    private final int EVENT_SALPICADURA = 0,
                EVENT_MORDEDURA   = 1;
    
    @SuppressWarnings("unused")
    private final int SPELL_SALPICADURA = 0,
                SPELL_MORDEDURA   = 1;
    
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
        stats.setSpeed(0.19f);
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
        while (Mouse.next()) 
    	{
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    click(Mouse.getX()/16 + Camera.getX()/16, Mouse.getY()/16 + Camera.getY()/16);
                }
            }else {
                if (Mouse.getEventButton() == 0) {
                }
            }
        }
    }
    
    private void click(float x, float y)
    {
        if(getStats().isAlive())
        {
            DoCast(null, SPELL_SALPICADURA, x, y);
        }
    }
    
    @Override
    public void update()
    {
        move();
        fisic();
        nearMapBlocks = Main.sphereMapCollide(getX(), getY(), 5);
        for(Block bl : nearMapBlocks)
        	bl.updateSpr();
        if(getY()<=-16)//futura mapColision aqui
        {
            setY(getY()-16);
            inGround = true;
        }
        if(inGround)
        {
            
        }
        
        if(groundBlock != null)
        {
            setY(Math.round(getY()));
            if(getX()-groundBlock.getX()<-1 || getX()-groundBlock.getX()>1)
                groundBlock=null;
            
            gSpeed=0;
            
            if(justJump)
            {
                stats.setJumping(true);
                gSpeed=0.5;
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
            if(gSpeed <= -0.8746)
                gSpeed=-0.8746;
            else
                gSpeed += stats.getGravity()*Time.getDelta();

        }
        inGround = false;
        spr.update();
    }
    
    public void fisic()
    {
        setY(getY() + (float)gSpeed);
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
    public void setGroundBlock(Block groundBlock)
    {
        this.groundBlock = groundBlock;
    }
}

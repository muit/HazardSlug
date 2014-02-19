/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.EffectManager;
import com.base.engine.GameObject;
import com.base.engine.UnitSprite;
import com.base.game.Time;
import com.base.game.text.Log;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Miguel
 */
public class Unit extends GameObject
{
	protected float modX;
    protected float modY;
	protected static final int 
	    MOVE_NULL  = 0,
	    MOVE_RIGHT = 1,
	    MOVE_LEFT  = -1;
	protected int id;
    protected String name;
    private boolean player = false;
    protected Stats stats;
    protected UnitSprite spr;
    protected int movx;
    
    protected void init(int id, float x, float y, float sx,float sy)
    {
    	this.id = id;
        this.type = 3;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        spr = new UnitSprite(id,sx,sy);
    }
    
    public boolean isPlayer()
    {
        return player;
    }
    public void toPlayer()
    {
        type = 1;
        player = true;
    }
    public void toCreature()
    {
        type = 2;
        player = false;
    }
    public void modifyHealth(int damage)
    {
        if(stats.modifyHealth(-damage))
        {
            Log.sendMessageToAll(stats.getName() + " ha muerto.");
        }
        else
        {
            Log.sendMessageToAll(stats.getName() + " ha sufrido "+damage+" puntos de da�o.");
        }
    }
    public Stats getStats()
    {
        return stats;
    }
    public String getName()
    {
        return stats.getName();
    }
    public boolean isAlive()
    {
        return stats.isAlive();
    }
    public float getDelta()
    {
        return Time.getDelta();
    }
    public void DoCast(Unit target, int id)
    {
        //Scripts de spell
        switch(id)
        {
            case 0: //Salpicadura
                EffectManager.createEffect(this, id, x, y, target, 0.21f, true);
                break;
            case 1:
                Log.sendMessageToAll(target.getName()+" recibi� Mordisco.");
                break;
            
            //On hit(id+1000): 
            case 1000://Salpicadura 
                Log.sendMessageToAll(target.getName()+" recibi� Salpicadura.");
                break;
            default:
                Log.sendMessageToAll("Spell: "+id+" no existe.");
                break;
        }
    }
    
    public void update()
    {
    	spr.render();
    }
    
    @Override
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    
    protected void move()
    {
        x+=getSpeed() * movx * getDelta();
        if(movx == MOVE_LEFT)
        	spr.setAnimation(1);
        else if(movx == MOVE_RIGHT)
        	spr.setAnimation(0);
        else
        	spr.setAnimationEnabled(false);
    }
    
    public float getSpeed()
    {
        return stats.getSpeed();
    }
}

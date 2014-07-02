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
import org.lwjgl.util.vector.Vector2f;

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
        vectors = new Vector2f[] {
            new Vector2f(x, y),
            new Vector2f(x, y + sy),
            new Vector2f(x + sx, y + sy),
            new Vector2f(x + sx, y)
        };
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
            Log.sendMessageToAll(stats.getName() + " ha sufrido "+damage+" puntos de daño.");
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
        DoCast(target, id, 0, 0);
    }
    public void DoCast(Unit target, int id, float x, float y)
    {
        //Scripts de spell
        switch(id)
        {
            case 0: //Salpicadura
                Log.sendMessageToAll(getName()+" lanzó Salpicadura.");
                EffectManager.createEffect(this, id, getX(), getY(), new Vector2f(x, y), 0.25f);
                break;
            case 3: //Salpicadura Inteligente
                Log.sendMessageToAll(getName()+" lanzó Salpicadura Inteligente a "+target.getName()+".");
                EffectManager.createEffect(this, 0, getX(), getY(), target, 0.21f, true);
                break;
            case 1: //Mordisco
                Log.sendMessageToAll(target.getName()+" recibió Mordisco.");
                break;
            case 2: //Escupitajo
            	//EffectManager.createEffect(this, id, x, y, target, 0.21f, true);
            	break;
            
            //On Effect Hit(id+1000): 
            case 1000: //Salpicadura 
                Log.sendMessageToAll(target.getName()+" recibió Salpicadura.");
                target.modifyHealth(1);
                break;
                
            case 1001: //Mordisco
                Log.sendMessageToAll(target.getName()+" recibió Mordisco.");
                break;
                
            case 1002: //Escupitajo
            	Log.sendMessageToAll(target.getName()+" recibió Escupitajo.");
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
            glTranslatef(getX()*16, getY()*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    
    protected void move()
    {
        setX(getX()+(getSpeed() * movx * getDelta()));
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
    
    public boolean pointInside(float pX, float pY)
    {
        System.out.println(pX+" "+getX());
        return ((getX() < pX && getX()+getSX() > pX) && (getY() < pY && getY() + getSY() > pY));
    }
}

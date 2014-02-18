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
    protected String name;
    private boolean player = false;
    protected Stats stats;
    protected UnitSprite spr;
    
    protected void init(float x, float y, float r, float g, float b, float sx,float sy)
    {
        this.type = 3;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        spr = new UnitSprite(r,g,b,sx,sy);
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
        //Scripts de spell
        switch(id)
        {
            case 0: //Salpicadura
                EffectManager.createEffect(this, id, x, y, target, 0.21f, true);
                break;
            case 1:
                Log.sendMessageToAll(target.getName()+" recibió Mordisco.");
                break;
            
            //On hit(id+1000): 
            case 1000://Salpicadura 
                Log.sendMessageToAll(target.getName()+" recibió Salpicadura.");
                break;
            default:
                Log.sendMessageToAll("Spell: "+id+" no existe.");
                break;
        }
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
}

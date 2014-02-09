/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Time;
import com.base.game.text.Log;

/**
 *
 * @author Miguel
 */
public class Unit extends GameObject
{
    private boolean player = false;
    protected Stats stats;
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
            case 0:
                Log.sendMessageToAll(target.getName()+" recibio Salpicadura.");
                break;
            case 1:
                Log.sendMessageToAll(target.getName()+" recibio Mordisco.");
                break;
            default:
                Log.sendMessageToAll("Spell: "+id+" no existe.");
                break;
        }
    }
}

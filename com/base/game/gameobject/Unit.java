/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Delay;
import com.base.game.Time;

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
            System.out.println(stats.getName() + " ha muerto.");
        }
        else
        {
            System.out.println(stats.getName() + " ha sufrido "+damage+" puntos de daño.");
        }
    }
    public Stats getStats()
    {
        return stats;
    }
    public boolean isAlive()
    {
        return stats.isAlive();
    }
    public float getDelta()
    {
        return Time.getDelta();
    }
}

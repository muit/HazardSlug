/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Delay;

/**
 *
 * @author Miguel
 */
public class Unit extends GameObject
{
    private boolean player = false;
    
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
}

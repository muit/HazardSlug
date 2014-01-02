/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.gameobject.player.Stats;

/**
 *
 * @author Miguel
 */
public class Enemy extends GameObject{
    private Stats stats;
    private GameObject target;
    
    public Enemy ()
    {
        target = null;
    }
    
    @Override
    public void update()
    {
        if(target!=null)
            cUpdate();
        
        //Attack call
        if(stats.getHealth()<=0)
            die();
    }
    
    public void cUpdate()
    {
        
    }
    
    public void attack()
    {
        
    }
    
    public void die()
    {
        
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.Util;

/**
 *
 * @author Miguel
 */
public class Enemy extends GameObject
{
    private Stats stats;
    private GameObject target;
    
    public Enemy (int level)
    {
        stats = new Stats(level, false);
        target = null;
        this.type = 2;
    }
    
    @Override
    public void update()
    {
        if(target==null)
            look();
        else
        {
            chase();
            if(Util.LineOfSight(this,target))
                attack();
        }
        if(stats.getHealth()<=0)
            die();
    }
    
    protected void look()
    {
    }
    
    protected void chase()
    {
        
    }
    
    protected void attack()
    {
        
    }
    
    protected void die()
    {
        
    }
    public static int LevelToXP(int level)
    {
        return 0;
    }
    protected void setTarget(GameObject go)
    {
        target = go;
    }
}
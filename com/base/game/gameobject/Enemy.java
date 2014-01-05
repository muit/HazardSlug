/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.Util;

/**
 *
 * @author Miguel
 */
public class Enemy extends GameObject
{
    private Stats stats;
    private GameObject target;
    private boolean justEnterCombat=false;
    protected int MELE_RANGE=0;
    protected int VISION_RANGE=0;
    protected int DISTANCE_RANGE=0;
    
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
        {
            if(justEnterCombat)
                justEnterCombat = false;
            look();
        }
        else
        {
            if(!justEnterCombat)
            {
                justEnterCombat = true;
                justEnterCombat();
            }
            chase();
            if(Util.LineOfSight(this,target))
                attack();
        }
        if(stats.getHealth()<=0)
            justDied();
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
    
    protected void justDied()
    {
        
    }
    protected void justEnterCombat()
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
    protected GameObject getTarget()
    {
        return target;
    }
    protected Stats getStats()
    {
        return stats;
    }
}
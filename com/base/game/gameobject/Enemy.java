/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.Util;
import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class Enemy extends Unit
{
    private Stats stats;
    private Unit target;
    private boolean justEnterCombat;
    protected float MELEE_RANGE=0;
    protected float VISION_RANGE=0;
    protected float DISTANCE_RANGE=0;
    protected float DAMPING;
    public int SIZE;
    private boolean meleRangeCorrect;
    
    public Enemy (int level)
    {
        stats = new Stats(level, false);
        target = null;
        meleRangeCorrect = false;
        justEnterCombat = false;
        toCreature();
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
                EnterCombat(target);
            }
            if(!meleRangeCorrect)
                chase();
            if(Util.LineOfSight(this,target))
                Attack();
        }
        if(stats.getHealth()<=0)
            JustDied(target);
    }
    
    protected void look()
    {
        ArrayList<Unit> objects = Main.sphereCollide(x, y, 10);
        
        for( Unit go : objects)
        {
            if(go.isPlayer()&& go != this)
            {
                System.out.println("Ping");
                setTarget(go);
            }
        }
    }
    
    protected void chase()
    {
        float speedX = (getTarget().getX()-x);
        float speedY = (getTarget().getY()-y);
        
        float maxSpeed = getStats().getSpeed()*DAMPING;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        x+=speedX;
        y+=speedY;
    }
    
    protected void Attack()
    {
        
    }
    
    protected void JustDied(Unit killer)
    {
        
    }
    protected void EnterCombat(Unit who)
    {
        
    }
    protected void DamageTaken(Unit who)
    {
        
    }
    public static int LevelToXP(int level)
    {
        return 0;
    }
    protected void setTarget(Unit go)
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
    protected void DoAttackWhenReady()
    {
        if(Util.dist(x, y, target.getX(), target.getY())< MELEE_RANGE)
        {
            meleRangeCorrect = true;
            System.out.println("Pum");
        }
        else
            meleRangeCorrect = false;
    }
}
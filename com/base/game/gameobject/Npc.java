/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.Main;
import com.base.game.Delay;
import com.base.game.Util;
import com.base.game.text.Log;
import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class Npc extends Unit
{
    protected Unit target;
    private boolean justEnterCombat;
    protected float MELEE_RANGE=0;
    protected float VISION_RANGE=0;
    protected float DISTANCE_RANGE=0;
    protected float DAMPING;
    protected float ATTACK_SPEED;
    protected Delay hitDelay;
    protected int SIZE;
    private boolean meleRangeCorrect;
    private float positionBeforeCombatX, positionBeforeCombatY;
    private float goToX, goToY;
    
    public Npc (int level)
    {
        stats = new Stats(level, false);
        target = null;
        meleRangeCorrect = false;
        justEnterCombat = false;
        hitDelay = new Delay(0);
        hitDelay.end();
        toCreature();
    }
    
    @Override
    public void update()
    {
        if(target == null)
        {
            if(justEnterCombat)
                justEnterCombat = false;
            look();
        }
        else if(!getTarget().isAlive())
        {
            if(goToPos(positionBeforeCombatX,positionBeforeCombatY))
                target=null;
        }
        else
        {
            if(!justEnterCombat)
            {
                justEnterCombat = true;
                EnterCombat(target);
                positionBeforeCombatX = x;
                positionBeforeCombatY = y;
            }
            if(!meleRangeCorrect)
                chase();
            if(Util.LineOfSight(this,target)&& getTarget().isAlive())
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
            if(go.isPlayer()&& go != this && go.isAlive())
            {
                setTarget(go);
            }
        }
    }
    
    protected void chase()
    {
        float speedX = (getTarget().getX()-x);
        float speedY = (getTarget().getY()-y);
        
        float maxSpeed = stats.getSpeed()*DAMPING;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        x+=speedX * getDelta();
        y+=speedY * getDelta();
    }
    private boolean goToPos(float X, float Y)
    {
        float speedX = (X-x);
        float speedY = (Y-y);
        
        float maxSpeed = getStats().getSpeed()*DAMPING;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        x+=speedX * getDelta();
        y+=speedY * getDelta();
        
        return x==X && y==Y;
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
    protected Unit getTarget()
    {
        return target;
    }
    
    public void setAttackDelay(float cof_time)
    {
        int time = (int) (cof_time*1000);
        hitDelay = new Delay(time);
        hitDelay.end();
    }
    public void resetAttackDelay()
    {
        hitDelay.start();
    }
    protected void DoAttackWhenReady()
    {
        if(hitDelay.over())
        {
            if(Util.distSqrt(x, y, target.getX(), target.getY())< Math.pow(MELEE_RANGE, 2))
            {
                meleRangeCorrect = true;
                int damage = 1;
                getTarget().modifyHealth(damage);
                resetAttackDelay();
            }
            else
                meleRangeCorrect = false;
        }
    }
}
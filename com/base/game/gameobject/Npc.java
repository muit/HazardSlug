/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.Main;
import com.base.game.Delay;
import com.base.game.Util;

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
    private Delay hitDelay;
    private Delay diedDisappearDelay;
    protected int SIZE;
    private int miliSecondsToDisappear = 12000;
    private boolean meleRangeCorrect;
    private float positionBeforeCombatX, positionBeforeCombatY;
    
    public Npc (int id, int level, float x, float y, float modX, float modY)
    {
    	SIZE = 16;
    	init(id, x, y, SIZE*modX,SIZE*modY);
        stats = new Stats(level, false);
        target = null;
        meleRangeCorrect = false;
        justEnterCombat = false;
        hitDelay = new Delay(0);
        hitDelay.end();
        diedDisappearDelay = new Delay(0);
        diedDisappearDelay.end();
        toCreature();
        setDiedDisappearDelay(miliSecondsToDisappear);
    }
    
    @Override
    public void update()
    {
        if(stats.getHealth()>0)
        {
            //ALIVE
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
                    positionBeforeCombatX = getX();
                    positionBeforeCombatY = getY();
                }
                if(!meleRangeCorrect)
                    chase();
                if(Util.LineOfSight(this,target)&& getTarget().isAlive())
                    Attack();
            }
        }
        else
        {
            //NOT ALIVE
            if(stats.hasJustDied())
                JustDied(target);
            Died();
            if(diedDisappearDelay.over())
            {
                diedDisappearDelay.reset();
                this.remove = true;
            }
        }
        spr.update();
    }
    
    protected void look()
    {
        ArrayList<Unit> objects = Main.sphereCollide(getX(), getY(), 10);
        
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
    	float speed = getStats().getSpeed() * getDelta();
    	
    	// arctg(m)  m->v2/v1 (v1, v2)->(x-x2), (y-y2)
    	double alpha = Math.PI + Math.atan2(-(getTarget().getY()-getY()), -(getTarget().getX()-getX()));
    	
    	setX(getX()+(float)(speed * Math.cos(alpha)));
    	setY(getY()+(float)(speed * Math.sin(alpha)));
    	
    	if(alpha>Math.PI/2 && alpha<Math.PI+Math.PI/2)
        	spr.setAnimation(1);
        else 
        	spr.setAnimation(0);
    		
    }
    private boolean goToPos(float X, float Y)
    {
    	float speed = getStats().getSpeed() * getDelta();
    	
    	// arctg(m)  m->v2/v1 (v1, v2)->(x-x2), (y-y2)
    	double alpha = Math.PI + Math.atan2(-(Y-getY()), -(X-getX()));
    	
    	setX(getX()+(float)(speed * Math.cos(alpha)));
    	setY(getY()+(float)(speed * Math.sin(alpha)));
        return getX()==X && getY()==Y;
    }
    
    protected void Attack()
    {
        
    }
    protected void Died()
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
            if(Util.distSqrt(getX(), getY(), target.getX(), target.getY())< Math.pow(MELEE_RANGE, 2))
            {
                meleRangeCorrect = true;
                //Obtener Daño de habilidad de la DB!
                int damage = 1;
                getTarget().modifyHealth(damage);
                resetAttackDelay();
            }
            else
                meleRangeCorrect = false;
        }
    }
    
    public void setDiedDisappearDelay(int time)
    {
        diedDisappearDelay = new Delay(time);
        diedDisappearDelay.end();
    }
    public void resetDiedDisappearDelay()
    {
        diedDisappearDelay.start();
    }
}
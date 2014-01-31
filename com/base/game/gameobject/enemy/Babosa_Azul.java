/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.enemy;

import com.base.game.gameobject.Enemy;
import com.base.game.gameobject.Unit;

/**
 *
 * @author Miguel
 */
public class Babosa_Azul extends Enemy
{
    
    public Babosa_Azul(float x, float y, int level)
    {
        super(level);
        MELEE_RANGE = (float)1.5;
        DAMPING = 0.8f;
        SIZE = 16;
        init(type, x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE);
    }
    
    @Override
    protected void EnterCombat(Unit who)
    {
        //When npc enter combat:
        System.out.println("Zarigüella quiere sapatos pa comer!!");
        //End-------------------
    }
    
    @Override
    protected void Attack()
    {
        //If npc is in combat:
        
        //End-------------------
        DoAttackWhenReady();
    }
    
    @Override
    protected void JustDied(Unit killer)
    {
    
    }
    
    @Override
    protected void DamageTaken(Unit who)
    {
        
    }
}

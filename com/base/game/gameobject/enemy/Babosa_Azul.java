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
        MELEE_RANGE = 2;
        DAMPING = 0.8f;
        SIZE = 16;
        init(type, x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE);
    }
    
    @Override
    protected void EnterCombat(Unit who)
    {
        System.out.println("Zarigüella quiere sapatos pa comer!!");
    }
    
    @Override
    protected void attack()
    {
        DoAttackWhenReady();
    }
}

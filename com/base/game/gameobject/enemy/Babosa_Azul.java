/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.enemy;

import com.base.game.EventsMap;
import com.base.game.Util;
import com.base.game.gameobject.Npc;
import com.base.game.gameobject.Unit;

/**
 *
 * @author Miguel
 */


public class Babosa_Azul extends Npc
{
    private EventsMap event = new EventsMap();
    
    private int EVENT_SALPICADURA = 0,
                EVENT_MORDEDURA   = 1;
    
    private int SPELL_SALPICADURA = 0,
                SPELL_MORDEDURA   = 1;
    
    
    public Babosa_Azul(float x, float y, int level)
    {
        super(level);//0.8f: 1sec*% attack speed
        MELEE_RANGE = (float)1.5;
        DAMPING = 1.0f;
        SIZE = 16;
        setAttackDelay(0.8f);//0.8f: 1sec*% attack speed
        init(x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE);
    }
    
    @Override
    protected void EnterCombat(Unit who)
    {
        //When npc enter combat:
        System.out.println("Zarigüella quiere sapatos pa comer!!");
        
        event.ScheduleEvent(EVENT_SALPICADURA, 2000);
        //End-------------------
    }
    
    @Override
    protected void Attack()
    {
        //If npc is in combat:
        while(true)
        {
            int id = event.getEvents();
            if(id != -1)
            {
                switch(id)
                {
                case 0:
                    DoCast(getTarget(),0);
                    event.RestartEvent(EVENT_SALPICADURA);
                    break;
                case 1:
                    //codigo si evento 1 se ejecuta
                    break;
                case 2:
                    //codigo si evento 2 se ejecuta
                    break;
                }
            }
            else
                break;
        }
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

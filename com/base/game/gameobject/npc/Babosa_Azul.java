/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.npc;

import com.base.game.EventsMap;
import com.base.game.gameobject.Npc;
import com.base.game.gameobject.Unit;

/**
 *
 * @author Miguel
 */


public class Babosa_Azul extends Npc
{
    private final EventsMap event = new EventsMap();
    
    @SuppressWarnings("unused")
	private final int EVENT_SALPICADURA = 0,
                EVENT_MORDEDURA   = 1;
    
    @SuppressWarnings("unused")
	private final int SPELL_SALPICADURA = 0,
                SPELL_MORDEDURA   = 1;
    
    
    public Babosa_Azul(float x, float y, int level)
    {
        super(0, level, x, y, 1.25f, 1.25f);//0.8f: 1sec*% attack speed
        MELEE_RANGE = (float)1.5;
        DAMPING = 1.0f;
        setAttackDelay(0.8f);//0.8f: 1sec*% attack speed
    }
    
    @Override
    protected void EnterCombat(Unit who)
    {
        //When npc enter combat:
        System.out.println("Zarigüella quiere sapatos para comer!!");
        
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
                    DoCast(getTarget(),SPELL_SALPICADURA);
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

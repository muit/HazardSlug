/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game;

import com.base.game.Delay;

/**
 *
 * @author Miguel_F
 */
public class Event {
    private int id;
    private int duration;
    private boolean enabled;
    private Delay delay;
    
    public Event(int id, int duration, boolean enabled)
    {
        this.id = id;
        this.duration = duration;
        this.enabled = enabled;
        delay = new Delay(duration);
        delay.end();
    }
    
    public void ChangeDuration(int newDuration)
    {
        this.duration = newDuration;
        delay = new Delay(duration);
        delay.end();
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
    public void Enable(boolean enabled)
    {
        this.enabled = enabled;
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public void restart()
    {
        enabled = true;
        delay = new Delay(duration);
        delay.end();
    }
    public boolean Over()
    {
        if(enabled)
            return delay.over();
        else
            return false;
    }
}

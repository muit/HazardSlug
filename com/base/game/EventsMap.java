/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game;

import com.base.game.Event;
import java.util.ArrayList;

/**
 *
 * @author Miguel_F
 */
public class EventsMap {
    private final ArrayList<Event> events = new ArrayList<>();
    
    public void ScheduleEvent(int id, int duration)
    {
        boolean used = false;
        for(int i =0; i<events.size(); i++)
        {
            Event ev = events.get(i);
            if(ev.getId() == id)
            {
                used = true;
                ev.ChangeDuration(duration);
                ev.Enable(true);
                break;
            }
        }
        if(used==false)
            events.add(new Event(id, duration, true));
    }
    
    public void RestartEvent(int id)
    {
        for(int i =0; i<events.size(); i++)
        {
            Event ev = events.get(i);
            if(ev.getId() == id)
            {
                ev.restart();
                break;
            }
        }
    }
    public void RestartEvent(int id, int newDuration)
    {
        for(int i =0; i<events.size(); i++)
        {
            Event ev = events.get(i);
            if(ev.getId() == id)
            {
                ev.Enable(true);
                ev.ChangeDuration(newDuration);
                break;
            }
        }
    }
    
    public void CancelEvent(int id)
    {
        for(int i=0; i<events.size(); i++)
        {
            if(events.get(i).getId() == id)
            {
                events.remove(i);
            }
        }
    }
    public void CancelAllEvents()
    {
       events.clear();
    }
    
    public boolean EventActive(int id)
    {
        for(int i=0; i<events.size(); i++)
        {
            Event ev = events.get(i);
            if(ev.getId() == id)
            {
                return ev.isEnabled();
            }
        }
        return false;
    } 
    public int getEvents()
    {
        for(int i=0; i<events.size(); i++)
        {
            Event ev = events.get(i);
            if(ev.Over())
            {
                int id = ev.getId();
                events.remove(i);
                return id;
            }
        }
        return -1;
    }
}

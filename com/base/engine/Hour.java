/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import com.base.game.Event;

/**
 *
 * @author Miguel_F
 */
public class Hour {
    public static int msecXMin = 1500;
    
    private static int minute = 0;
    private static Event delay = new Event(0, msecXMin, true);
    private static int hour = 7, minutes = 0;
    public static void reset()
    {
        delay = new Event(0, msecXMin, true);
        hour = 7;
        minutes = 0;
        minute = 0;
    }
    public static void update()
    {
        if(delay.Over())
        {
            minute++;
            if(minutes<60-1)
                minutes++;
            else
            {
                if(hour<24-1)
                    hour++;
                else
                {
                    hour = 0;
                    minute = 0;
                }
                minutes = 0;
            }
            delay.restart();
            System.out.println(getHoursS()+":"+getMinutesS());
        }
    }
    public static int getTime(){return minute;}
    public static void setTime(int Minute){minute = Minute;}
    public static int getHours()
    {
        return hour;
    }
    public static String getHoursS()
    {
        if(hour>=10)
            return ""+hour;
        else
            return "0"+hour;
    }
    public static int getMinutes()
    {
        return minutes;
    }
    public static String getMinutesS()
    {
        if(minutes>=10)
            return ""+minutes;
        else
            return "0"+minutes;
    }
    public static int getTotalMinutes()
    {
        return minutes+hour*60;
    }
    public static void setInterval(int interval)
    {
        msecXMin = interval;
        delay = new Event(0, msecXMin, true);
    }
}

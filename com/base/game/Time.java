/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

/**
 *
 * @author Miguel
 */
public class Time 
{
    private static long curTime;
    private static long lastTime;
    
    public static long getTime()
    {
        return System.nanoTime();
    }
    
    public static long getDelta()
    {
        return curTime - lastTime;
    }
    
    public static void update()
    {
        lastTime = curTime;
        curTime = getTime();
    }
    public static void init()
    {
        lastTime = getTime();
        curTime = getTime();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.engine.GameObject;

/**
 *
 * @author Miguel
 */
public class Util {
    public static int random(int min, int max)
    {
        return (int)(min + (Math.random() * ((max - min) + 1)));
    }
    
    public static boolean LineOfSight(GameObject go1, GameObject go2)
    {
        return true;
    }
    
    public static float dist(float x1, float y1, float x2, float y2)
    {
        double x = x2 - x1;
        double y = y2 - y1;
        return (float)Math.sqrt((x*x)-y*y);
    }
}

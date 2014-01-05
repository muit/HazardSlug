/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.awt.Rectangle;

/**
 *
 * @author Miguel
 */
public class Physics {
    
    public static GameObject checkCollision(GameObject go1, GameObject go2)
    {
        Rectangle r1 = new Rectangle((int)(go1.getX()*16), (int)(go1.getY()*16), (int)go1.getSX(), (int)go1.getSY());
        Rectangle r2 = new Rectangle((int)(go2.getX()*16), (int)(go2.getY()*16), (int)go2.getSX(), (int)go2.getSY());
        
        boolean res = r1.intersects(r2);
        if(res)
            return go2;
        else 
            return null;
    }
}

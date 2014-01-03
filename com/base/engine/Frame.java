/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author Miguel
 */
public class Frame 
{
    private final int lenght;
    private final Sprite spr;
    private int numDisplayed;
    
    public Frame(Sprite spr, int lenght)
    {
        this.lenght = lenght;
        this.spr = spr;
    }
    
    public boolean render()
    {
        spr.render();
        numDisplayed++;
        
        if(numDisplayed >= lenght)
        {
            numDisplayed = 0;
            return true;
        }
        return false;
    }
}

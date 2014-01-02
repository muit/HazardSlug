/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class Animation 
{
    private ArrayList<Frame> frames;
    private int curFrame;
    
    public Animation()
    {
        frames = new ArrayList<Frame>();
    }
    
    public void render()
    {
        Frame temp = frames.get(curFrame);
        
        if(temp.render())
        {
            curFrame++;
            curFrame %= frames.size();
        }
    }
}

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
    
    public enum AnimationType {
        //MOVEMENT///////////
        //Stay///////
        ANIM_STAY_R, 
        ANIM_STAY_L, 
        
        //Run////////
        ANIM_RUN_R, 
        ANIM_RUN_L, 
        
        //Jump///////
        ANIM_JUMP_R, 
        ANIM_JUMP_L,
        
        //Falling////
        ANIM_FALL_R, 
        ANIM_FALL_L, 
        
        //Bow////////
        ANIM_BOW_R,
        ANIM_BOW_L,
        
        //COMBAT//////////////
        //Attack/////
        ANIM_ATTACK_R, 
        ANIM_ATTACK_L, 
        
        //Hit////////
        ANIM_HIT_R, 
        ANIM_HIT_L, 
        
        //Stun///////
        ANIM_STUN_R, 
        ANIM_STUN_L
    };
    private AnimationType currentAnimation;
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
    public void setAnimation(AnimationType anim)
    {
        currentAnimation = anim;
    }
}

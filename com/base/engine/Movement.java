/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import org.lwjgl.util.vector.Vector2f;

import com.base.game.Time;
import com.base.game.Util;
/**
 *
 * @author Miguel_F
 */
public class Movement {
    private Vector2f vector;
    private Vector2f target;
    private boolean flying;
    private boolean following;
    
    private int moveHStatus;
    private int moveVStatus;
    @SuppressWarnings("unused")
	private final int 
            MOVE_RIGHT = 1,
            MOVE_LEFT = -1,
            MOVE_UP = 1,
            MOVE_DOWN = -1,
            MOVE_NONE = 0;
            
    
    private float v, vD;
    private float G;
    
    public Movement()
    {
        moveHStatus = MOVE_NONE;
        flying = false;
        G = -0.2f; 
    }
    public void setFly(boolean enable)
    {
        flying = enable;
    }
    
    public void moveHorizontal(int value)
    {
        moveHStatus = value;
    }
    public void moveVertical(int value)
    {
        moveVStatus = value;
    }
    public void setSpeed(float speed)
    {
        v = speed;
    }
    
    public Vector2f update(float x, float y)
    {
        vector.setX(x);
        vector.setY(y);
        react();
        return vector;
    }
    public Vector2f update(Vector2f vector)
    {
        this.vector = vector;
        react();
        return vector;
    }
    public void follow(float x, float y)
    {
        target.setX(x);
        target.setY(y);
        following = true;
    }
    public void follow(Vector2f target)
    {
        this.target = target;
        following = true;
    }
    public void react()
    {
        //OPERATIONS////////////////////////////////////////////////////////////
        vD = v*Time.getDelta();
        if(flying)
        {
        	vector.x += vD*moveHStatus;
            vector.y += vD*moveVStatus;
        }
        else if(following)
        {
            //1*1 = 1 so we dont need to Sqrt the distance
            double distSqrt = Util.distSqrt(vector.x, vector.y, target.x, target.y);
            if(distSqrt<=1)
            {
                following = false;
            }
            else
            {
            	// arctg(m)  m->v2/v1 (v1, v2)->(x-x2), (y-y2)
            	double alpha = Math.PI + Math.atan2(-(target.y-vector.y), -(target.x-vector.x));
            	
            	vector.x += vD * Math.cos(alpha);
            	vector.y += vD * Math.sin(alpha);
            }
        }
        else
        {
        	vector.x += vD*moveHStatus;
        	vector.y += vD+(G*Math.pow(Time.getDelta(), 2))/2;
        }
        ////////////////////////////////////////////////////////////////////////
    }
}

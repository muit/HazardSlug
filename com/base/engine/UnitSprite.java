/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;

import com.base.data.DataBase;
import com.base.game.EventsMap;
/**
 *
 * @author Miguel
 */
public class UnitSprite 
{
    private float sx,sy; 
    private int id;
    private int frame;  
    private int animation;
    private boolean animEnabled;
    private EventsMap event = new EventsMap(); 
    DataBase db;
    
    public UnitSprite(int id, float sx, float sy)
    {
    	this.id = id;
        this.sx = sx;
        this.sy = sy;
        db = new DataBase();
        animEnabled = false;
        frame=0;
        animation = 0;
        event.ScheduleEvent(0, 200);
    }
    
    public void update()
    {
    	//eventos para frames
        while(true)
        {
            int evId = event.getEvents();
            if(evId == 0)
            {
            	if(animEnabled)
            	{
            		frame++;
            		if(frame>=4 || frame<0)
                        frame=0;
            	}
            	else 
            		frame = 0;
                event.RestartEvent(0);
            }
            else
                break;
        }
    }
    
    public void render()
    {
        
        Color.white.bind();
        db.getNpcTexture(id).bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0.25f*frame+0.25f, 0.5f+animation*0.5f);
            glVertex2f(0,0);
            glTexCoord2f(0.25f*frame, 0.5f+animation*0.5f);
            glVertex2f(sx,0);
            glTexCoord2f(0.25f*frame, animation*0.5f);
            glVertex2f(sx,sy);
            glTexCoord2f(0.25f*frame+0.25f, animation*0.5f);
            glVertex2f(0,sy);
        }
        glEnd();
    }
    public float getSX()
    {
        return sx;
    }
    public float getSY()
    {
        return sy;
    }
    public void setSX(float sx)
    {
        this.sx = sx;
    }
    public void setSY(float sy)
    {
        this.sy = sy;
    }
    
    public void nextFrame()
    {
        frame++;
    }
    public void lastFrame()
    {
        frame--;
    }

    public void setAnimation(int animId)
    {
    	animation = animId;
    	animEnabled = true;
    }
    public void setAnimationEnabled(boolean value)
    {
    	animEnabled = value;
    }
    public boolean getAnimationEnabled()
    {
    	return animEnabled;
    }
}

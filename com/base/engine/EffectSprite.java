/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.data.DataBase;
import com.base.game.EventsMap;

import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Miguel
 */
public class EffectSprite 
{
    private float sx,sy;
    private int id;
    private int frame;
    DataBase db;
    private EventsMap event = new EventsMap();
    
    public EffectSprite(int id)
    {
        this.id = id;
        this.sx = 16;
        this.sy = 16;
        frame=0;
        db  = new DataBase();
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
                frame++;
                if(frame>=4 || frame<0)
                    frame=0;
                event.RestartEvent(0);
            }
            else
                break;
        }
    }
    
    public void render()
    {
        Color.white.bind();
        db.getEffectTexture(id).bind();
        glBegin(GL_QUADS);
        {
        	glTexCoord2f(0.25f*frame+0.25f,1);
            glVertex2f(0,0);
            glTexCoord2f(0.25f*frame,1);
            glVertex2f(sx,0);
            glTexCoord2f(0.25f*frame,0);
            glVertex2f(sx,sy);
            glTexCoord2f(0.25f*frame+0.25f,0);
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
}

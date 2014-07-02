/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Miguel_F
 */
public class Background {
    private static Texture bgColor;
    private static float x, y;
    
    private static float fadeCof = 0;
    private static final float MAX_FADE_PCT = 80, MIN_FADE_PCT = 20;
    public static void init()
    {
    }
    public static void update()
    {
    }
    public static void renderBG()
    {
        x = Camera.getX();
        y = Camera.getY();
        //bgColor.bind();
        //glColor3f(0, 190, 255);
        if(Hour.getHours() == 20 && Hour.getMinutes() < 50)
        {
            //anochecer
            fadeCof = (MAX_FADE_PCT-MIN_FADE_PCT)/100*((float)(Hour.getMinutes())/50)+MIN_FADE_PCT/100;
        }
        else if(Hour.getHours() == 6 && Hour.getMinutes() < 50)
        {
            //amanecer
            fadeCof = (MAX_FADE_PCT-MIN_FADE_PCT)/100*((float)(50-Hour.getMinutes())/50)+MIN_FADE_PCT/100;
        }
        else if(Hour.getHours() < 20 && Hour.getHours() >= 6)
        {
            fadeCof = MIN_FADE_PCT/100;
        } 
        else
        {
            fadeCof = MAX_FADE_PCT/100;
        }
        
        glColor4f(0.0f, 0.74f, 1.0f, (float)(1.0f - fadeCof*0.85f));
        
        glDisable(GL_TEXTURE_2D);
        glBegin(GL_QUADS);
        {
            glVertex2f(x, y);
            glVertex2f(x+Display.getWidth(), y);
            glVertex2f(x+Display.getWidth(), y+Display.getHeight());
            glVertex2f(x, y+Display.getHeight());
        }
        glEnd();
        glEnable(GL_TEXTURE_2D);
    }
    public static void renderDarkness()
    {
        //Black & Alpha Mod
        glDisable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE);
        
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f - fadeCof * 0.90f);
        
        glBegin(GL_QUADS);
        {
            glVertex2f(x, y);
            glVertex2f(x+Display.getWidth(), y);
            glVertex2f(x+Display.getWidth(), y+Display.getHeight());
            glVertex2f(x, y+Display.getHeight());
        }
        glEnd();
        glEnable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }
    
    
    public static void setFade(float FadeCof){fadeCof = FadeCof;}
    public static float getFade(){return fadeCof;}
}

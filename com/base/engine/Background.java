/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Miguel_F
 */
public class Background {
    private static Texture bgColor;
    private static int width = 0, height = 0;
    private static float x, y;
    
    private static float fadeCof = 0;
    private static final float MAX_FADE_PCT = 80, MIN_FADE_PCT = 0;
    
    public static void init(int Width, int Height)
    {
        height = Height;
        width = Width;
        //bgColor = new Texture();
    }
    public static void update(float X, float Y)
    {
        x = X*16;
        y = Y*16;
    }
    public static void renderBG()
    {
        bgColor.bind();
        glBegin(GL_QUADS);
        {
            glVertex2f(x, y);
            glVertex2f(x+width, y);
            glVertex2f(x+width, y+height);
            glVertex2f(x, y+height);
        }
        glEnd();
    }
    public static void renderDarkness()
    {
        glBlendFunc(GL_SRC_ALPHA, GL_ONE);//TEST
        //Black & Alpha Mod
        glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
        glBegin(GL_QUADS);
        {
            glVertex2f(x, y);
            glVertex2f(x+width, y);
            glVertex2f(x+width, y+height);
            glVertex2f(x, y+height);
        }
        glEnd();
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);//TEST
    }
    
    
    public static void setFade(float FadeCof){fadeCof = FadeCof;}
    public static float getFade(){return fadeCof;}
}

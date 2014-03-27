/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Miguel_F
 */
public class MoveBar extends Element{
    private final float cofValue;
    private final Texture barTex;
    
    public MoveBar(Menu menu, float x, float y, float sx, float sy, float cofValue)
    {
        super(x, y, menu);
        setSX(sx*16);
        setSY(sy*16);
        this.cofValue = cofValue;
        barTex = getTexture("bar");
        loadTexture("barBG");
    }
    @Override
    protected void update()
    {
        
    }
    
    @Override
    protected void render()
    {
        //BackGround
	glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            
            Color.white.bind();
            tex.bind();
            
            glBegin(GL_QUADS);
            {
                glTexCoord2f(1,1);
                glVertex2f(0,0);
                glTexCoord2f(0,1);
                glVertex2f(sx,0);
                glTexCoord2f(0,0);
                glVertex2f(sx,sy);
                glTexCoord2f(1,0);
                glVertex2f(0,sy);
            }
            glEnd();
        }
        glPopMatrix();
        
        //Bar
        glPushMatrix();
        {
            glTranslatef(x*16+((sx-barTex.getImageWidth())*cofValue), y*16, 0);
            
            Color.white.bind();
            barTex.bind();
            
            glBegin(GL_QUADS);
            {
                glTexCoord2f(1,1);
                glVertex2f(0,0);
                glTexCoord2f(0,1);
                glVertex2f(barTex.getImageWidth(),0);
                glTexCoord2f(0,0);
                glVertex2f(barTex.getImageWidth(), barTex.getImageHeight());
                glTexCoord2f(1,0);
                glVertex2f(0,barTex.getImageHeight());
            }
            glEnd();
        }
        glPopMatrix();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import com.base.engine.Font;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Miguel_F
 */
public class Text {
    Font font;
    public float x,y;
    public String text;
    
    public Text(String text, float x, float y)
    {
        this.x = x;
        this.y = y;
        font = new Font("Narkisim", 16, 16);
        this.text = text;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            font.drawString(1, text);
        }
        glPopMatrix();
    }
}

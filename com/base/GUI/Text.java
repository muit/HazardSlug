/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import com.base.engine.TrueTypeFont;
import static com.base.engine.TrueTypeFont.ALIGN_CENTER;
import static com.base.engine.TrueTypeFont.ALIGN_LEFT;
import static com.base.engine.TrueTypeFont.ALIGN_RIGHT;
import java.awt.Font;
import org.newdawn.slick.Color;

/**
 *
 * @author Miguel_F
 */
public class Text extends Thread{
    
    TrueTypeFont font;
    String text;
    Color col;
    float x, y;
    private int format;
    public static final int RIGHT = 0, 
        CENTER = 1, 
        LEFT = 2;
    
    public Text(String text, float x, float y, int format)
    {
        this.text = text;
        this.x=x;
        this.y=y;
        this.format = format;
        this.col = Color.white;
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);

        /* load font from a .ttf file
        try {
        InputStream inputStream = ResourceLoader.getResourceAsStream("myfont.ttf");

        Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        awtFont = awtFont.deriveFont(24f); // set font size
        font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }  
        */
    }
    public Text(String text, float x, float y, int format, Color col)
    {
        this.text = text;
        this.x=x;
        this.y=y;
        this.format = format;
        this.col = col;
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
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
    public void update()
    {
    }
    public void render()
    {
        col.bind();
        switch(format)
        {
            case RIGHT:
                font.drawString(x*16, y*16, text, 1, 1, ALIGN_RIGHT);
                break;
            case CENTER:
                font.drawString(x*16, y*16, text, 1, 1, ALIGN_CENTER);
                break;
            case LEFT:
                font.drawString(x*16, y*16, text, 1, 1, ALIGN_LEFT);
                break;
        }
    }
    
    /*
    Font font;
    public float x,y;
    public String text;
    
    public Text(String text, float x, float y)
    {
        this.x = x;
        this.y = y;
        font = new Font("font", 16, 16);
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
        font.render("Hey", 18, 3.0f,3.0f);
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import com.base.game.text.Log;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;

/**
 *
 * @author Miguel_F
 */
public class Box extends Element{
    private boolean state, blocked;
    private boolean clickUsed, mouseInUsed, mouseOutUsed;
    private int id;
    private int frame;
    
    public Box(Menu menu, float x, float y, int id)
    {
        super(x,y, 0.5f, 0.5f);
        this.menu = menu;
        this.gui = null;
        this.id = id;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        loadTexture("box");
    }
    public Box(GUI gui, float x, float y, int id)
    {
        super(x,y, 0.5f, 0.5f);
        this.gui = gui;
        this.menu = null;
        this.id = id;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        loadTexture("box");
    }
    
    @Override
    protected void render()
    {
        //ERROR: No se ejecuta Update ni Render
        if(state)
            frame = 0;
        else
            frame = 1;
        
	glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            
            Color.white.bind();
            tex.bind();
            
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0.5f+0.5f*frame,1);
                glVertex2f(0,0);
                glTexCoord2f(0.5f*frame,1);
                glVertex2f(sx,0);
                glTexCoord2f(0.5f*frame,0);
                glVertex2f(sx,sy);
                glTexCoord2f(0.5f+0.5f*frame,0);
                glVertex2f(0,sy);
            }
            glEnd();
        }
        glPopMatrix();
    }
    
    public void click()
    {
        if (!clickUsed)
        {
            if(!blocked)
                state = !state;
            
            clickUsed = true;
        }
        else if(clickUsed)
        {
            clickUsed = false;
        }
    }

    @Override
    protected void doAction()
    {
        if(menu != null)
            menu.boxDoAction(this);
        else if(gui != null)
            gui.boxDoAction(this);
    }

    @Override
    protected void update()
    {
        int mouseX = Mouse.getX()/16;
        int mouseY = Mouse.getY()/16;

        if(mouseX >= getX() && mouseX <= getX()+getSX())
            if(mouseY >= getY() && mouseY <= getY()+getSY())
            {
                if(Mouse.isButtonDown(0))
                    click();

                if(!mouseInUsed)
                {
                    boxIN();
                    if(menu != null)
                        menu.mouseOverBox(this);
                    else if(gui != null)
                        gui.mouseOverBox(this);
                    mouseInUsed=true;
                }
                mouseOutUsed = false;
            }
            else
            {
                if(!mouseOutUsed)
                {
                    boxOUT();
                    mouseOutUsed=true;
                }
                mouseInUsed = false;
            }
    }
    private void boxIN()
    {
        System.out.println("IN "+state);
    }
    private void boxOUT()
    {
        System.out.println("pin "+state);
    }
    
    public void setState(boolean state)
    {
        this.state = state;
    }
    public boolean getState()
    {
        return state;
    }
    
    public void setBlocked(boolean blocked)
    {
        this.blocked = blocked;
    }
    public boolean getBlocked()
    {
        return blocked;
    }
    
    public int getElementId()
    {
        return id;
    }
}

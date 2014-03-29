/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

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
    
    public Box(Menu menu, float x, float y, boolean state, int id)
    {
        super(x,y, menu);
        this.id = id;
        this.state = state;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        loadTexture("box");
    }
    public Box(GUI gui, float x, float y, boolean state, int id)
    {
        super(x,y, gui);
        this.id = id;
        this.state = state;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        loadTexture("box");
    }
    
    @Override
    protected void render()
    {
        if(state)
            frame = 0;
        else
            frame = 1;
        
        sx = tex.getImageWidth()/2;
        sy = tex.getImageHeight();
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
        if(!blocked)
            state = !state;
    }

    @Override
    protected void doAction(String action)
    {
        switch(action)
        {
            case "click":
                if(menu != null)
                    menu.boxDoAction(this);
                else if(gui != null)
                    gui.boxDoAction(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void update()
    {
        int mouseX = Mouse.getX()/16;
        int mouseY = Mouse.getY()/16;

        if(mouseX >= getX() && mouseX <= getX()+getSX()/16-1)
            if(mouseY >= getY() && mouseY <= getY()+getSY()/16-1)
            {
                while (Mouse.next()){
                    if (Mouse.getEventButtonState()) {
                        if (Mouse.getEventButton() == 0) {
                            click();
                        }
                    }else {
                        if (Mouse.getEventButton() == 0) {
                        }
                    }
                }

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
    }
    private void boxOUT()
    {
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

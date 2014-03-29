/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import org.lwjgl.input.Mouse;
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
    private float cofValue;
    private boolean blocked;
    private boolean mouseInUsed, mouseOutUsed;
    private final Texture barTex;
    private final int id;
    
    public MoveBar(Menu menu, float x, float y, float sx, float sy, float cofValue, int id)
    {
        super(x, y, menu);
        setSX(sx*16);
        setSY(sy*16);
        this.id = id;
        mouseInUsed = false;
        mouseOutUsed = true;
        blocked = false;
        this.cofValue = cofValue;
        barTex = getTexture("bar");
        loadTexture("barBG");
    }
    
    public void click()
    {
        if(!blocked)
        {
            float mouseInBarX = (float)(Mouse.getX() - getX()*16)/16;
            
            cofValue = mouseInBarX/(getSX()/16);
            System.out.println(cofValue);
        }
    }

    @Override
    protected void doAction(String action)
    {
        switch(action)
        {
            case "click":
                if(menu != null)
                    menu.moveBarDoAction(this);
                else if(gui != null)
                    gui.moveBarDoAction(this);
                break;
            default:
                break;
        }
    }
    @Override
    protected void update()
    {
        float mouseX = Mouse.getX()/16;
        float mouseY = Mouse.getY()/16;

        if(mouseX >= getX() && mouseX <= getX()+getSX()/16-1)
            if(mouseY >= getY() && mouseY <= getY()+getSY()/16-1)
            {
                if(Mouse.isButtonDown(0))
                    click();
                /*
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
                */

                if(!mouseInUsed)
                {
                    IN();
                    if(menu != null)
                        menu.mouseOverMoveBar(this);
                    else if(gui != null)
                        gui.mouseOverMoveBar(this);
                    mouseInUsed=true;
                }
                mouseOutUsed = false;
            }
            else
            {
                if(!mouseOutUsed)
                {
                    OUT();
                    mouseOutUsed=true;
                }
                mouseInUsed = false;
            }
    }
    private void IN()
    {
        System.out.println("IN");
    }
    private void OUT()
    {
        System.out.println("OUT");
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
            glTranslatef((float)(x*16+(sx-sx*0.1)*cofValue), y*16, 0);
            
            Color.white.bind();
            barTex.bind();
            
            glBegin(GL_QUADS);
            {
                glTexCoord2f(1,1);
                glVertex2f(0,0);
                glTexCoord2f(0,1);
                glVertex2f((float)(sx*0.1),0);
                glTexCoord2f(0,0);
                glVertex2f((float)(sx*0.1),sy);
                glTexCoord2f(1,0);
                glVertex2f(0,sy);
            }
            glEnd();
        }
        glPopMatrix();
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

package com.base.GUI;

import org.lwjgl.input.Mouse;



public class Button extends Element{
    private boolean clickUsed, mouseInUsed, mouseOutUsed;
    private final String text;
    private Menu menu;
    private final int id;
    public Button(Menu menu, float x, float y, float sx, float sy, int id, String text)
    {
        super(x,y, sx, sy);
        this.menu = menu;
        this.gui = null;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        this.text = text;
        this.id = id;
    }

    public Button(GUI gui, float x, float y, float sx, float sy, int id, String text)
    {
        super(x,y, sx, sy);
        this.gui = gui;
        this.menu = null;
        clickUsed = false;
        mouseInUsed = false;
        mouseOutUsed = true;
        this.text = text;
        this.id = id;
    }

    public void click()
    {
        if (!clickUsed)
        {
            doAction();
            clickUsed = true;
        }
        else if(clickUsed)
        {
            setFrame(0);
            clickUsed = false;
        }
    }

    @Override
    protected void doAction()
    {
        if(menu != null)
            menu.buttonDoAction(this);
        else if(gui != null)
            gui.buttonDoAction(this);
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
                    mouseIN();
                    if(menu != null)
                        menu.mouseOverButton(this);
                    else if(gui != null)
                        gui.mouseOverButton(this);
                    mouseInUsed=true;
                }
                mouseOutUsed = false;
            }
            else
            {
                if(!mouseOutUsed)
                {
                    mouseOUT();
                    mouseOutUsed=true;
                }
                mouseInUsed = false;
            }
    }
    private void mouseIN()
    {
    }
    private void mouseOUT()
    {
    }
    public int getElementId()
    {
        return id;
    }
}

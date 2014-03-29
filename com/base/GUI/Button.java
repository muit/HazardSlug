package com.base.GUI;

import org.lwjgl.input.Mouse;



public class Button extends Element{
    private boolean mouseInUsed, mouseOutUsed;
    private final String text;
    private final int id;
    public Button(Menu menu, float x, float y, float sx, float sy, int id, String text)
    {
        super(x,y, menu);
        setSX(sx);
        setSY(sy);
        mouseInUsed = false;
        mouseOutUsed = true;
        this.text = text;
        this.id = id;
    }

    public Button(GUI gui, float x, float y, float sx, float sy, int id, String text)
    {
        super(x,y, gui);
        mouseInUsed = false;
        mouseOutUsed = true;
        this.text = text;
        this.id = id;
    }

    public void click()
    {
        doAction("click");
    }

    @Override
    protected void doAction(String action)
    {
        switch(action)
        {
            case "click":
                if(menu != null)
                    menu.buttonDoAction(this);
                else if(gui != null)
                    gui.buttonDoAction(this);
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

        if(mouseX >= getX() && mouseX <= getX()+getSX())
            if(mouseY >= getY() && mouseY <= getY()+getSY())
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

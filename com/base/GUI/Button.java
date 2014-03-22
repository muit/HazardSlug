package com.base.GUI;

import com.base.game.text.Log;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;



public class Button extends Element{
	private boolean clickUsed, mouseInUsed, mouseOutUsed;
	private String text;
	private Menu menu;
	public Button(Menu menu, float x, float y, float sx, float sy, int elementId, String text)
	{
            super(x,y, sx, sy, elementId);
            clickUsed = false;
            mouseInUsed = false;
            mouseOutUsed = true;
            this.text = text;
            this.menu = menu;
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
            menu.buttonDoAction(this);
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
                        menu.mouseOverButton(this);
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
            Log.consoleMessage("IN");
        }
        private void mouseOUT()
        {
            Log.consoleMessage("OUT");
        }
}

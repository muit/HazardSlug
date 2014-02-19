/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

/**
 *
 * @author Miguel_F
 */
public class Menu {
    private ArrayList<Element> elements;
	private int status;
	
	private final static int ST_INTRO = 0,
		ST_INTRO_MENU = 1,
		ST_MAIN_MENU = 2,
		ST_GAME_MENU = 3;
	
    public Menu()
    {
    	elements = new ArrayList<>();
    	setStatus(ST_INTRO);
    }
    
    public void setStatus(int id)
    {
    	status = id;
    	elements.clear();
    	
    	switch(status)
    	{
    		case ST_INTRO:
    			//Button 1
    			elements.add(new Button(this, 0, 0, 0,""));
    			break;
    		case ST_INTRO_MENU:
    			//Button 1
    			elements.add(new Button(this, 0, 20, 0,""));
    			//Button 2
    			elements.add(new Button(this, 0, 0, 1,""));
    			break;
    		case ST_MAIN_MENU:
    			break;
    		case ST_GAME_MENU:
    			break;
    		default:
    			break;
    	}
    }
    
    public void buttonDoAction(Button focus)
    {
    	//Button Actions depending on menu status
    	switch(status)
    	{
    		case ST_INTRO:
    			System.out.println("Cambio de estado");
    			setStatus(ST_INTRO_MENU);
    			break;
    		case ST_INTRO_MENU:
    			if(focus.getElementId()==0)
    			{
    				//Button 1
    				System.out.println("Se pulso Boton 1");
    			}
    			else if(focus.getElementId()==1)
    			{
    				//Button 2
    				System.out.println("Se pulso Boton 2");
    			}
    			break;
    		case ST_MAIN_MENU:
    			break;
    		case ST_GAME_MENU:
    			break;
    		default:
    			break;
    	}
    }
    
    public void update()
    {
    	if(Mouse.isButtonDown(0))
    	{
	    	int mouseX = Mouse.getX();
	    	int mouseY = Mouse.getY();
	    	for(int i = 0; i<elements.size(); i++)
	    	{
	    		Button btn = (Button)elements.get(i);
	    		if(mouseX >= btn.getX() && mouseX <= btn.getX()+btn.getSX())
	    			if(mouseY >= btn.getY() && mouseY <= btn.getY()+btn.getSY())
	    				btn.click();
	    	}
    	}
    }
    
    public void render()
    {
    	for(int i = 0; i<elements.size(); i++)
    	{
    		elements.get(i).render();
    	}
    }
}

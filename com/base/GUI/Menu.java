/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import java.util.ArrayList;

/**
 *
 * @author Miguel_F
 */
public class Menu {
    private ArrayList<Element> elements;
	
	private final static int 
		ST_INTRO = 0,
		ST_INTRO_MENU = 1,
		ST_MAIN_MENU = 2,
		ST_GAME_MENU = 3;
		
	private int status;
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
    			break;
    		case ST_INTRO_MENU:
    			elements.add(new Button(0, 0));
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
    	for(int i = 0; i<elements.size(); i++)
    	{
    		elements.get(i).update();
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

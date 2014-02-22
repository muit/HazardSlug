/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.base.engine.Camera;
import com.base.engine.Main;
import com.base.game.map.Block;

/**
 *
 * @author Miguel_F
 */
public class Menu {
    private ArrayList<Element> elements;
    private ArrayList<Block> scene;
    
	private int status;
	
	private final static int ST_INTRO = 0,
		ST_INTRO_MENU = 1,
		ST_MAIN_MENU = 2,
		ST_GAME_MENU = 3;
	
    public Menu()
    {
    	elements = new ArrayList<>();
    	scene = new ArrayList<>();
    	setStatus(ST_MAIN_MENU);
    }
    
    public void setStatus(int id)
    {
    	status = id;
    	elements.clear();
    	scene.clear();
    	switch(status)
    	{
    		case ST_INTRO:
    			//Button 1
    			elements.add(new Button(this, 0, 0, 2, 2, 0,""));
    			break;
    		case ST_INTRO_MENU:
    			//Button 1
    			elements.add(new Button(this, 0, 10, 2, 2, 0,"Nueva Partida"));
    			//Button 2
    			elements.add(new Button(this, 0, 0, 2, 2, 1,""));
    			break;
    		case ST_MAIN_MENU:
    			for(int i = 0; i<50; i++)
    				scene.add(new Block(i, 0, 3));
    			for(int i = 0; i<50; i++)
    				scene.add(new Block(i, 1, 3));
    			for(int i = 0; i<50; i++)
    				scene.add(new Block(i, 2, 3));
    			for(int i = 0; i<50; i++)
    				scene.add(new Block(i, 3, 1));
    			//Nueva Partida / Cargar///////////////////////////////////////////
    			elements.add(new Button(this, 0, 4, 3, 3, 0, "Nueva Partida"));
    			addPortal(0,4);
    			///////////////////////////////////////////////////////////////////
    			//Salir
    			elements.add(new Button(this, 10, 4, 3, 3, 1, "Nueva Partida"));
    			addPortal(10,4);
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
    			if(focus.getElementId()==0)
    			{
    				//Button 1
    				System.out.println("Nueva Partida");
    				Main.startGame();
    			}
    			else if(focus.getElementId()==1)
    			{
    				//Button 1
    				System.out.println("Saliendo");
    				Main.cleanUp();
    				System.exit(1);
    			}
    			break;
    		case ST_GAME_MENU:
    			break;
    		default:
    			break;
    	}
    }
    
    public void update()
    {
    	
    	for(int i = 0; i<scene.size(); i++)
    		scene.get(i).updateSpr();
    	
    	if(Mouse.isButtonDown(0))
    	{
	    	int mouseX = Mouse.getX()/16;
	    	int mouseY = Mouse.getY()/16;
	    	
	    	for(int i = 0; i<elements.size(); i++)
	    	{
	    		Button btn = (Button)elements.get(i);
	    		if(mouseX >= btn.getX() && mouseX <= btn.getX()+btn.getSX())
	    			if(mouseY >= btn.getY() && mouseY <= btn.getY()+btn.getSY())
	    				btn.click();
	    	}
    	}
    	Camera.setCamera(0*16, 0*16, Display.getWidth(), Display.getHeight());
    }
    
    public void render()
    {
    	for(int i = 0; i<scene.size(); i++)
    		scene.get(i).render();
    	for(int i = 0; i<elements.size(); i++)
    	{
    		elements.get(i).render();
    	}
    }
    public void getInput()
    {
    	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
    		Main.cleanUp();
    		System.exit(1);
        }
    }
    public void addPortal(int x, int y)
    {
    	scene.add(new Block(x, y, 44));
		scene.add(new Block(x, y+1, 44));
		scene.add(new Block(x, y+2, 44));
		
		scene.add(new Block(x+1, y, 45));
		scene.add(new Block(x+1, y+1, 45));
		scene.add(new Block(x+1, y+2, 45));
		scene.add(new Block(x+2, y, 45));
		scene.add(new Block(x+2, y+1, 45));
		scene.add(new Block(x+2, y+2, 45));
		
		scene.add(new Block(x+3, y, 44));
		scene.add(new Block(x+3, y+1, 44));
		scene.add(new Block(x+3, y+2, 44));
    }
}

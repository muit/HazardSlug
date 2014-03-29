/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.GUI;

import static com.base.GUI.Text.CENTER;
import static com.base.GUI.Text.LEFT;
import static com.base.GUI.Text.RIGHT;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.base.engine.Camera;
import com.base.engine.Main;
import com.base.game.Config;
import com.base.game.map.Block;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

/**
 *
 * @author Miguel_F
 */
public final class Menu {
    private final ArrayList<Element> elements;
    private final ArrayList<Block> scene;
    private final ArrayList<Text> texts;
    
    private int status;
    private final String version = "Beta 0.1.26";

    public final static int ST_INTRO = 0,
            ST_INTRO_MENU = 1,
            ST_MAIN_MENU = 2,
            ST_LOAD_MENU = 3,
            ST_OPTIONS_MENU = 4,
            ST_GAME_MENU = 5;
	
    public Menu()
    {
    	elements = new ArrayList<>();
    	scene = new ArrayList<>();
        texts = new ArrayList<>();
    	setStatus(ST_INTRO);
    }
    public Menu(int Status)
    {
    	elements = new ArrayList<>();
    	scene = new ArrayList<>();
        texts = new ArrayList<>();
    	setStatus(Status);
    }
    public void setStatus(int id)
    {
    	status = id;
    	elements.clear();
    	scene.clear();
        texts.clear();
    	switch(status)
    	{
            case ST_INTRO:
                setStatus(ST_INTRO_MENU);
                break;
            case ST_INTRO_MENU:
                //Button Inicio
                texts.add(new Text("Pulsar una tecla para continuar.", Display.getWidth()/32, 7, CENTER, Color.red));
                elements.add(new Button(this, 0, 0, Display.getWidth()/16, Display.getHeight()/16, 0,""));
                break;
                
            case ST_MAIN_MENU:
                texts.add(new Text(version, 0, 0, LEFT, 12, Color.white));
                
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 0, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 1, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 2, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 3, 1));
                //Nueva Partida ///////////////////////////////////////////////////
                texts.add(new Text("Nueva Partida", 2+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 2, 4, 3, 3, 0, "Nueva Partida"));
                addPortal(2,4);
                ///////////////////////////////////////////////////////////////////

                //Cargar Partida///////////////////////////////////////////////////
                texts.add(new Text("Cargar Partida", 12+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 12, 4, 3, 3, 1, "Cargar Partida"));
                addPortal(12,4);
                ///////////////////////////////////////////////////////////////////

                //Opciones/////////////////////////////////////////////////////////
                texts.add(new Text("Opciones", 22+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 22, 4, 3, 3, 2, "Opciones"));
                addPortal(22,4);
                ///////////////////////////////////////////////////////////////////

                //Salir////////////////////////////////////////////////////////////
                texts.add(new Text("Salir", 42+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 42, 4, 3, 3, 3, "Salir"));
                addPortal(42,4);
                ///////////////////////////////////////////////////////////////////
                break;
                
            case ST_LOAD_MENU:
                texts.add(new Text(version, 0, 0, LEFT, 12, Color.white));
                
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 0, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 1, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 2, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 3, 1));
                
                for(int e = 0; e<24; e++)
                    for(int i = 0; i<40; i++)
                        scene.add(new Block(5+i, 9+e, 5));
                
                //Volver///////////////////////////////////////////////////////////
                texts.add(new Text("Volver", 2+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 2, 4, 3, 3, 0, "Volver"));
                addPortal(2,4);
                ///////////////////////////////////////////////////////////////////
                break;
                
            case ST_OPTIONS_MENU:
                texts.add(new Text(version, 0, 0, LEFT, 12, Color.white));
                
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 0, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 1, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 2, 3));
                for(int i = 0; i<50; i++)
                    scene.add(new Block(i, 3, 1));
                
                for(int e = 0; e<24; e++)
                    for(int i = 0; i<40; i++)
                        scene.add(new Block(5+i, 9+e, 5));
                
                
                //Volver///////////////////////////////////////////////////////////
                texts.add(new Text("Volver", 2+2, 7, CENTER, Color.red));
                elements.add(new Button(this, 2, 4, 3, 3, 0, "Volver"));
                
                //VSync_Enabled
                texts.add(new Text("VSync activado:", 14, 30, RIGHT, 18, Color.white));
                elements.add(new Box(this, 15, 30, Config.getVSyncEnabled(), 0));
                //Fps
                texts.add(new Text("Fps:", 14, 28, RIGHT, 18, Color.white));
                elements.add(new MoveBar(this, 15, 28, 5, 1, (float)(Config.getFPS()-40)/210, 0));//Max 250 Fps, Min 40 Fps
                
                //Separator
                texts.add(new Text("----------------------------------------------------------------------------------------------------------", 5, 26, LEFT, 20, Color.white));
                
                //Sound_Enabled
                texts.add(new Text("Sonido activado:", 14, 24, RIGHT, 18, Color.white));
                elements.add(new Box(this, 15, 24, Config.getSoundEnabled(), 1));
                
                //Sound_Level
                texts.add(new Text("Volumen:", 14, 22, RIGHT, 18, Color.white));
                elements.add(new MoveBar(this, 15, 22, 5, 1, Config.getSoundValue()/100, 1));
                addPortal(2,4);
                ///////////////////////////////////////////////////////////////////
                break;
            case ST_GAME_MENU:
                break;
            default:
                break;
    	}
    }
    
    public void boxDoAction(Box focus)
    {
        switch(status)
    	{
            case ST_MAIN_MENU:
                if(focus.getElementId()==0)
                {
                    Config.setVSyncEnabled(!Config.getVSyncEnabled());
                }
                else if(focus.getElementId()==1)
                {
                    Config.setSoundEnabled(!Config.getSoundEnabled());
                }
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
                break;
            case ST_INTRO_MENU:
                if(focus.getElementId()==0)
                {
                    setStatus(ST_MAIN_MENU);
                    Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
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
                    //Button 2
                    System.out.println("Cargar Partida");
                    setStatus(ST_LOAD_MENU);
                }
                else if(focus.getElementId()==2)
                {
                    //Button 3
                    System.out.println("Opciones");
                    setStatus(ST_OPTIONS_MENU);
                }
                else if(focus.getElementId()==3)
                {
                    //Button 4
                    System.out.println("Saliendo");
                    Main.cleanUp();
                    System.exit(1);
                }
                break;
            case ST_LOAD_MENU:
                if(focus.getElementId()==0)
                {
                    System.out.println("Inicio");
                    setStatus(ST_MAIN_MENU);
                }
                break;
            case ST_OPTIONS_MENU:
                if(focus.getElementId()==0)
                {
                    System.out.println("Inicio");
                    setStatus(ST_MAIN_MENU);
                }
                break;
            case ST_GAME_MENU:
                break;
            default:
                break;
    	}
    }
    public void moveBarDoAction(MoveBar focus)
    {
        switch(status)
    	{
            default:
                break;
    	}
    }
    
    
    public void mouseOverBox(Box focus)
    {
        switch(status)
    	{
            default:
                break;
    	}
    }
    public void mouseOverButton(Button focus)
    {
        switch(status)
    	{
            default:
                break;
    	}
    }
    public void mouseOverMoveBar(MoveBar focus)
    {
        switch(status)
    	{
            default:
                break;
    	}
    }
    
    public void update()
    {
        for(Block bl : scene)
            bl.updateSpr();
        
        for(int i=0; i<elements.size(); i++)
            elements.get(i).update();
        
        for(Text tx : texts)
            tx.update();
        
        Camera.setCamera(0*16, 0*16, Display.getWidth(), Display.getHeight());
    }
    public void render()
    {
    	for(Block bl : scene)
            bl.render();
        
        for (Element el : elements)
            el.render();
        
        for(Text tx : texts)
            tx.render();
    }
    
    public void getInput()
    {
        switch(status)
    	{
            case ST_INTRO:
                break;
            case ST_INTRO_MENU:
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    Main.cleanUp();
                    System.exit(1);
                }
                else if(Keyboard.getEventKeyState())
                {
                    setStatus(ST_MAIN_MENU);
                    Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
                }
                
                break;
            case ST_MAIN_MENU:
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    Main.cleanUp();
                    System.exit(1);
                }
                break;
            case ST_LOAD_MENU:
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    setStatus(ST_MAIN_MENU);
                }
                break;
            case ST_OPTIONS_MENU:
                if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
                {
                    setStatus(ST_MAIN_MENU);
                }
                break;
            case ST_GAME_MENU:
                break;
            default:
                break;
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

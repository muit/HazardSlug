/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

//import com.base.GUI.*;
import com.base.GUI.Menu;
import com.base.game.Game;
import com.base.game.Time;
import com.base.game.gameobject.Unit;
import com.base.game.map.Block;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Miguel Fernandez
 */
public class Main {
    private static Game game;
    private static Menu menu;
    private static boolean done, menuEnabled;
    public static void main(String[] args)
    {
        done = false;
        
        initDisplay();
        initGL();
        initMenu();
        
        gameLoop();
        
        cleanUp();
    }
    
    public static ArrayList<Unit> sphereCollide(float x, float y, float radius)
    {
        return game.sphereCollide(x, y, radius);
    }
    public static ArrayList<Block> sphereMapCollide(float x, float y, float radius)
    {
        return game.sphereMapCollide(x, y, radius);
    }
    
    public static ArrayList<Unit> getPlayers()
    {
        return game.getPlayers();
    }
    public static Game getGame()
    {
        return game;
    }
    
    private static void getInput()
    {
    	if(menuEnabled)
    	{
    		menu.getInput();
        }
    	else
    	{
            game.getInput();
    	}
        
    }
    private static void update()
    {
        game.update();
    }
    private static void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        //Draw/////////////////////
        game.render();
        ///////////////////////////
        Display.update();
        Display.sync(60);
    }
    private static void updateMenu()
    {
        menu.update();
    }
    private static void renderMenu()
    {
    	glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        //Draw/////////////////////
        menu.render();
        ///////////////////////////
        Display.update();
        Display.sync(40);
    }
    private static void gameLoop()
    {
        Time.init();
        while(!Display.isCloseRequested() && !done)
        {
            Time.update();
            getInput();
            if(menuEnabled)
            {
            	updateMenu();
            	renderMenu();
            }
            else
            {
	            update();
	            render();
            }
        }
    }
    
    private static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Keyboard.create();
            Mouse.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void initGL()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glEnable(GL_TEXTURE_2D);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        // enable alpha blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        
        glClearColor(0, 0, 0, 0);
    }
    private static void initGame()
    {
        game = new Game();
    }
    private static void initMenu()
    {
    	menu = new Menu();
    	menuEnabled = true;
    }
    public static void cleanUp()
    {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    public static void heavyClose()
    {
        done = true;
    }
    public static void spawnGUI(int id)
    {
        //GUI.spawnGUI(id);
    }
    public static void setMenuEnabled(boolean enabled)
    {
    	menuEnabled = enabled;
    }
    public static void startGame()
    {
    	menuEnabled = false;
        initGame();
    }
    public static void exitGame()
    {
    	menuEnabled = true;
    	initMenu();
    }
}

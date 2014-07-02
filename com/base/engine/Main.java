/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

//import com.base.GUI.*;
import com.base.GUI.Menu;
import com.base.GUI.Menu.State;
import static com.base.GUI.Menu.State.ST_MAIN_MENU;
import com.base.engine.lightning.Lighting;
import com.base.game.Config;
import com.base.game.Game;
import com.base.game.Time;
import com.base.game.gameobject.Unit;
import com.base.game.map.Block;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

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
        Lighting.init();
        initMenu();
        
        
        try{
            gameLoop();
        }
        catch(StackOverflowError e){
            initMenu(State.ST_ERROR_PAGE, e+"");
        }
        
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
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        //Draw/////////////////////
        game.render();
        ///////////////////////////
        Display.update();
        Display.sync(Config.getFPS());
    }
    private static void updateMenu()
    {
        menu.update();
    }
    private static void renderMenu()
    {
    	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
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
        
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        try{
            while(!Display.isCloseRequested() && !done)
            {
                if(menuEnabled)
                { 
                    Time.update();
                    menu.getInput();
                    updateMenu();
                    renderMenu();
                    if(!menuEnabled)
                        menu = null;
                }
                else
                {
                    Time.update();
                    game.getInput();
                    update();
                    render();
                    if(menuEnabled)
                        game = null;
                }
            }
        }catch(OutOfMemoryError e){
            //Security To Error
            MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
            System.gc();
            long maxMemory = heapUsage.getMax() / (1024*1024);//(1024*1024) = Mb
            long usedMemory = heapUsage.getUsed() / (1024*1024);
            initMenu(State.ST_ERROR_PAGE, e+" : Memory Used: " + usedMemory + "Mb/" + maxMemory + "Mb");
        }
    }
    
    private static void initDisplay()
    {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
            Keyboard.create();
            Mouse.create();
            Display.setVSyncEnabled(Config.getVSyncEnabled());
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void initGL()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        //glShadeModel(GL_SMOOTH); No Needed
        
        // enable alpha blending
        glEnable(GL_ALPHA);
        glEnable(GL_BLEND);
        glEnable(GL_DEPTH);
        glDepthFunc(GL_EQUAL);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    private static void initGame()
    {
        Lighting.reset();
        game = new Game();
        menuEnabled = false;
    }
    private static void initMenu()
    {
        Lighting.reset();
    	menu = new Menu();
    	menuEnabled = true;
    }
    private static void initMenu(State st)
    {
        Lighting.reset();
    	menu = new Menu(st);
    	menuEnabled = true;
    }
    private static void initMenu(State st, String err_message)
    {
        
        Lighting.reset();
    	menu = new Menu(st, err_message);
    	menuEnabled = true;
    }
    public static void cleanUp()
    {
        menu = null;
        game = null;
        Lighting.cleanup();
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    public static void Close()
    {
        done = true;
        cleanUp();
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
        initGame();
    }
    public static void exitGame()
    {
    	initMenu(ST_MAIN_MENU);
    }
}

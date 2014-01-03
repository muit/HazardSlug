/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.data.DataBase;
import com.base.engine.Camera;
import com.base.engine.GameObject;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.item.Cube;
import com.base.game.map.Map;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Miguel
 */
public class Game 
{
    private final ArrayList<GameObject> objects;
    private final Map map = new Map(7);
    private final ArrayList<GameObject> remove;
    private final Player player;
    private final Camera cam = new Camera();
    protected DataBase db = new DataBase();
    
    public Game()
    {
        objects = new ArrayList<>();
        remove = new ArrayList<>();
        
        player = new Player(Display.getWidth() / 2 - Player.SIZE / 2, Display.getHeight() / 2 - Player.SIZE / 2);
        
        objects.add(player);
        //WORLD//////////////////////
        map.load();
        
        //ITEMS//////////////////////
        objects.add(new Cube((int)(Display.getWidth() / 2 - Player.SIZE / 2), 200, 3, this));
    }
    
    public void getInput()
    {  
        player.getInput();
    }
    
    public void update()
    {
        map.update();
        for(GameObject go : objects)
        {
            if(!go.getRemove())
                go.update();
            else
            {
                remove.add(go);
            }
        }
        for(GameObject go : remove)
        {
            objects.remove(go);
        }
        remove.clear();
        cam.setCamera((int)(player.getX()+player.getSX()/2-Display.getWidth()/2),(int)(player.getY()+player.getSY()/2-Display.getHeight()/2), Display.getWidth(), Display.getHeight());
    }
    
    public void render()
    {
        map.render();
        for(GameObject go : objects)
            go.render();
    }
    
    private void removeObject(GameObject target)
    {
        for(GameObject go : objects)
            if(go == target)
                objects.remove(go);
    }
    public DataBase db()
    {
        return db;
    }
    public Player getPlayer()
    {
        return player;
    }
}

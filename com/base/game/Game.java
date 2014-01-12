/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.data.DataBase;
import com.base.engine.Camera;
import com.base.engine.GameObject;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.enemy.Babosa_Azul;
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
    private final Map map = new Map(3, this);
    private final ArrayList<GameObject> remove;
    private final Player player;
    private final Camera cam = new Camera();
    protected DataBase db = new DataBase();
    
    public Game()
    {
        objects = new ArrayList<>();
        remove = new ArrayList<>();
        player = new Player(-5, 250, this);
        
        objects.add(player);
        objects.add(new Babosa_Azul(1, 230, 1));
        //WORLD//////////////////////
        
        //ITEMS//////////////////////
        objects.add(new Cube( 1, 250, 3, this));
        objects.add(new Cube(-1, 250, 4, this));
    }
    
    public void getInput()
    {  
        player.getInput();
    }
    
    public void update()
    {
        map.update((int)player.getX(), Display.getWidth()/16);
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
        cam.setCamera((int)(player.getX()*16+player.getSX()/2-Display.getWidth()/2),(int)(player.getY()*16+player.getSY()/2-Display.getHeight()/2), Display.getWidth(), Display.getHeight());
    }
    
    public void render()
    {
        map.render((int)player.getX(), Display.getWidth()/16);
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
    public Map getMap()
    {
        return map;
    }
    public ArrayList<GameObject> sphereCollide(float x, float y, float radius)
    {
        ArrayList<GameObject> res = new ArrayList<>();
        for(GameObject go : objects)
        {
            if(Util.dist(go.getX(), go.getY(), x, y) <= radius)
                res.add(go);
        }
        return res;
    }
}

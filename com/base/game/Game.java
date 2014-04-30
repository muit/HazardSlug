/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import com.base.data.DataBase;
import com.base.engine.Background;
import com.base.engine.Camera;
import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.Unit;
import com.base.engine.Effect;
import com.base.engine.Hour;
import com.base.game.gameobject.item.Cube;
import com.base.game.gameobject.npc.Babosa_Azul;
import com.base.game.map.Block;
import com.base.game.map.Map;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Miguel
 */
public class Game 
{
    private final ArrayList<GameObject> objects;
    private final ArrayList<Unit> entitys;
    private final ArrayList<Effect> effects;
    private final MapManager mapMg;
    private final Map map;
    private final ArrayList<GameObject> remove;
    private final ArrayList<Effect> removeffect;
    private final Player player;
    //private final Quadtree quad;
    protected DataBase db = new DataBase();
    
    public Game()
    {
        Hour.reset();
        mapMg = new MapManager(3, this);
        map = mapMg.getMap();
        
        objects = new ArrayList<>();
        entitys = new ArrayList<>();
        effects = new ArrayList<>();
        remove = new ArrayList<>();
        removeffect = new ArrayList<>();
        
        //quad = new Quadtree(0, new Rectangle(0,0,Display.getWidth(),Display.getHeight()));
        
        player = new Player(-5, 250);
        
        entitys.add(player);
        entitys.add(new Babosa_Azul(1, 235, 1));
        //WORLD//////////////////////
        
        //ITEMS//////////////////////
        objects.add(new Cube( 1, 250, 3, this));
        objects.add(new Cube(-1, 250, 4, this));
    }
    
    public void getInput()
    {  
    	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            Main.exitGame();
        }
        player.getInput();
    }
    
    public void update()
    {
        Hour.update();
        
        map.update((int)player.getX(), Display.getWidth()/16);
        //quad.clear();
        for(GameObject go : objects)
        {
            if(!go.getRemove())
                go.update();
            else
            {
                remove.add(go);
            }
        }
        
        for(Unit go : entitys)
        {
            if(!go.getRemove())
            {
                go.update();
            }
            else
            {
                remove.add(go);
            }
        }
        
        for(Effect go : effects)
        {
            if(!go.getRemove())
                go.update();
            else
            {
                removeffect.add(go);
            }
        }
        
        for(GameObject go : remove)
        {
            objects.remove(go);
        }
        remove.clear();
        
        for(Effect go : removeffect)
        {
            effects.remove(go);
        }
        removeffect.clear();
        
        Camera.setCamera((int)(player.getX()*16+player.getSX()/2-Display.getWidth()/2),(int)(player.getY()*16+player.getSY()/2-Display.getHeight()/2), Display.getWidth(), Display.getHeight());
    }
    
    public void render()
    {
        Background.renderBG();
        map.render((int)player.getX(), Display.getWidth()/16);
        for(GameObject go : objects)
            go.render();
        for(Unit go : entitys)
            go.render();
        for(Effect go : effects)
            go.render();
        Background.renderDarkness();
    }
    @SuppressWarnings("unused")
    private void removeObject(GameObject target)
    {
        for(GameObject go : objects)
            if(go == target)
                objects.remove(go);
    }
    @SuppressWarnings("unused")
	private void removeUnit(Unit target)
    {
        if(entitys.contains(target))
            entitys.remove(target);
    }
    @SuppressWarnings("unused")
    private void removeEffect(Effect target)
    {
        if(effects.contains(target))
            effects.remove(target);
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
    public ArrayList<Unit> sphereCollide(float x, float y, float radius)
    {
        ArrayList<Unit> res = new ArrayList<>();
        for(Unit go : entitys)
        {
            if(Util.dist(go.getX(), go.getY(), x, y) <= radius)
                res.add(go);
        }
        return res;
    }
    public ArrayList<Block> sphereMapCollide(float x, float y, float radius)
    {
        return map.sphereMapCollide(x, y, radius);
    }
    
    public ArrayList<Unit> getPlayers()
    {
        ArrayList<Unit> players = new ArrayList<>();
        for (int i=0; i<entitys.size(); i++)
        {
            if(entitys.get(i).isPlayer())
            {
                players.add(entitys.get(i));
            }
        }
        return players;
    }
    public void createEffect(Unit me, int id, float x, float y)
    {
        effects.add(new Effect(me, id, x, y));
    }
    public void createEffect(Unit me, int id, float x, float y, float sx, float sy, float speed)
    {
        effects.add(new Effect(me, id, x, y, sx, sy, speed));
    }
    public void createEffect(Unit me, int id, float x, float y, Unit target, float speed, boolean follow)
    {
        effects.add(new Effect(me, id, x, y, target, speed, follow));
    }
    public void deleteEffect(Effect effect)
    {
        effects.remove(effect);
    }
}
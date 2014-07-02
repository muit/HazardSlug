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
import com.base.engine.lightning.Lighting;
import com.base.game.gameobject.item.Cube;
import com.base.game.gameobject.npc.Babosa_Azul;
import com.base.game.map.Block;
import com.base.game.map.Map;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

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
    private final ArrayList<GameObject> removeObjects;
    private final ArrayList<Unit> removeEntitys;
    private final ArrayList<Effect> removeEffects;
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
        removeObjects = new ArrayList<>();
        removeEntitys = new ArrayList<>();
        removeEffects = new ArrayList<>();
        
        //quad = new Quadtree(0, new Rectangle(0,0,Display.getWidth(),Display.getHeight()));
        
        player = new Player(-5, 240);
        Lighting.addLight(-5, 240, 1.0f, 1.0f, 0.0f);
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
                removeObjects.add(go);
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
                removeEntitys.add(go);
            }
        }
        
        for(Effect go : effects)
        {
            if(!go.getRemove())
                go.update();
            else
            {
                removeEffects.add(go);
            }
        }
        
        for(GameObject go : removeObjects)
        {
            objects.remove(go);
        }
        removeObjects.clear();
        
        for(Unit go : removeEntitys)
        {
            objects.remove(go);
        }
        removeObjects.clear();
        
        for(Effect go : removeEffects)
        {
            effects.remove(go);
        }
        removeEffects.clear();
        
        Camera.setCamera((int)(player.getX()*16+player.getSX()/2-Display.getWidth()/2),(int)(player.getY()*16+player.getSY()/2-Display.getHeight()/2), Display.getWidth(), Display.getHeight());
    }
    
    public void render()
    {
        Background.renderBG();
        
        //Lighting.render();
        map.render((int)player.getX(), Display.getWidth()/16);
        for(GameObject go : objects)
            go.render();
        for(Unit go : entitys)
            go.render();
        for(Effect go : effects)
            go.render();
        //Background.renderDarkness();
    }
    @SuppressWarnings("unused")
    public void removeObject(GameObject target)
    {
        for(GameObject go : objects)
            if(go == target)
                objects.remove(go);
    }
    @SuppressWarnings("unused")
    public void removeUnit(Unit target)
    {
        if(entitys.contains(target))
            entitys.remove(target);
    }
    @SuppressWarnings("unused")
    public void removeEffect(Effect target)
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
    public ArrayList<GameObject> getGameObjects()
    {
        return objects;
    }
    public ArrayList<Unit> getEntitys()
    {
        return entitys;
    }
    public ArrayList<Effect> getEffects()
    {
        return effects;
    }
    public void createEffect(Unit me, int id, float x, float y)
    {
        effects.add(new Effect(me, id, x, y));
    }
    public void createEffect(Unit me, int id, float x, float y, float sx, float sy, float speed)
    {
        effects.add(new Effect(me, id, x, y, sx, sy, speed));
    }
    public void createEffect(Unit me, int id, float x, float y, Vector2f directionPos, float speed)
    {
        effects.add(new Effect(me, id, x, y, directionPos, speed));
    }
    public void createEffect(Unit me, int id, float x, float y, Unit target, float speed, boolean follow)
    {
        effects.add(new Effect(me, id, x, y, target, speed, follow));
    }
    public void deleteEffect(Effect effect)
    {
        effects.remove(effect);
    }
    
    public void addEntity(Unit entity)
    {
        entitys.add(entity);
    }
    public void addObject(GameObject go)
    {
        objects.add(go);
    }
}
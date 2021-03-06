/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.engine.Physics;
import com.base.game.Game;
import com.base.game.gameobject.Player;
import static com.base.game.gameobject.item.Item.db;

/**
 *
 * @author Miguel
 */
public class Cube extends Item {
    public static final float SIZE = 16;
    
    private Player player;
    private Game game;
    
    
    public Cube(int x, int y, int id, Game game)
    {
        this.player = game.getPlayer();
        this.game = game;
        init(id, x, y, 1.0f, 0.5f, 0, SIZE, SIZE);
        this.type = 4;
    }
    
    @Override
    public void pickUp()
    {
        System.out.println("Has cojido: " + name);
        if(player.addItem(this))
            remove = true;
        else
            System.out.println("Inventario lleno.");
    }
    @Override
    public void update()
    {
        if(getY()<=-16)
        {
            remove = true;
            return;
        }
        
        if(Physics.checkCollision(this, player)!=null)
        {
            gSpeed = 0;
            pickUp();
        }
        else
        {
            fisic();
        }
    }
    
    @Override
    protected float getGSpeed()
    {
        if(gSpeed<5)
            gSpeed+=0.2;
        return gSpeed;
    }
}

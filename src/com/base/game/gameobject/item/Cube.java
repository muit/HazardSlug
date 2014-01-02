/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.engine.Physics;
import com.base.game.Game;
import com.base.game.gameobject.Player;

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
        this.id = id;
        this.game = game;
        init(db.getItemName(id), x, y, 1.0f, 0.5f, 0, SIZE, SIZE);
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
        if(y<=100)
        {
            remove = true;
            return;
        }
        
        if(Physics.checkCollision(this, player))
        {
            gSpeed = 0;
            pickUp();
        }
        else
        {
            fisic(-1);
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

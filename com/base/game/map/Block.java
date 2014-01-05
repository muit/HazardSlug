/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.engine.Physics;
import com.base.game.Game;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.item.Item;

/**
 *
 * @author Miguel_F
 */
public class Block extends Item{
    public static final float SIZE = 16;
    
    public Block(int x, int y, int id)
    {
        this.id = id;
        init(db.getItemName(id), x, y, 1.0f, 0.5f, 0, SIZE, SIZE);
    }
    
    @Override
    public void update()
    {
    }
}
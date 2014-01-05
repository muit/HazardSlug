/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.enemy;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.gameobject.Enemy;

/**
 *
 * @author Miguel
 */
public class Babosa_Azul extends Enemy
{
    private static int SIZE = 16;
    public Babosa_Azul(float x, float y, int level)
    {
        super(level);
        init(type, x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE);
    }
    @Override
    protected void look()
    {
        GameObject[] objects = Main.sphereCollide(x, y, 7*16);
        for(GameObject go : objects)
            if(go.getType()==1)
                setTarget(go);
    }
    @Override
    protected void chase()
    {
    
    }
}

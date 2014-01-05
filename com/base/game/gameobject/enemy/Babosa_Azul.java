/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.enemy;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.gameobject.Enemy;
import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class Babosa_Azul extends Enemy
{
    public static final int SIZE = 16;
    public static final float DAMPING = 0.5f;
    
    public Babosa_Azul(float x, float y, int level)
    {
        super(level);
        MELE_RANGE = 3;
        init(type, x, y, 0.2f, 0.2f, 1.0f, SIZE, SIZE);
    }
    @Override
    protected void look()
    {
        ArrayList<GameObject> objects = Main.sphereCollide(x, y, 10);
        for(GameObject go : objects)
        {
            if(go.getType()==1)
            {
                setTarget(go);
            }
        }
    }
    @Override
    protected void justEnterCombat()
    {
            System.out.println("Zarigüella quiere sapatos pa comer!!");
    }
    
    
    @Override
    protected void chase()
    {
        float speedX = (getTarget().getX()-x);
        float speedY = (getTarget().getY()-y);
        
        float maxSpeed = getStats().getSpeed()*DAMPING;
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        x+=speedX;
        y+=speedY;
    }
    @Override
    protected void attack()
    {
        //if(Util.dist(x, y, target.getX(), target.getY()))
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.gameobject;

import com.base.engine.EffectSprite;
import com.base.game.Delay;
import com.base.game.EventsMap;
import com.base.game.Time;
import com.base.game.Util;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

/**
 *
 * @author Miguel_F
 */
public class Effect {
    
    protected boolean remove = false;
    enum Mode 
    {
        FOLLOW,
        GOTO,
        STATIC
    };
    
    private EventsMap events;
    private EffectSprite spr;
    private Unit target;
    private int id;
    private Mode mode;
    private float speed;
    private float x,y,x0,y0;
    private float x2,y2;
    protected Delay removeDelay = null;
    
    public Effect(int id, float x, float y)
    {
        mode = Mode.STATIC;
        this.id = id;
        this.x = x;
        this.y = y;
        this.target = null;
        spr = new EffectSprite(id);
    }
    public Effect(int id, float x, float y, float sx, float sy, float speed)
    {
        mode = Mode.GOTO;
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = sx;
        this.y2 = sy;
        this.speed = speed;
        this.target = null;
        spr = new EffectSprite(id);
        removeDelay = new Delay((int)(2000*speed));
        removeDelay.start();
    }
    public Effect(int id, float x, float y, Unit target, float speed, boolean follow)
    {
        if(follow)
            mode = Mode.FOLLOW;
        else
            mode = Mode.GOTO;
        
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = target.getX();
        this.y2 = target.getY();
        this.speed = speed;
        this.target = target;
        spr = new EffectSprite(id);
        removeDelay = new Delay(Util.random((int)(1000*(1+speed)), (int)(1000*(1+speed)*(1+speed+0.2f))));
        removeDelay.start();
    }
    
    public void update()
    {
        if(mode != Mode.STATIC && removeDelay.over()){
            remove = true;
            return;
        }
        switch(mode)
        {
            case FOLLOW:
                x2=target.getX();
                y2=target.getY();
                if(goToPos())
                    remove = true;
                break;
                
            case GOTO:
                if(goToPos())
                    remove = true;
                break;
                
            case STATIC:
                break;
                
            default:
                break;
        }
    }
    
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x*16, y*16, 0);
            spr.render();
        }
        glPopMatrix();
    }
    public boolean getRemove()
    {
        return remove;
    }
    private boolean goToPos()
    {
        float speedX = (x2-x);
        float speedY = (y2-y);
        
        float maxSpeed = speed;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        x+=speedX * getDelta();
        y+=speedY * getDelta();
        
        return x==x2 && y==y2;
    }
    
    public float getDelta()
    {
        return Time.getDelta();
    }
}

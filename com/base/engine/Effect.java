/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import com.base.game.Delay;
import com.base.game.Time;
import com.base.game.Util;
import com.base.game.gameobject.Unit;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Miguel_F
 */
public class Effect {
    
    protected boolean remove = false;
    enum Mode 
    {
        FOLLOW,
        GOTOPOSITION,
        GOTODIRECTION,
        STATIC
    };
    
    private EffectSprite spr;
    private Unit me, target;
    private int id;
    private Mode mode;
    private float speed;
    private double directionAlpha;
    private float x,y;
    private float x2,y2;
    protected Delay removeDelay = null;
    
    public Effect(Unit me, int id, float x, float y)
    {
        mode = Mode.STATIC;
        this.id = id;
        this.x = x;
        this.y = y;
        this.me = me;
        this.target = null;
        spr = new EffectSprite(id);
    }
    public Effect(Unit me, int id, float x, float y, float sx, float sy, float speed)
    {
        mode = Mode.GOTOPOSITION;
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = sx;
        this.y2 = sy;
        this.speed = speed;
        this.me = me;
        this.target = null;
        
        spr = new EffectSprite(id);
    }
    public Effect(Unit me, int id, float x, float y, Vector2f DirectionPos, float speed)
    {
        mode = Mode.GOTODIRECTION;
        this.id = id;
        this.x = x;
        this.y = y;
        this.speed = speed;
        directionAlpha = Math.PI + Math.atan2(-(DirectionPos.getY()-y), -(DirectionPos.getX()-x));
        
        this.me = me;
        this.target = null;
        removeDelay = new Delay(Util.random((int)(1000*(1+speed)), (int)(1000*(1+speed)*(1+speed+0.2f))));
        removeDelay.start();
        
        spr = new EffectSprite(id);
    }
    public Effect(Unit me, int id, float x, float y, Unit target, float speed, boolean follow)
    {
        if(follow){
            mode = Mode.FOLLOW;
            removeDelay = new Delay(Util.random((int)(1000*(1+speed)), (int)(1000*(1+speed)*(1+speed+0.2f))));
            removeDelay.start();
        }
        else
            mode = Mode.GOTOPOSITION;
        
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = target.getX();
        this.y2 = target.getY();
        this.speed = speed;
        this.me = me;
        this.target = target;
        spr = new EffectSprite(id);
    }
    
    public void update()
    {
        if((mode == Mode.FOLLOW || mode == Mode.GOTODIRECTION) && removeDelay.over()){
            remove = true;
            return;
        }
        switch(mode)
        {
            case FOLLOW:
                x2=target.getX();
                y2=target.getY();
                if(goToPos())
                {
                    me.DoCast(target, id+1000);
                    remove = true;
                }
                break;
                
            case GOTOPOSITION:
                if(goToPos())
                    remove = true;
                break;
            case GOTODIRECTION:
                goToDirection();
                for(Unit entity : Main.getGame().getEntitys())
                {
                    if(entity != me && entity.pointInside(x, y))
                    {
                        System.out.println("Yow");
                        //No se ejecuta la colision
                        me.DoCast(entity, id+1000);
                        remove = true;
                    }
                }
                break;
                
            case STATIC:
                break;
                
            default:
                break;
        }
        spr.update();
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
    	float speedFinal = speed * getDelta();
    	// arctg(m)  m->v2/v1 (v1, v2)->(x-x2), (y-y2)
    	double alpha = Math.PI + Math.atan2(-(target.getY()-y), -(target.getX()-x));
    	
    	x += speedFinal * Math.cos(alpha);
    	y += speedFinal * Math.sin(alpha);
    	
        return Util.distSqrt(x, y, x2, y2)< 1;
    }
    private void goToDirection()
    {
    	float speedFinal = speed * getDelta();
    	
    	x += speedFinal * Math.cos(directionAlpha);
    	y += speedFinal * Math.sin(directionAlpha);
    }
    
    public float getDelta()
    {
        return Time.getDelta();
    }
}

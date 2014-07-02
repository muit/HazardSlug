/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

/**
 *
 * @author Miguel
 */
public class Stats {
    private String name;
    private int health;
    private int maxHealth;
    private int energy;
    private int maxEnergy;
    private int invSpace;
    private int level;
    private int xp;
    private float speed;
    private boolean jumping;
    private boolean leveable;
    private boolean alive;
    private boolean trigger_JustDied = false;
    private float gravity;
    
    public Stats(int xp, boolean leveable)
    {
        this.leveable = leveable;
        if(leveable)
        {
            this.xp = xp;
            this.level = 1;
        }
        else
        {
            this.xp = -1;
            this.level = xp;
        }
        maxHealth = 10;
        invSpace = 12;
        speed = 0.1f;
        maxEnergy=10;
        name = "";
        health = maxHealth;
        energy = maxEnergy;
        alive = true;
        jumping=false;
        gravity = -0.0210f;
    }
    //XP////////////////////////////////////////////////////////////////////////
    public void addXp(int plus)
    {
        xp+=plus;
    }
    public int getXp()
    {
        return xp;
    }
    public void setXp(int xp)
    {
        this.xp = xp;
    }
    //LEVEL/////////////////////////////////////////////////////////////////////
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    //NAME//////////////////////////////////////////////////////////////////////
    public String getName()
    {
        if(!name.equals(""))
            return name;
        else
            return "Indefined Entity";
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    //HEALTH////////////////////////////////////////////////////////////////////
    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        if(health <= 0)
        {
            health = 0;
            alive = false;
            trigger_JustDied = true;
        }
        else
        {
            this.health = health;
        }
    }
    public boolean modifyHealth(int live)
    {
        int newhealth = health+live;
        if(newhealth <= 0)
        {
            health = 0;
            alive = false;
            trigger_JustDied = true;
            return true;
        }
        else
        {
            health = newhealth;
            return false;
        }
    }
    public int getMaxHealth()
    {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }
    public boolean hasJustDied(){
        if(trigger_JustDied){
            trigger_JustDied = false;
            return true;
        }
        return false;
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    public void kill()
    {
        alive = false;
        health = 0;
    }
    
    //ENERGY////////////////////////////////////////////////////////////////////
    public int getEnergy()
    {
        return energy;
    }
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }
    public int getMaxEnergy()
    {
        return maxEnergy;
    }
    public void setMaxEnergy(int maxEnergy)
    {
        this.maxEnergy = maxEnergy;
    }
    
    //INVSPACE//////////////////////////////////////////////////////////////////
    public int getInvSpace()
    {
        return invSpace;
    }
    public void setInvSpace(int invSpace)
    {
        this.invSpace = invSpace;
    }
    public boolean getJumping()
    {
        return jumping;
    }
    public void setJumping(boolean jumping)
    {
        this.jumping = jumping;
    }
    public float getSpeed()
    {
        return speed;
    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    //OTHER///////////////////////////////////////////////////////////////////////
    public boolean getLeveable()
    {
    	return leveable;
    }
    public float getGravity() 
    {
    	return gravity;
    }
}

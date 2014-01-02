/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.player;

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
    
    public Stats()
    {
        name= "";
        health = 0;
        maxHealth = 0;
        energy = 0;
        maxEnergy = 0;
        invSpace = 8;
        level = 1;
        xp = 0;
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
        return name;
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
        this.health = health;
    }
    public int getMaxHealth()
    {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
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
}

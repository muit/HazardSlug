/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

/**
 *
 * @author Miguel
 */
public class BaseNpc {
    private int id;
    private String name;
    private int relation;//0-friendly, 1-pasive enemy, 2-active enemy
    private int live;
    private int damage;
    private int armor;
    
    public BaseNpc(int id, String name, int relation, int live, int damage, int armor)
    {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.live = live;
        this.damage = damage;
        this.armor = armor;
    }
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getRelation()
    {
        return relation;
    }
    
    public int getLive()
    {
        return live;
    }
    public int getDamage()
    {
        return damage;
    }
    public int getArmor()
    {
        return armor;
    }
}

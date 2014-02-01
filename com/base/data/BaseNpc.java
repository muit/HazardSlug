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
    private float meleDist;
    private float rangeDist;
    private float detectionDist;
    private float attackSpeed;
    
    public BaseNpc(int id, String name, int relation, int live, int damage, int armor, float meleDist, float rangeDist, float detectionDist, float attackSpeed)
    {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.live = live;
        this.damage = damage;
        this.armor = armor;
        
        if(meleDist != -1)
            this.meleDist = meleDist;
        else
            this.meleDist = 1.5f;
        
        if(rangeDist != -1)
            this.rangeDist = rangeDist;
        else
            this.rangeDist = 6.0f;
        
        if(detectionDist != -1)
            this.detectionDist = detectionDist;
        else
            this.detectionDist = 6.0f;
        
        if(attackSpeed!=-1)
            this.attackSpeed = attackSpeed;
        else
            this.attackSpeed = 2.11f;
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
    public float getMeleDist()
    {
        return meleDist;
    }
    public float getRangeDist()
    {
        return rangeDist;
    }
    public float getDetectionDist()
    {
        return detectionDist;
    }
    public float getAttackSpeed()
    {
        return attackSpeed;
    }
}

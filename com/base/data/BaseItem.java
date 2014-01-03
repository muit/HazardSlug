/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

/**
 *
 * @author Miguel
 */
public class BaseItem {
    private int id;
    private String name;
    private float gravity;
    private float heavy;
    
    
    public BaseItem(int id, String name, float gravity, float heavy)
    {
        this.id = id;
        this.name = name;
        this.gravity = gravity;
        this.heavy = heavy;
    }
    public BaseItem(int id, String name, double gravity, float heavy)
    {
        this.id = id;
        this.name = name;
        this.gravity = (float)gravity;
        this.heavy = heavy;
    }
    
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getGravity()
    {
        return gravity;
    }
    
    public float getHeavy()
    {
        return heavy;
    }
}

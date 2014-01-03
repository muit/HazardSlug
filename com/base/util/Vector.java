/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.util;

/**
 *
 * @author Miguel
 */
public class Vector {
    private float[] num;
    
    public Vector(int i)
    {
        num = new float[i];
    }
    public Vector()
    {
        num = new float[2];
    }
    public float get(int i)
    {
        return num[i];
    }
    public void set(int i, float e)
    {
        num[i]=e;
    }
}

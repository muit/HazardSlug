/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.util;

/**
 *
 * @author Miguel
 */
public class Util {
    public static int random(int min, int max)
    {
        return (int)(min + (Math.random() * ((max - min) + 1)));
    }
}

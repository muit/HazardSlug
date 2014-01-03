/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import static org.lwjgl.opengl.GL11.*;


/**
 *
 * @author Miguel_F
 */
public class Camera {
    private int x,y;
    public void setCamera(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho( x, w+x, y, h+y, -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }
}

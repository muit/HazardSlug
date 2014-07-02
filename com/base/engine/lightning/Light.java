/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.lightning;

import com.base.engine.GameObject;
import com.base.engine.Main;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Miguel_F
 */
public class Light {
    public Vector2f location;
    public float red, green, blue;
    public float attenuation;

    public Light(Vector2f location, float red, float green, float blue, float attenuation) {
        this.location = location;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.attenuation = attenuation;
    }
    
    public void render()
    {
        glColorMask(false, false, false, false);
        glStencilFunc(GL_ALWAYS, 1, 1);
        glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);
        
        glDisable(GL_BLEND);
        for (GameObject go : Main.getGame().getGameObjects()) {
            go.drawShadow(this);
        }
        glEnable(GL_BLEND);
        
        glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
        glStencilFunc(GL_EQUAL, 0, 1);
        glColorMask(true, true, true, true);
        
        glBlendFunc(GL_ONE, GL_ONE);

        glUseProgram(Lighting.shaderProgram);
        glUniform2f(glGetUniformLocation(Lighting.shaderProgram, "lightLocation"), location.getX(), Display.getHeight() - location.getY());
        glUniform3f(glGetUniformLocation(Lighting.shaderProgram, "lightColor"), red, green, blue);
        glUniform1f(glGetUniformLocation(Lighting.shaderProgram, "lightAtenuation"), attenuation);
        
        glBegin(GL_QUADS); {
            glVertex2f(0, 0);
            glVertex2f(0, Display.getHeight());
            glVertex2f(Display.getWidth(), Display.getHeight());
            glVertex2f(Display.getWidth(), 0);
        } glEnd();
        
        glUseProgram(0);
        glClear(GL_STENCIL_BUFFER_BIT);
    }
}

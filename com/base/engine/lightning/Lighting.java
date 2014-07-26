/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.lightning;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_STENCIL_TEST;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Miguel_F
 */
public class Lighting {
    private static ArrayList<Light> lights = new ArrayList<>();
    public static int fragmentShader;
    public static int shaderProgram;
    
    
    public static void addLight(float x, float y, float red, float green, float blue)
    {
        lights.add(new Light(new Vector2f(x*16, y*16), red, green, blue, 1.0f));
    }
    public static void render()
    {
        glEnable(GL_STENCIL_TEST);
        for(Light light : lights)
            light.render();
        glDisable(GL_STENCIL_TEST);
    }
    public static void reset()
    {
        lights = new ArrayList<>();
    }
    public static void init()
    {
        shaderProgram = glCreateProgram();
	fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        
        StringBuilder fragmentShaderSource = new StringBuilder();

        try {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(Light.class.getResourceAsStream("shader.frag")));
            while ((line = reader.readLine()) != null) {
                fragmentShaderSource.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment shader not compiled!");
        }
        
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);
    }
    public static void cleanup() {
        glDeleteShader(fragmentShader);
        glDeleteProgram(shaderProgram);
    }
}

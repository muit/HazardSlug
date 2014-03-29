/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game;

import org.lwjgl.opengl.Display;

/**
 *
 * @author Miguel_F
 */
public class Config {
    private static int FPS = 60;
    private static boolean VSync = true;
    
    private static boolean Sound_Enabled = true;
    private static float Sound_Level = 70.0f;
    
    public static void setFPS(int fps)
    {
        FPS = fps;
    }
    public static int getFPS()
    {
        return FPS;
    }
    
    public static void setVSyncEnabled(boolean VSyncVal)
    {
        VSync = VSyncVal;
        Display.setVSyncEnabled(VSync);
    }
    public static boolean getVSyncEnabled()
    {
        return VSync;
    }
    
    public static void setSoundEnabled(boolean soundEnabled)
    {
        Sound_Enabled = soundEnabled;
    }
    public static boolean getSoundEnabled()
    {
        return Sound_Enabled;
    }
    public static void setSoundLevel(float soundLevel)
    {
        Sound_Level = soundLevel;
    }
    public static float getSoundValue()
    {
        return Sound_Level;
    }
    
}

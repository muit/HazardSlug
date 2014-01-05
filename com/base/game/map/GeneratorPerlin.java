/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.game.Util;

/**
 *
 * @author Miguel_F
 */
public class GeneratorPerlin {
    private  int chunkNum;
    private  int frequency;
    private  int amplitude;
    private int[][] chunkPoints;
    private int[][] cubes;
    private int tempPoint;
    private int chunkSize = 32;
    
    public int[][] generate(int frequency, int amplitude)
    {
        this.frequency = frequency;
        this.amplitude = amplitude;
        randomPoints();
        drawLine();
        return cubes;
    }
    
    private void randomPoints()
    {
        chunkPoints = new int[chunkNum][frequency];
        for (int[] chunkPoint : chunkPoints) {
            for (int o = 0; o < frequency; o++) {
                chunkPoint[o] = Util.random(0,amplitude);
            }
        }
    }
    private void drawLine()
    {
        cubes = new int[chunkNum][chunkSize];
        for(int chunk = 0; chunk+1 < chunkPoints.length; chunk++)
        {
            System.out.println("Chunk: "+chunk);
            
            for(int o = 0; o < frequency; o++)
            {
                for(int i = 0; i<chunkSize/frequency; i++)
                {
                    if(o+1 >= frequency)
                        tempPoint = cosine_Interpolate(chunkPoints[chunk][o], chunkPoints[chunk+1][0], (float)i/(chunkSize/frequency-1) );
                    else
                        tempPoint = cosine_Interpolate(chunkPoints[chunk][o], chunkPoints[chunk][o+1], (float)i/(chunkSize/frequency-1) );
                    cubes[chunk][o*chunkSize+i] = tempPoint;
                }
            }
        }
    }
    
    private static int linear_Interpolate(int a, int b, float x)
    {
        return Math.round(a*(1-x)+b*x);
    }
    private static int cosine_Interpolate(int a, int b, float x)
    {
        double ft = x*3.1415927;
        double f = (1-Math.cos(ft))*0.5;
        return Math.round((float)(a*(1-f)+b*f));
    }
    //avanzado
    private static int cubic_Interpolate(int a0, int a, int b, int b0, float x)
    {
        double P = (b0-b)-(a0-a);
        double Q = (a0-a)-P;
        double R = b-a0;
        double S = a;
        return Math.round((float)(P*Math.pow(x,3) + Q*Math.pow(x,2) + R*x + S));
    }
    
    
}

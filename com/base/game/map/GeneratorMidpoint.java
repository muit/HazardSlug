/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.map;

import com.base.util.*;


/**
 *
 * @author Miguel_F
 */
public class GeneratorMidpoint {
    private int maxAlt=50;
    private int minAlt=0;
    private int chunksNum;
    private Chunk chunks[];
    private Vector vects[];
    private Vector vect = new Vector(2);
    public void generate(int Size)
    {
        //Size 0-pequeño 1-mediano 2-grande 3-enorme
        chunksNum = 1024*(int)Math.pow(2,Size)+1024;
        chunks = new Chunk[chunksNum];
        vects = new Vector[chunksNum*32+1];
        
        //nivel de creacion 1
        for(int i = 0; i<vects.length; i+=32)
        {
            vects[i] = new Vector();
            vects[i].set(1,maxAlt);
            vects[i].set(0, Util.random(maxAlt/2, -maxAlt/2));
        }
        //nivel de creacion 2
        for(int i = 16; i<vects.length;i+=32)
        {
            vects[i]=genPoint(vects[i+32/2],vects[i-32/2]);
        }
        //nivel de creacion 3
        for(int i = 8; i<vects.length;i+=16)
        {
            vects[i]=genPoint(vects[i+8],vects[i-8]);
        }
        //nivel de creacion 4
        for(int i = 4; i<vects.length;i+=8)
        {
            vects[i]=genPoint(vects[i+4],vects[i-4]);
        }
        //nivel de creacion 5
        for(int i = 2; i<vects.length;i+=4)
        {
            vects[i]=genPoint(vects[i+2],vects[i-2]);
        }
        //nivel de creacion puro
        for(int i = 1; i<vects.length;i+=2)
        {
            vects[i]=genPoint(vects[i+1],vects[i-1]);
        }
        
    }
    
    private Vector genPoint(Vector y1, Vector y2)
    {
        if(y1.get(1)<y2.get(1))
        {
            y2.set(1,y1.get(1));
        }
        else if(y2.get(1)<y1.get(1))
        {
            y1.set(1,y2.get(1));
        }
        Vector res = new Vector(2);
        res.set(1,y1.get(1)/2);
        int C = (int)((y1.get(0) + y2.get(0)) / 2 + Util.random( (int)(res.get(1) / 2), (int)(-res.get(1) / 2)));
        if(C<minAlt)
            C=minAlt;
        if(C>maxAlt)
            C=maxAlt;
        
        res.set(0, C);
        return res;
    }
}

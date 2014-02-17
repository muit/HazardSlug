/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.base.engine.GameObject;
import com.base.game.map.GeneratorMidpoint;

/**
 *
 * @author Miguel
 */
public class Util {
    public static int random(int min, int max)
    {
        return (int)(min + (Math.random() * ((max - min) + 1)));
    }
    
    public static boolean LineOfSight(GameObject go1, GameObject go2)
    {
        return true;
    }
    
    public static float dist(float x1, float y1, float x2, float y2)
    {
        double x = x2 - x1;
        double y = y2 - y1;
        return (float)Math.sqrt(x*x+y*y);
    }
    public static float distSqrt(float x1, float y1, float x2, float y2)
    {
        double x = x2 - x1;
        double y = y2 - y1;
        return (float)(x*x+y*y);
    }
    
    public static int encriptChunkId(int chunkId)
    {
        String cIdCacheStr = ""+chunkId;
        //cifrado String////////////////////////////////////////////////////////
        String cIdInverted = "";
        for (int i = cIdCacheStr.length()-1; i >=0 ; i--) { 
            cIdInverted += cIdCacheStr.charAt(i); 
        }
        //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        chunkId = Integer.parseInt(cIdInverted);
        //cifrado Integer///////////////////////////////////////////////////////
        
        //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        return chunkId;
    }
    public String encript(String hash, String content)
    {
    	try{
	    	MessageDigest md = MessageDigest.getInstance(hash);
	        md.update(content.getBytes("UTF-8")); // Change this to "UTF-16" if needed
	        byte[] digest = md.digest();
	    	return digest.toString();
    	}
    	catch (NoSuchAlgorithmException ex) 
        {
            System.err.println("Couldn´t hash password.");
        } 
    	catch (UnsupportedEncodingException e) 
        {
        	System.err.println("Couldn´t hash password(2).");
		}
    	return null;
    }
}

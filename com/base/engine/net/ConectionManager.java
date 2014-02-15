/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Miguel_F
 */
public final class ConectionManager {
    private String URL;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean connected;
    private MessageDigest md; 
    
    public ConectionManager(String URL)
    {
        this.URL = URL;
    }
    public void read()
    {
        
    }
    public void openConnection()
    {
        if(connected)
        {
            closeConection();
        }
        
        try {
            socket = new Socket(URL, 19595);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
        connected = true;
    }
    public void closeConection()
    {
        try{
        out.close();
        in.close();
        socket.close();
        } catch (IOException e) {
            System.err.println("Couldn't close conection.");
            System.exit(1);
        }
        connected = false;
    }
    public void sendMsg(String msg)
    {
        out.println(msg);
    }
    
    public String readMsg()
    {
    try{
        return in.readLine();
        }catch(IOException e)
        {
            System.err.println("Couldn´t read net data.");
            return null;
        }
    }
    public boolean login(String name, String password)
    {
        try{
            /*CIFRATE:
            name-password
            name-SHA-256(password)@SHA-256(SHA-256(password))
            */
            
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            md.update(digest);
            byte[] digestdouble =md.digest();
            
            out.println(name+"-"+digest+"@"+digestdouble);
            return !in.readLine().equals("false");
        } 
        catch(IOException e)
        {
            System.err.println("Couldn´t read net data.");
        }
        catch (NoSuchAlgorithmException ex) 
        {
            System.err.println("Couldn´t hash password.");
        }
        return false;
    }
}

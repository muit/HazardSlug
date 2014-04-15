/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import com.base.engine.net.packets.Connect_Packet;
import static com.base.engine.net.packets.Connect_Packet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    private String name, password;
    
    private Listener listen;
    private Sender send;
    
    private boolean connected;
    private MessageDigest md; 
    
    public ConectionManager(String URL)
    {
        this.URL = URL;
        send = null;
    }
    
    public void openConnection(String name, String password)
    {
        if(connected)
        {
            closeConection();
        }
        
        this.name = name;
        this.password = password;
        
        try {
            socket = new Socket(URL, 19595);
            send = new Sender(socket);
            listen = new Listener(socket);
            connect();
            connected = true;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
            System.exit(1);
        }
        
    }
    
    
    private void connect()
    {
        if(login(name, password))
        {
            listen.start();
        }
        else
        {
            closeConection();
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
            md.update(password.getBytes("UTF-16")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            md.update(digest);
            byte[] digestdouble =md.digest();
            
            password = digest.toString()+"@"+digestdouble.toString();
            
            Connect_Packet connectPacket = new Connect_Packet(name, password, STATUS_ONLINE);
            
            send.SendPacket(0x04, connectPacket);
            return true;
        } 
        catch(UnsupportedEncodingException e)
        {
            System.err.println("Couldn´t use security correctly.");
        }
        catch (NoSuchAlgorithmException ex) 
        {
            System.err.println("Couldn´t hash password.");
        }
        return false;
    }
    
    public void closeConection()
    {
        try{
            socket.close();
            send = null;
            listen.interrupt();
            listen = null;
        } catch (IOException e) {
            System.err.println("Couldn't close conection.");
            System.exit(1);
        }
        connected = false;
    }
    
    
    //Packet Actions////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
}

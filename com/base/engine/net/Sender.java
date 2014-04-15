/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Miguel_F
 */
public class Sender {
    
    private Socket socket;
    private Packet pck;
    public Sender(Socket sock)
    {
        socket = sock;
    }

    public void CloseConnection()
    {
        // disconnect properly
        try 
        {
            socket.close();
        } 
        catch (IOException e)
        {
            //Log.outError("Socket with " + socket.getInetAddress() + " already closed");
        }
    }

    public void SendPacket(Integer op,Object packet)
    {
        pck = new Packet(op,packet);
        
        // out stream
        ObjectOutputStream out;
        try 
        {
            out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            // send packet
            out.writeObject(pck);
            // clean buffer
            out.flush();
            //Log.outTimed("Send packet " + pck.getOpcode() + ": " + pck.getData() + " to client : " + socket.getInetAddress());
        }	
        catch(SocketException e)
        {
            CloseConnection();
        }
        catch (IOException e) 
        {
            //Log.outError("Socket Write error, closing connection with " + socket.getInetAddress());
            CloseConnection();
        }
    }
}

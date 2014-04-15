/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Miguel_F
 */
public class Listener extends Thread{
    private Socket sockt;
    private Packet_Handler packopt;
    private ObjectInputStream in;

    @Override
    public void run()
    {
        ListenAndDo();
    }
    
    public Listener(Socket sock)
    {
        this.sockt = sock;
    }
    
    public void ListenAndDo()
    {
        try{
            in = new ObjectInputStream(new BufferedInputStream(sockt.getInputStream()));
            
            while(true)
            {
                // read the stream
                Packet message = (Packet) in.readObject();

                // if opcode is 6 disconnect force
                if(message.getOpcode().equals(0x07))
                    break;

                // get packet to do sth
                TreatPacket(message);
            }
            sockt.close();
        }catch(IOException | ClassNotFoundException e)
        {
        
        }
    }
    
    public void TreatPacket(Packet packt)
    {
        // create packethandler for this packet 
        packopt = new Packet_Handler(packt,sockt);
        packopt.Destroy();
    }
}

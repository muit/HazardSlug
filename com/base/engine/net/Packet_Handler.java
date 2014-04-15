/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 *
 * @author Miguel_F
 */
public class Packet_Handler {
    final static int MAX_OPCODE = 0xFF;
    Integer opcode_id;
    Object data;
    Socket sc;
    
    public Packet_Handler(Packet stream,Socket sock)
    {
        // register socket & session
        sc = sock;
        try{
            // register id
            opcode_id = stream.getOpcode();
            //Log.outString("Packet received from " + mysock.getInetAddress() + " (opcode :" + this.opcode_id + ")");

            // register data
            data = stream.getData();
            ActionOnPacket();
        }
        catch(IOException | SQLException e)
        {
            //Log.outError("Packet Handler: client " + mysock.getInetAddress() + " send invalid packet !");
        }
    }
    
    public void ShowPacket() throws IOException
    {
        //Log.outString(opcode_id + " / " + data);
    }

    public void ActionOnPacket() throws IOException, SQLException
    {
        if(this.opcode_id > MAX_OPCODE)
        {
            // packet is not in list
            //Log.outError("Unrecognized opcode received from " + mysock.getInetAddress());
            return;
        }
        else
        {
            // switch by opcode and do actions
            switch(opcode_id)
            {
                case 0x08:
                    
                    break;
                default:
                    break;
            }
        }
    }

    public void Destroy() 
    {

    }
}

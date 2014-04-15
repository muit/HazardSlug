/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net;

/**
 *
 * @author Miguel_F
 */
public enum OpCodes {
    CMSG_REQUEST_PING(0x00),
    SMSG_RESPONSE_PONG(0x01),
    SMSG_REQUEST_PING(0x02),
    CMSG_RESPONSE_PONG(0x03),
    CMSG_REQUEST_CONNECT(0x04),
    CMSG_REQUEST_DISCONNECT(0x05),
    CMSG_REQUEST_CLOSE_SOCKET(0x06),
    SMSG_REQUEST_CLOSE_SOCKET(0x07),
    SMSG_RESPONSE_CONNECT(0x08),
    CMSG_SEND_STATUS(0x09),
    SMSG_RECEIVED_STATUS(0x0A),
    SMSG_SEND_PLAYERLIST(0x0B),
    CMSG_RECEIVED_PLAYERLIST(0x0C),
    SMSG_SEND_SERVERNAME(0x0D),
    CMSG_RECEIVED_SERVERNAME(0x0E),

    //Player Position Send
    CMSG_SEND_PLAYERPOS(0x0F),
    SMSG_RECEIVED_PLAYERPOS(0x10),

    //Player Position From Server
    SMSG_SEND_PLAYERSPOS(0x11),
    CMSG_RECEIVED_PLAYERSPOS(0x12),
    
    //Map Update From Server OpCodes
    CMSG_REQUEST_MAP(0x13),
    SMSG_RESPONSE_MAP(0x14),
    CMSG_REQUEST_MAPCHUNK(0x15),
    SMSG_RESPONSE_MAPCHUNK(0x16),
    SMSG_REQUEST_MAPCHUNK(0x17),
    CMSG_RESPONSE_MAPCHUNK(0x18),
    CMSG_SEND_BLOCK(0x19),
    SMSG_RECEIVED_BLOCK(0x1A),
    SMSG_SEND_BLOCK(0x1B),
    CMSG_RECEIVED_BLOCK(0x1C),
    
    CMSG_REQUEST_TESTPACKET(0xFF);
    
    private final int value;

    private OpCodes(int op)
    {
        this.value = op;
    }

    public int getValue() {
        return this.value;
    }
}

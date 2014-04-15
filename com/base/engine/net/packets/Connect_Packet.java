/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine.net.packets;

import java.io.Serializable;

/**
 *
 * @author Miguel_F
 */
public class Connect_Packet implements Serializable{
    
    private String name, password;
    private Integer status;
    
    public static final int
            STATUS_DISCONECT = 0,
            STATUS_ONLINE = 1,
            STATUS_AFK = 2;
    
    public Connect_Packet(String _name, String _password, Integer _status) 
    {
        name = _name;
        password = _password;
        status = _status;
    }
    
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(Integer status) { this.status = status; }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.text;

import com.base.engine.Main;
import com.base.game.gameobject.Player;
import com.base.game.gameobject.Unit;
import java.util.ArrayList;

/**
 *
 * @author Miguel_F
 */
public class Log {
    
    public static void sendMessageToAll(String message)
    {
        //CRASH: Main.getPlayers(); NullPointerException
        ArrayList<Unit> players = Main.getPlayers();
        for(int i=0; i<players.size(); i++)
        {
            //Envio de texto al jugador------
            
            //-------------------------------
            
            //Temporal:---
            System.out.println("(" + players.get(i).getName() + "): " + message);
            //------------
        }
    }
    
    public static void sendMessage(String message, Player player)
    {
        //Envio de texto al jugador------
        
        //-------------------------------
        
        //Temporal:---
        System.out.println("(" + player.getName() + "): " + message);
        //------------
    }
    public static void consoleMessage(String message)
    {
        System.out.println(message);
    }
}

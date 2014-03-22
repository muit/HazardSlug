/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game;

import com.base.game.map.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel_F
 */
public class MapManager {
    private Map map;
    public MapManager(int size, Game game)
    {
        map = new Map(size, game);
    }
    public void save(String saveName)
    {
        try {
            FileOutputStream fout = new FileOutputStream("\\saves\\"+saveName+"\\world.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(map);
            oos.close();
            System.out.println("Done");
        } catch (IOException ex) {
            Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void load(String saveName)
    {
        try{
            FileInputStream fin = new FileInputStream("\\saves\\"+saveName+"\\world.sav");
            ObjectInputStream ois = new ObjectInputStream(fin);
            map = (Map) ois.readObject();
            ois.close();
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setMap(Map map)
    {
        this.map = map;
    }
    
    public Map getMap()
    {
        return map;
    }
}
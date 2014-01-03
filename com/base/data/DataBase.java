/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

import java.util.ArrayList;

/**
 *
 * @author Miguel
 */
public class DataBase {
    private ArrayList<BaseItem> objects;
    private ArrayList<BaseNpc> npcs;
    
    public DataBase()
    {
        objects = new ArrayList<>();
        npcs = new ArrayList<>();
        //ITEMS/////////////////////////////////////////////////////////////////
        objects.add(new BaseItem(1, "Piedra", 0, 10));
        objects.add(new BaseItem(2, "Piedra encajada", 0, 10));
        objects.add(new BaseItem(3, "Arena", 0.2, 10));
        objects.add(new BaseItem(4, "Tierra", 0, 10));
        //NPCS//////////////////////////////////////////////////////////////////
        npcs.add(new BaseNpc(0, "Cria de Babosa", 1, 10,5,5));
    }
    
    
    
    public String getItemName(int id)
    {
        BaseItem bi = getItem(id);
        if(bi != null)
            return bi.getName();
        else
            return "Error(id:"+id+")";
    }
    public float getItemGravity(int id)
    {
        BaseItem bi = getItem(id);
        if(bi != null)
            return bi.getGravity();
        else
            return 255;
    }
    public float getItemHeavy(int id)
    {
        BaseItem bi = getItem(id);
        if(bi != null)
            return bi.getHeavy();
        else
            return 255;
    }
    private BaseItem getItem(int id)
    {
        for(BaseItem bi : objects)
        {
            if(bi.getId() == id)
            {
                return bi;
            }
        }
        return null;
    }
    
    
    public String getNpcName(int id)
    {
        BaseNpc npc = getNpc(id);
        if(npc != null)
            return npc.getName();
        else
            return "Error(id:"+id+")";
    }
    public int getNpcRelation(int id)
    {
        BaseNpc npc = getNpc(id);
        if(npc != null)
            return npc.getRelation();
        else
            return 255;
    }
    public int getNpcLive(int id)
    {
        BaseNpc npc = getNpc(id);
        if(npc != null)
            return npc.getLive();
        else
            return 255;
    }
    public int getNpcDamage(int id)
    {
        BaseNpc npc = getNpc(id);
        if(npc != null)
            return npc.getDamage();
        else
            return 255;
    }
    public int getNpcArmor(int id)
    {
        BaseNpc npc = getNpc(id);
        if(npc != null)
            return npc.getArmor();
        else
            return 255;
    }
    private BaseNpc getNpc(int id)
    {
        for(BaseNpc npc : npcs)
        {
            if(npc.getId() == id)
            {
                return npc;
            }
        }
        return null;
    }
}

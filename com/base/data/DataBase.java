/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

import java.util.ArrayList;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Miguel
 */
public class DataBase {
    static private ArrayList<BaseItem> objects;
    static private ArrayList<BaseNpc> npcs;
    static private ArrayList<BaseEffect> effects;
    static boolean defined = false;
    
    public DataBase()
    {
        if(!defined)
        {
            objects = new ArrayList<>();
            npcs = new ArrayList<>();
            effects = new ArrayList<>();
            
            //ITEMS/////////////////////////////////////////////////////////////////
            objects.add(new BaseItem(0, "Rock", 0, 30));
            objects.add(new BaseItem(1, "Floor Rock", 0, 25));
            objects.add(new BaseItem(2, "Arena", 0.2, 10));
            objects.add(new BaseItem(3, "Tierra", 0, 15));
            objects.add(new BaseItem(4, "Tierra con Hierba", 0, 10));
            objects.add(new BaseItem(5, "Madera", 0, 10));
            objects.add(new BaseItem(10, "Sulfuro del Infierno", 0, 100000));
            objects.add(new BaseItem(44, "Azulita", 0, 100));
            objects.add(new BaseItem(45, "Eter", 0, 1));
            //NPCS//////////////////////////////////////////////////////////////////
            npcs.add(new BaseNpc(0, "Cria de Babosa", 1, 10,5,5,-1,-1,-1,-1));
            npcs.add(new BaseNpc(1, "Babosa Adulta", 1, 10,5,5,-1,-1,-1,-1));
            //EFFECTS///////////////////////////////////////////////////////////////
            effects.add(new BaseEffect(0));
            effects.add(new BaseEffect(1));
            ////////////////////////////////////////////////////////////////////////
            defined = true;
        }
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
    public Texture getItemTexture(int id)
    {
        BaseItem bi = getItem(id);
        if(bi != null)
            return bi.getTexture();
        else
            return null;
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
    public Texture getNpcTexture(int id)
    {
        BaseNpc bu = getNpc(id);
        if(bu != null)
            return bu.getTexture();
        else
            return null;
    }
    
    private BaseEffect getEffect(int id)
    {
        for(BaseEffect be : effects)
        {
            if(be.getId() == id)
            {
                return be;
            }
        }
        return null;
    }
    
    public Texture getEffectTexture(int id)
    {
        BaseEffect be = getEffect(id);
        if(be != null)
            return be.getTexture();
        else
            return null;
    }
    
}

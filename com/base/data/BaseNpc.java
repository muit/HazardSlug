/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.data;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.base.engine.Main;

/**
 *
 * @author Miguel
 */
public class BaseNpc {
    private int id;
    private Texture tex = null;
    private String name;
    private int relation;//0-friendly, 1-pasive enemy, 2-active enemy
    private int live;
    private int damage;
    private int armor;
    private float meleDist;
    private float rangeDist;
    private float detectionDist;
    private float attackSpeed;
    
    public BaseNpc(int id, String name, int relation, int live, int damage, int armor, float meleDist, float rangeDist, float detectionDist, float attackSpeed)
    {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.live = live;
        this.damage = damage;
        this.armor = armor;
        
        if(meleDist != -1)
            this.meleDist = meleDist;
        else
            this.meleDist = 1.5f;
        
        if(rangeDist != -1)
            this.rangeDist = rangeDist;
        else
            this.rangeDist = 6.0f;
        
        if(detectionDist != -1)
            this.detectionDist = detectionDist;
        else
            this.detectionDist = 6.0f;
        
        if(attackSpeed!=-1)
            this.attackSpeed = attackSpeed;
        else
            this.attackSpeed = 2.11f;
    }
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getRelation()
    {
        return relation;
    }
    
    public int getLive()
    {
        return live;
    }
    public int getDamage()
    {
        return damage;
    }
    public int getArmor()
    {
        return armor;
    }
    public float getMeleDist()
    {
        return meleDist;
    }
    public float getRangeDist()
    {
        return rangeDist;
    }
    public float getDetectionDist()
    {
        return detectionDist;
    }
    public float getAttackSpeed()
    {
        return attackSpeed;
    }
    
    public Texture getTexture()
    {
        if(tex == null)
        {
            String path = "com/resources/npc/"+id+".png";
            try {
                tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
            } catch (IOException ex) {
                System.out.println("Textura(npc): "+id+" no se pudo cargar.");
                Main.Close();
                
            }
            if(tex == null)
            {
                System.out.println("Textura(npc): "+id+" no se pudo cargar.");
                Main.Close();
            }
        }
        return tex;
    }
}

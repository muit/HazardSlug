/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.player;

import com.base.game.gameobject.item.Item;

/**
 *
 * @author Miguel
 */
public class Inventory {
    private Item[] items;
    private int firstFree;
    
    
    public Inventory(int space)
    {
        items = new Item[space];
        firstFree = 0;
    }
    public boolean add(Item item)
    {
        if(firstFree == items.length)
            return false;
        items[firstFree] = item;
        
        for(int i = firstFree; i < items.length; i++)
        {
            if(items[i]==null)
            {
                firstFree = i;
                return true;
            }
    }
        firstFree = items.length;
        
        return true;
    }
    public void remove(int index)
    {
        items[index] = null;
        if(index < firstFree)
            firstFree= index;
    }
    public void remove(Item item)
    {
        for(int i = 0; i < items.length; i++)
            if(items[i]==item)
            {
                items[i] = null;
                if(i< firstFree)
                    firstFree = i;
            }
    }
    public Item getItem(int i)
    {
        return items[i];
    }
}

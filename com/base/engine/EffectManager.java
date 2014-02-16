/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import com.base.game.gameobject.Unit;

/**
 *
 * @author Miguel_F
 */
public class EffectManager {
    public static void createEffect(Unit me, int id, float x, float y)
    {
        Main.getGame().createEffect(me, id, x, y);
    }
    public static void createEffect(Unit me, int id, float x, float y, float sx, float sy, float speed)
    {
        Main.getGame().createEffect(me, id, x, y, sx, sy, speed);
    }
    public static void createEffect(Unit me, int id, float x, float y, Unit target, float speed, boolean follow)
    {
        Main.getGame().createEffect(me, id, x, y, target, speed, follow);
    }
}

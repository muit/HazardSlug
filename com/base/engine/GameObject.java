/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.lightning.Light;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Miguel
 */
public abstract class GameObject 
{
    protected int type = 0;
    protected boolean remove = false;
    protected Vector2f[] vectors;
    
    public void update()
    {
        
    }
    public void render()
    {
    }
    
    public float getSX(){ return vectors[2].getX()-vectors[0].getX(); }
    public float getSY(){ return vectors[2].getY()-vectors[0].getY(); }
    public float getX(){ return vectors[0].getX(); }
    public float getY(){ return vectors[0].getY(); }
    public void setX(float x){
        float sx = getSX();
        vectors[0].setX(x);
        vectors[1].setX(x);
        vectors[2].setX(x+sx);
        vectors[3].setX(x+sx);
    }
    public void setY(float y){
        float sy = getSY();
        vectors[0].setY(y);
        vectors[1].setY(y+sy);
        vectors[2].setY(y+sy);
        vectors[3].setY(y);
    }
    public void setSX(float sx){
        vectors[2].setX(vectors[0].getX()+sx);
        vectors[3].setX(vectors[0].getX()+sx);
    }
    public void setSY(float sy){
        vectors[1].setY(vectors[0].getY()+sy);
        vectors[2].setY(vectors[0].getY()+sy);
    }
    
    public int getType()
    {
        return type;
    }
    
    public boolean getRemove()
    {
        return remove;
    }
    
    protected void init(int type, float x, float y, float sx,float sy)
    {
        this.type = type;
        vectors = new Vector2f[] {
            new Vector2f(x, y),
            new Vector2f(x, y + sy),
            new Vector2f(x + sx, y + sy),
            new Vector2f(x + sx, y)
        };
    }
    
    public Vector2f[] getVertices() {
        return vectors;
    }
    public void drawShadow(Light light)
    {
        for (int i = 0; i < vectors.length; i++) 
        {
            Vector2f currentVertex = vectors[i];
            Vector2f nextVertex = vectors[(i + 1) % vectors.length];
            Vector2f edge = Vector2f.sub(nextVertex, currentVertex, null);
            Vector2f normal = new Vector2f(edge.getY(), -edge.getX());
            Vector2f lightToCurrent = Vector2f.sub(currentVertex, light.location, null);
            if (Vector2f.dot(normal, lightToCurrent) > 0) {
                Vector2f point1 = Vector2f.add(currentVertex, (Vector2f) Vector2f.sub(currentVertex, light.location, null).scale(800), null);
                Vector2f point2 = Vector2f.add(nextVertex, (Vector2f) Vector2f.sub(nextVertex, light.location, null).scale(800), null);
                glBegin(GL_QUADS); {
                    glVertex2f(currentVertex.getX(), currentVertex.getY());
                    glVertex2f(point1.getX(), point1.getY());
                    glVertex2f(point2.getX(), point2.getY());
                    glVertex2f(nextVertex.getX(), nextVertex.getY());
                } glEnd();
            }
        }
    }
}

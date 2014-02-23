package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Font {
    private int charactersAcross;
    
    private int characterWidth;
    private int characterHeight;
    
    private float characterWidthInTexture;
    private float characterHeightInTexture;
    
    private Texture texture;
    private int characterStep;
    
    /**
     * Create a new font based on specific texture cut up into a specific
     * collection of characters
     * 
     * @param textureName The texture containing the characters
     * @param characterWidth The width of the characters on the sheet (in pixels)
     * @param characterHeight The height of the characters on the sheet (in pixels)
     */
    public Font(String textureName, int characterWidth, int characterHeight) 
    {
            setBitmapFont(textureName, characterWidth, characterHeight);
    }
    
    public void setBitmapFont(String textureName, int characterWidth, int characterHeight)
    {
            this.texture = loadTexture(textureName);
            this.characterWidth = characterWidth;
            this.characterHeight = characterHeight;
            
            characterWidthInTexture = texture.getWidth() / (texture.getImageWidth() / characterWidth);
            characterHeightInTexture = texture.getHeight() / (texture.getImageHeight() / characterHeight);
    
            charactersAcross = texture.getImageWidth() / characterWidth;
            
            characterStep = characterWidth - 5;
            
    }
    
            
    /**
     * Draw a string to the screen as a set of quads textured in the
     * appropriate way to show the string.
     * 
     * @param font The index of the font to draw. 0 means the font
     * at the top, 1 the font at the bottom.
     * @param text The text to be draw to the screen.
     * @param x The x coordinate to draw the text at (int pixels)
     * @param y The y coordinate to draw the text at (int pixels)
     */
    public void drawString(int font, String text, int x, int y) {
            
            glEnable(GL_TEXTURE_2D);
            texture.bind();

            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            glBegin(GL_QUADS);
            
            for (int i=0;i<text.length();i++) {
            	
                    int c = text.charAt(i) - ' ';
                    
                    float u = ((c % charactersAcross) * characterWidthInTexture);
                    float v = 1 - ((c / charactersAcross) * characterHeightInTexture);
                    v -= font * 0.5f;
                    
                    glTexCoord2f(u, v);
                    glVertex2i(x+(i*characterStep), y);
                    glTexCoord2f(u, v - characterHeightInTexture);
                    glVertex2i(x+(i*characterStep), y+characterHeight);
                    glTexCoord2f(u + characterWidthInTexture, v - characterHeightInTexture);
                    glVertex2i(x+(i*characterStep)+characterWidth, y+characterHeight);
                    glTexCoord2f(u + characterWidthInTexture, v);
                    glVertex2i(x+(i*characterStep)+characterWidth, y);
            }
            
            glEnd();
            
            glDisable(GL_BLEND);
    }
    private Texture loadTexture(String name)
    {
    	Texture tex = null;
    	
        String path = "com/resources/fonts/"+name+".png";
        try {
            tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
        } catch (RuntimeException ex) {
            System.out.println("Textura: "+ex);
            Main.heavyClose();   
        } catch (IOException ex) {
            System.out.println("Textura(2): "+name+" no se pudo cargar.");
            Main.heavyClose();
        }

        if(tex == null)
        {
            System.out.println("Textura(3): "+name+" no se pudo cargar.");
            Main.heavyClose();
        }
        return tex;
    }
}
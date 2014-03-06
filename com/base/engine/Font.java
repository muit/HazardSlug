package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public final class Font {
    private int charactersAcross;
    
    private int characterWidth;
    private int characterHeight;
    
    private float characterWidthInTexture;
    private float characterHeightInTexture;
    
    private Texture texture;
    private int characterStep;
    private float boxX, boxY;
    private int charactersEveryLine, charactersEveryColumn;
    /**
     * Create a new font based on specific texture cut up into a specific
     * collection of characters
     * 
     * fontName:
     * "Urdu_Typesetting" - 20 20
     * "Narkisim"         - 16 16
     * 
     * @param fontName The texture containing the characters
     * @param characterWidth The width of the characters on the sheet (in pixels)
     * @param characterHeight The height of the characters on the sheet (in pixels)
     */
    public Font(String fontName, int characterWidth, int characterHeight) 
    {
            setBitmapFont(fontName, characterWidth, characterHeight);
    }
    
    public void setBitmapFont(String textureName, int charactersEveryLine, int charactersEveryColumn)
    {
            this.texture = loadTexture(textureName);
            this.charactersEveryColumn = charactersEveryColumn;
            this.charactersEveryLine = charactersEveryLine;
            
            this.characterWidth = (int)(texture.getImageWidth() / charactersEveryLine);
            this.characterHeight = (int)(texture.getImageHeight() / charactersEveryColumn);
            
            boxX = 1/charactersEveryLine;
            boxY = 1/charactersEveryColumn;
            
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
     * @param x0 The x coordinate to draw the text at (int pixels)
     * @param y0 The y coordinate to draw the text at (int pixels)
     */
    public void drawString(int font, String text) 
    {
        texture.bind();
        glBegin(GL_QUADS);

        for (int i=0;i<text.length();i++) 
        {
            int c = text.charAt(i) - ' ';
            
            int lineCount = 0;
            int columnCount = 0;
            for(int o = c; o>charactersEveryLine;)
            {
                columnCount++;
                 o-=charactersEveryLine;
                 if(o<charactersEveryLine){
                     columnCount++;
                     lineCount = o;
                 }
            }
            System.out.println(c+" "+columnCount+" "+lineCount);
            /*
            for(int o = 0; o<charactersEveryLine; o++)
                for(int e = 0; e <charactersEveryColumn; e++)
                    if((o*charactersEveryLine)+e==c)
                    {
                        lineCount = o;
                        columnCount = e;
                        break;
                    }
            */
            
            glTexCoord2f(lineCount*boxX, columnCount*boxY);
            glVertex2f(0,0);
            glTexCoord2f(lineCount*boxX, columnCount*boxY + boxY);
            glVertex2f(100,0);
            glTexCoord2f(lineCount*boxX + boxX, columnCount*boxY + boxY);
            glVertex2f(100,100);
            glTexCoord2f(lineCount*boxX + boxX, columnCount*boxY);
            glVertex2i(0,100);
                    /*
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
                    */
        }

        glEnd();

    }
    private Texture loadTexture(String name)
    {
        String path = "com/resources/fonts/"+name+".png";
    	Texture tex = null;
        try {
            tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
        } catch (RuntimeException ex) {
            System.out.println("Textura: "+ex);
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println("Textura(2): "+ex);
            System.exit(-1);
        }

        if(tex == null)
        {
            System.out.println("Textura(3): "+name+" no se pudo cargar.");
            System.exit(-1);
        }
        return tex;
    }
}
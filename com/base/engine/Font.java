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
    
    public void setBitmapFont(String textureName, int cellsX, int cellsY)
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
    
    private void renderString(String string, int gridSize, float x, float y, float characterWidth, float characterHeight) {
        glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        texture.bind();
        // Enable linear texture filtering for smoothed results.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        // Enable additive blending. This means that the colours will be added to already existing colours in the
        // frame buffer. In practice, this makes the black parts of the texture become invisible.
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        // Store the current model-view matrix.
        glPushMatrix();
        // Offset all subsequent (at least up until 'glPopMatrix') vertex coordinates.
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        // Iterate over all the characters in the string.
        for (int i = 0; i < string.length(); i++) {
            // Get the ASCII-code of the character by type-casting to integer.
            int asciiCode = (int) string.charAt(i);
            // There are 16 cells in a texture, and a texture coordinate ranges from 0.0 to 1.0.
            final float cellSize = 1.0f / gridSize;
            // The cell's x-coordinate is the greatest integer smaller than remainder of the ASCII-code divided by the
            // amount of cells on the x-axis, times the cell size.
            float cellX = ((int) asciiCode % gridSize) * cellSize;
            // The cell's y-coordinate is the greatest integer smaller than the ASCII-code divided by the amount of
            // cells on the y-axis.
            float cellY = ((int) asciiCode / gridSize) * cellSize;
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * characterWidth / 3, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y + characterHeight);
            glTexCoord2f(cellX, cellY);
            glVertex2f(i * characterWidth / 3, y + characterHeight);
        }
        glEnd();
        glPopMatrix();
        glPopAttrib();
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
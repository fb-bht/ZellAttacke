package application;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Fredi Benko 
 */
public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double width;
    private double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
    }

    public void setImage(String filename, double size)
    {
        Image i = new Image(filename);
        image = i;
        width = size;
        height = size;
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
    
    public Vector2 getPosition()
    {
    	return new Vector2(positionX, positionY); 
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY, width, height );
    }

 
}

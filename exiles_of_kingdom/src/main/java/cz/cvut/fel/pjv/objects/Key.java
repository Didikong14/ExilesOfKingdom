package cz.cvut.fel.pjv.objects;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.KEY;


import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * The Key class represents a key object in the game. It extends the Superobject class and 
 * includes additional properties such as a key image.
 *
 * The Key class is instantiated with x and y coordinates, which are passed to the Superobject 
 * constructor. The key image is loaded from a file, and the hitbox is initialized with a specific 
 * width and height.
 * 
 * The Key class contains methods to render the key image on a GraphicsContext, get the x and y 
 * coordinates, and get the hitbox of the key.
 * 
 */

public class Key extends Superobject{
    
    private Image keyImage;

    public Key(int x, int y) {
        super(x, y);
        keyImage = LoadFiles.LoadImage(KEY);
        initHitbox(x, y, 50, 45);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(keyImage, 1492, 1752, 45, 45);
        //drawHitbox(gc); 
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}

package cz.cvut.fel.pjv.objects;

import cz.cvut.fel.pjv.map.Collision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * The Superobject class represents a generic object in the game. It includes properties such as 
 * a hitbox for collision detection, x and y coordinates, and dialogues for interaction.
 *
 * The Superobject class is instantiated with x and y coordinates. The hitbox is initialized with 
 * a specific width and height using the initHitbox method.
 * 
 */
public class Superobject {
    public Rectangle hitbox;
    protected Collision collision;
    int x, y;
    String dialogues[] = new String[2];
    public int dialogIndex = 0;

    /**
     * Constructor for the Superobject class. Initializes the Superobject with x and y coordinates.
     *
     * @param x the x-coordinate of the Superobject
     * @param y the y-coordinate of the Superobject
     */
    public Superobject(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void speak(){}

    /**
     * Initializes the hitbox of the Superobject with a specific width and height.
     *
     * @param x the x-coordinate of the hitbox
     * @param y the y-coordinate of the hitbox
     * @param width the width of the hitbox
     * @param height the height of the hitbox
     */
    
    public void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle(x, y, width, height);
    }

    
    protected void drawHitbox(GraphicsContext gc) {
        // for debugging purposes
        gc.strokeRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
     }

    
    public Rectangle getHitbox() {
        return hitbox;
    }
}

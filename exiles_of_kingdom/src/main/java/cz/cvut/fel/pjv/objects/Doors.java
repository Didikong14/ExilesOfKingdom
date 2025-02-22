package cz.cvut.fel.pjv.objects;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.DOORS;

import cz.cvut.fel.pjv.gamestates.Dialog;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
/**
 * The Doors class represents a door object in the game. It extends the Superobject class and 
 * includes additional properties such as a door image.
 *
 * The Doors class is instantiated with x and y coordinates, which are passed to the Superobject 
 * constructor. The door image is loaded from a file, and the hitbox is initialized with a specific 
 * width and height.
 * 
 * The Doors class contains methods to render the door image on a GraphicsContext, get the x and y 
 * coordinates, and get the hitbox of the door.
 * 
 */
public class Doors extends Superobject{
    private Image doorImage;
    /**
     * Constructor for the Doors class. Initializes the Doors with x and y coordinates, loads the 
     * door image, initializes the hitbox, and sets the dialogues.
     *
     * @param x the x-coordinate of the door
     * @param y the y-coordinate of the door
     */
    public Doors(int x, int y) {
        super(x, y);
        doorImage = LoadFiles.LoadImage(DOORS);
        initHitbox(x, y, 36, 40);
        setDialogues();
    }
        /**
     * Renders the door image on a GraphicsContext. The door image is drawn at a specific location 
     * and scaled to a specific size.
     *
     * @param gc the GraphicsContext to render the door image on
     */
    
    public void render(GraphicsContext gc) {
        gc.drawImage(doorImage, 60, 1808, 65, 50);
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

    public void speak(){
        Dialog.currentDialog = dialogues[dialogIndex];
        dialogIndex++;
    }

    private void setDialogues(){
        dialogues[0] = "The door is locked.";
    }

    public void openDoor() {
        hitbox.setWidth(0);
        hitbox.setHeight(0);
    }
}

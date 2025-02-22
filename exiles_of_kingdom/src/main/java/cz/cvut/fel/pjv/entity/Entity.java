package cz.cvut.fel.pjv.entity;

import java.util.logging.Logger;

import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.map.Collision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * The Entity class represents a basic game entity. It provides common properties and methods 
 * that all game entities share, such as position, health, damage, movement, hitboxes, and collision.
 *
 * The Entity class also provides a method for entities to speak, which can be overridden by subclasses 
 * to provide entity-specific dialogues.
 */

public abstract class Entity {
    public float x, y;
    public int health;
    protected int damage;
    public boolean moving;

    protected static final Logger log = Logger.getLogger(GameManager.class.getName());
    
    public Rectangle hitbox;
    protected Collision collision;
    String dialogues[] = new String[15];
    int dialogIndex = 0;

     /**
     * Constructs a new Entity at the specified coordinates with the specified health and damage.
     * This constructor also initializes the entity's hitbox.
     *
     * @param x the x-coordinate where the entity will be placed
     * @param y the y-coordinate where the entity will be placed
     * @param health the health of the entity
     * @param damage the damage the entity can inflict
     */

    public Entity(float x, float y, int health, int damage) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        initHitbox(x, y, x, y);
    }

    public void speak(){}

    /**
     * Initializes the entity's hitbox at the specified coordinates with the specified width and height.
     * @param x
     * @param y
     * @param width
     * @param height
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

    
    /**
     * This function is checking, if two hitboxes can hit each other
     * It is using intersects method
     * @param hitbox1
     * @param hitbox2
     * @return
     */
    public boolean checkCollision( Rectangle hitbox1,  Rectangle hitbox2) {
        return hitbox1.intersects(hitbox2.getX(), hitbox2.getY(), hitbox2.getWidth(), hitbox2.getHeight());
    }

    public void updatePos(Entity entity) {
        // to be implemented in subclasses
    }

     

}

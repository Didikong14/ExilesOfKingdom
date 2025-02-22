package cz.cvut.fel.pjv.entity;

import cz.cvut.fel.pjv.map.Collision;
import static cz.cvut.fel.pjv.utils.Constanz.EnemyConstans.*;

import cz.cvut.fel.pjv.utils.Constanz.EnemyConstans;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import static cz.cvut.fel.pjv.utils.Constanz.Strings.DRAGON;

/**
 * The Dragon class represents a dragon entity in the game. It extends the Entity class and 
 * adds additional properties and behaviors specific to dragons, such as direction, speed, 
 * and dragon-specific images.
 *
 * The Dragon class also handles the dragon's collision detection and hitbox initialization.
 */

public class Dragon extends Entity{
    private int direction = 0; 
    private int speed = 1; 
    private Image dragonImages[][];
    private int dragonAction = IDLE;
    private int aniIndex;

    /**
     * Constructs a new Dragon at the specified coordinates.
     * This constructor also initializes the dragon's hitbox and collision detection, 
     * and loads the dragon's images.
     *
     * @param x the x-coordinate where the dragon will be placed
     * @param y the y-coordinate where the dragon will be placed
     */

    public Dragon(float x, float y) {
        super(x, y, 100,1);
        
        collision = new Collision(1);
        initHitbox(x,y,100, 100);

        dragonImages = LoadFiles.LoadSubImages(DRAGON, 32);

        
    }
    /**
     * Updates the dragon's animation
     * @param player
     */

    public void update(Player player){
        updatePos(player);
    }
    
    /**
     * Renders the dragon on the given GraphicsContext.
     * @param gc
     */
    public void render(GraphicsContext gc) {
            
            gc.drawImage(dragonImages[dragonAction][aniIndex], hitbox.getX(), hitbox.getY(), 100, 100);
            //drawHitbox(gc);
        
    } 

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updatePos(Player player) {
        int oldX = (int) x;
        int oldY = (int) y;
        if(health>0){
            switch (direction) {
            case 0:
                dragonAction = EnemyConstans.RUNBACK;
                y -= speed;
                break;
            case 1:
                dragonAction = EnemyConstans.RUNRIGHT;
                x += speed;
                break;
            case 2:
                dragonAction = EnemyConstans.RUNFORWARD;
                y += speed;
                break;
            case 3:
                dragonAction = EnemyConstans.RUNLEFT;
                x -= speed;
                break;
            case 4:
                dragonAction = EnemyConstans.IDLE;
                break;
        }

        // Change direction randomly for simple AI
        if (Math.random() < 0.01) {
            direction = (int)(Math.random() * 5);
        }
        hitbox.setX(x);
        hitbox.setY(y);

        // If a collision is detected, revert to the old position
        if (collision.checkCollision(hitbox)) {
            x = oldX;
            y = oldY;
            hitbox.setX(x);
            hitbox.setY(y);
          //  System.out.println("Collision detected");
        }
        if (this.checkCollision(this.getHitbox(), player.getHitbox())) {
                
                x = oldX;
                y = oldY;
                hitbox.setX(x);
                hitbox.setY(y);
                
        }
    } 
    }

    
    public void setDirection(int direction){
        this.dragonAction = direction;
        moving = true;
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
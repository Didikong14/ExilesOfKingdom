package cz.cvut.fel.pjv.entity;

import cz.cvut.fel.pjv.map.Collision;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.EnemyConstans.*;

import cz.cvut.fel.pjv.utils.Constanz.EnemyConstans;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.YETI;

import cz.cvut.fel.pjv.gamestates.Dialog;


/**
 * The Yeti class represents a Yeti entity in the game. It extends the Entity class and 
 * adds additional properties and behaviors specific to Yetis, such as direction, speed, 
 * Yeti-specific images, and Yeti actions.
 *
 * The Yeti class also handles the Yeti's collision detection and hitbox initialization, 
 * animation updates, and setting the animation based on the Yeti's movement.
 */

public class Yeti extends Entity{
    private int direction = 0; // 0 = up, 1 = right, 2 = down, 3 = left
    private int speed = 1; // The speed at which the knight moves
    private Image YetiImages[][];
    private int Yeti_action = IDLE;
    private int aniIndex;

    /**
     * Constructor for the Yeti class, initializes the Yeti's position, health, and damage
     * also loading the SubImages for the Yeti
     * @param x
     * @param y
     */
    public Yeti(float x, float y) {
        super(x, y, 30,1);
        
        collision = new Collision(0);
        initHitbox(x,y,40, 40);
        setDialogues();
    
        YetiImages = LoadFiles.LoadSubImages(YETI, 16);

        if(isLogging){
            log.info("Yeti created");
        }
        
    }

    /**
     * Updates the Yeti's position based on its direction and speed. 
     * Also checks for collisions and reverts to the old position if a collision is detected.
     *
     * @param player the player to check for collisions with
     */

    public void update(Player player){
        updatePos(player);
        
    }
    
    /**
     * Renders the Yeti on the screen.
     *
     * @param gc the GraphicsContext to draw the Yeti on
     */

    public void render(GraphicsContext gc) {
            
            gc.drawImage(YetiImages[Yeti_action][aniIndex], hitbox.getX(), hitbox.getY(), 40, 40);
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
                Yeti_action = EnemyConstans.RUNBACK;
                y -= speed;
                break;
            case 1:
                Yeti_action = EnemyConstans.RUNRIGHT;
                x += speed;
                break;
            case 2:
                Yeti_action = EnemyConstans.RUNFORWARD;
                y += speed;
                break;
            case 3:
                Yeti_action = EnemyConstans.RUNLEFT;
                x -= speed;
                break;
            case 4:
                Yeti_action = EnemyConstans.IDLE;
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

    /**
     * Allows the Yeti to speak by setting the current dialogue to the Yeti's next dialogue.
     */

    public void speak(){
        Dialog.currentDialog = dialogues[dialogIndex];
        dialogIndex++;
    }
    
    /**
     * Sets the Yeti's dialogues.
     */

    public void setDialogues(){
        dialogues[0] = "Take my key, and go, you are the chosen one!";
    }

    public void setDirection(int direction){
        this.Yeti_action = direction;
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
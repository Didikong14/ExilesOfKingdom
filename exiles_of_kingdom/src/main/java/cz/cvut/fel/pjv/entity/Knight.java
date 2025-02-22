package cz.cvut.fel.pjv.entity;

import cz.cvut.fel.pjv.map.Collision;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.KNIGHT;

import cz.cvut.fel.pjv.gamestates.Dialog;
import static cz.cvut.fel.pjv.utils.Constanz.NPC;
import static cz.cvut.fel.pjv.utils.Constanz.NPC.*;


/**
 * The Knight class represents a knight entity in the game. It extends the Entity class and 
 * adds additional properties and behaviors specific to knights, such as direction, speed, 
 * knight-specific images, and knight actions.
 *
 * The Knight class also handles the knight's collision detection and hitbox initialization, 
 * animation updates, and setting the animation based on the knight's movement.
 */

public class Knight extends Entity {
    private int direction = 0; // 0 = up, 1 = right, 2 = down, 3 = left
    private int speed = 1; 
    private Image knightImage[][];
    private int knight_action = RUNFORWARD;
    private int aniTick, aniIndex, aniSpeed = 10;

     /**
     * Constructs a new Knight at the specified coordinates.
     * This constructor also initializes the knight's hitbox and collision detection, 
     * sets the knight's dialogues, and loads the knight's images.
     *
     * @param x the x-coordinate where the knight will be placed
     * @param y the y-coordinate where the knight will be placed
     */

    public Knight(float x, float y) {
        super(x, y, 3, 0);
        collision = new Collision(0);
        initHitbox(x,y,26, 30);
        setDialogues();

        knightImage = LoadFiles.LoadSubImages(KNIGHT, 32);

        
    }
    /**
     * Updates the knight's animation tick, sets the animation, and updates the knight's position.
     *
     * @param player the player to check for collisions with
     */
    public void update(Player player){
        updateAnimationTick();
        setAnimation();
        updatePos(player);
        
    }

     /**
     * Renders the knight on the screen.
     *
     * @param gc the GraphicsContext to draw the knight on
     */

    public void render(GraphicsContext gc) {
        gc.drawImage(knightImage[knight_action][aniIndex], hitbox.getX(), hitbox.getY(), 35, 35);
        //drawHitbox(gc);
    }   

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updatePos(Player player) {
        int oldX = (int) x;
        int oldY = (int) y;
        switch (direction) {
            case 0:
                knight_action = NPC.RUNBACK;
                y -= speed;
                break;
            case 1:
                knight_action = NPC.RUNRIGHT;
                x += speed;
                break;
            case 2:
                knight_action = NPC.RUNFORWARD;
                y += speed;
                break;
            case 3:
                knight_action = NPC.RUNLEFT;
                x -= speed;
                break;
        }

        // Change direction randomly for simple AI
        if (Math.random() < 0.01) {
            direction = (int)(Math.random() * 4);
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
                
                //System.out.println("Collision detected between player and knight");
                x = oldX;
                y = oldY;
                hitbox.setX(x);
                hitbox.setY(y);
                
        }
    }
    /**
     * Allows the knight to speak by setting the current dialogue to the knight's next dialogue.
     */
    public void speak(){
        Dialog.currentDialog = dialogues[dialogIndex];
        dialogIndex++;
    }

    private void setAnimation(){
        if (moving)
            knight_action = RUNFORWARD;
        else
            knight_action = RUNBACK;
        
    }

    public void setDirection(int direction){
        this.knight_action = direction;
        moving = true;
    }

    private void updateAnimationTick() {
    aniTick++;
    if (aniTick >= aniSpeed) {
        aniTick = 0;
        aniIndex++;
        if (aniIndex >= getSpriteAmount(knight_action)) { 
            aniIndex = 0;
        }
     }
    }
    /**
     * Sets the knight's dialogues.
     */
    public void setDialogues() {
        dialogues[0] = "Welcome, stranger!";
        dialogues[1] = "I was the king of this realm";
        dialogues[2] = "That was before the dragon came...";
        dialogues[3] = "Now I am nobody";
        dialogues[4] = "I am too old to fight the dragon";
        dialogues[5] = "You can defeat the dragon!";
        dialogues[6] = "Go to the castle and save us!";
        dialogues[7] = "But before that, you need to kill the yeti to get the key";
        dialogues[8] = "Move with WASD, use MOUSE to attack";
        dialogues[9] = "Come back once you kill him. Good luck!";
        dialogues[10] = "Good job you killed Yeti!";
        dialogues[11] = "You can go to the dungeon house down the river and kill the dragon!";
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
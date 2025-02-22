package cz.cvut.fel.pjv.entity;

/*The animations are inspired from https://www.youtube.com/watch?v=nuRXTWJ66vc&list=PL4rzdwizLaxYmltJQRjq18a9gsSyEQQ-0&index=6&ab_channel=KaarinGaming
*/

import cz.cvut.fel.pjv.utils.Constanz.Playerconstans;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.PLAYER;



import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.gamestates.Gamestates;
import cz.cvut.fel.pjv.objects.Doors;
import cz.cvut.fel.pjv.objects.Key;
import cz.cvut.fel.pjv.utils.LoadFiles;

import static cz.cvut.fel.pjv.utils.Constanz.Playerconstans.*;
import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.stopBug;
import static cz.cvut.fel.pjv.utils.Constanz.Directions.*;

/**
 * The Player class represents the main character or player in the game. It extends the Entity class, 
 * inheriting its properties and methods. This class is responsible for handling the player's movements, 
 * animations, and interactions with other entities in the game.
 * 
 * The Player class contains several properties to manage the player's state, such as the player's 
 * current action, direction, speed, and whether the player is moving or attacking. It also manages 
 * the player's interactions with other game objects like doors and keys.
 * 
 * The Player class is instantiated with a position (x, y) and a reference to the GameManager, which 
 * is used to check for collisions and manage the player's inventory. The constructor also initializes 
 * the player's hitbox and animations.
 * 
 * The Player class also contains methods to interact with NPCs (Non-Player Characters) in the game, 
 * check for collisions, and manage the player's inventory.
 * 
 */

public class Player extends Entity {

    private Image Ani[][];
    private int aniTick, aniIndex, aniSpeed = 10;
    private int player_action = IDLEFORWARD;
    private int player_direction = -1;
    private boolean moving = false;
    private boolean attack = false;
    private int playerSpeed = 4;
    private Doors doors;
    private Key key;
    public boolean hasKey= false;
    public boolean doorsVisible = true;
    private GameManager gameManager;


    /**
     * Constructor for player entity
     * 
     * @param x 
     * @param y
     * @param gameManager
     */
    public Player(float x, float y, GameManager gameManager) {
        super(x, y, 1,1);
    
        key = new Key(1472, 1752);
        doors = new Doors(60, 1808); 
        this.gameManager = gameManager;

        
        initHitbox(x,y,26, 30);
        Ani = LoadFiles.LoadSubImages(PLAYER, 48);

        for (int i = 0; i < getSpriteAmout(RUNRIGHT); i++) {
            Ani[RUNLEFT][i] = getFlippedImage(Ani[RUNRIGHT][i]);
        }
        for (int i = 0; i < getSpriteAmout(FIGHTRIGHT); i++) {
            Ani[FIGHTLEFT][i] = Ani[FIGHTRIGHT][i];
        }
    }

    private Image getFlippedImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setScaleX(-1);
        imageView.setTranslateX(image.getWidth());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return imageView.snapshot(params, null);
    }

    /**
     * Here is the update method for the player entity
     * I am updating the player position, animation and interaction with other entities
     * @param knight
     * @param yeti
     * @param dragon
     */
    public void update(Knight knight, Yeti yeti, Dragon dragon) {
        updateAnimationTick();
        setAnimation();
        updatePos(knight, yeti, dragon);
        
    }

    /**
     * Here is the render method for the player entity
     * @param gc
     */
    
    public void render(GraphicsContext gc) { 
            gc.drawImage(Ani[player_action][aniIndex], hitbox.getX()- 32, hitbox.getY() - 44, 90, 90);
            //drawHitbox(gc);
    }

    /**
     * Method for setting the player moving
     * @param moving
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    

    private void updatePos(Knight knight, Yeti yeti, Dragon dragon) {
        int oldX = (int) x;
        int oldY = (int) y;
        
        if (moving) {
            // Store the current position
    
            // Update the position based on the direction
            switch (player_direction) {
                case LEFT:
                    player_action = Playerconstans.RUNLEFT;
                    x -= playerSpeed;
                    
                    break;
                case UP:
                    player_action = Playerconstans.RUNBACK;
                    y -= playerSpeed;
                    if(attack){
                        
                        player_action = Playerconstans.FIGHTBACK;                               
                    } 
                    break;
                case RIGHT:
                    player_action = Playerconstans.RUNRIGHT;
                    x += playerSpeed;
                    if(attack){
                        player_action = Playerconstans.FIGHTRIGHT;
                    }
                    break;
                case DOWN:
                    player_action = Playerconstans.RUNFORWARD;
                    y += playerSpeed;
                    if(attack){
                        player_action = Playerconstans.FIGHTFORWARD;
                    }
                    break;
                
            }
            
            // Update the hitbox to the new position
            hitbox.setX(x);
            hitbox.setY(y);
            // If a collision is detected, revert to the old position
            if (gameManager.getCollision().checkCollision(hitbox) || InteractNPC(knight, yeti, dragon)) {
                x = oldX;
                y = oldY;
                hitbox.setX(x);
                hitbox.setY(y);
                if(isLogging){
                    log.info("Collision detected moving player back");
                }
                
            } 
            if (InteractNPC(knight, yeti, dragon) == false){
                hitbox.setX(x);
                hitbox.setY(y);
            }
            
            if (this.checkCollision(this.getHitbox(), key.getHitbox()) && stopBug == 2) {
                hasKey = true;
                gameManager.getInventory().setKey(true);
                gameManager.getInventory().setQuest(3);
            }

            if(this.checkCollision(this.getHitbox(), doors.getHitbox())){
                if (canOpenDoor()) {
                    if(isLogging){
                        log.info("Opening door");
                    }
                    doors.openDoor();
                    gameManager.getInventory().setKey(false);
                    doorsVisible = false;
                    gameManager.getInventory().setQuest(5);
                } else {
                    if (doors.dialogIndex < 1) {
                        if(isLogging){
                            log.warning("Player does not have key");
                        }
                        Gamestates.CurrentState = Gamestates.DIALOG;
                        doors.speak();
                    }

                    x = oldX;
                    y = oldY;
                    hitbox.setX(x);
                    hitbox.setY(y);
                }
            
               
               
            }
           
        } 
        if(x == 72 && y == 1768){
            doors.dialogIndex = 0;
        }
        
    }

    private void setAnimation() {
        int tempAni = player_action;

        player_action = choosePlayerAction(); // depending on the key pressed, the player action is set

        if (tempAni != player_action) { // if the player action has changed
            resetAni(); // reset the animation so it starts from the beginning
        }

        

    }

    private void resetAni() {
        aniIndex = 0;
        aniTick = 0;
    }

    
    private int choosePlayerAction(){
        if (attack) {
            return player_action;
        }
        if (moving)
            return player_action;
        else 
        return IDLEFORWARD;

    }

    

    /**
     * Method for setting the player direction
     * @param direction
     */

    public void setDirection(int direction){
        this.player_direction = direction;
        moving = true;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmout(player_action)) {
                aniIndex = 0;
                attack = false;
            }
        }
    }


    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    private boolean InteractNPC(Knight knight, Yeti yeti, Dragon dragon){
        
        if (this.checkCollision(this.getHitbox(), knight.getHitbox())) {
            if(knight.dialogIndex < 10){
                Gamestates.CurrentState = Gamestates.DIALOG;
                knight.speak();
                gameManager.getInventory().setQuest(1);

            
            } 
            if (hasKey && knight.dialogIndex < 12){
                Gamestates.CurrentState = Gamestates.DIALOG;
                knight.speak();
                gameManager.getInventory().setQuest(4);
            }
           
            return true;
        }
    
        if (this.checkCollision(this.getHitbox(), yeti.getHitbox())) {
            if (yeti.health > 0) {
                if (attack) {
                    yeti.health -= 1;
                }
                if (yeti.health == 0) {
                    Gamestates.CurrentState = Gamestates.DIALOG;
                    yeti.speak();
                    gameManager.getInventory().setQuest(2);
                }
                return true;
            } else {
                return false;
            }
        }
        if (this.checkCollision(this.getHitbox(), dragon.getHitbox())) {
            if (dragon.health > 0) {
                if (attack) {
                    dragon.health -= 1;
                }
            
                return true;
            } else if (dragon.health == 0){
                Gamestates.CurrentState = Gamestates.WINSTATE;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    

    public boolean canOpenDoor() {
        return hasKey; // The player can open the door if they have the key
    }

    public boolean getHasKey() {
        return this.hasKey;
    }

    public boolean isOnExit() {
        return gameManager.getCollision().isOnExit(x,y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    

}
package cz.cvut.fel.pjv.gamestates;

import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_ENTERING_DUNGEON_X;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_ENTERING_DUNGEON_Y;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_EXITING_DUNGEON_X;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_EXITING_DUNGEON_Y;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_STARTING_X;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.PLAYER_STARTING_Y;
import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.stopBug;
import static cz.cvut.fel.pjv.utils.Constanz.Directions.*;

import java.util.HashMap;
import java.util.Map;
import cz.cvut.fel.pjv.engine.Camera;
import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.entity.Dragon;
import cz.cvut.fel.pjv.entity.Knight;
import cz.cvut.fel.pjv.entity.Player;
import cz.cvut.fel.pjv.entity.Yeti;
import cz.cvut.fel.pjv.map.Collision;
import cz.cvut.fel.pjv.map.Maps;
import cz.cvut.fel.pjv.objects.Doors;
import cz.cvut.fel.pjv.objects.Key;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * The Playing class represents the main gameplay state in the game. It implements the Statemethods interface, 
 * which provides a standard set of methods for game states.
 *
 * The Playing class is responsible for rendering the game world and the entities in it, handling user 
 * input to control the player character, and managing the game's state.
 *
 * The Playing class is instantiated with a reference to the GameManager and the game's Canvas, which 
 * are used to render the game world and handle user input. The constructor also initializes the game world 
 * and the entities in it.

 * The Playing class also contains methods to update the game state, render the game world and the entities in it, 
 * handle mouse and keyboard input, and manage the game's state.
 */

public class Playing implements Statemethods {
    private Camera camera;
    private Maps map;
    private Player player;
    private Yeti yeti = new Yeti(1700, 1600);
    private Knight knight = new Knight(400, 1300);
    private Key key = new Key(1492, 1752);
    private Doors doors = new Doors(60, 1808);
    private Map<KeyCode, Boolean> keys = new HashMap<>();
    public boolean isInDungeon = false;
    private Dragon dragon = new Dragon(776, 888);
   

    /**
     * Constructor for the Playing class. Initializes the Playing with a reference to the GameManager 
     * and the game's Canvas, which are used to render the game world and handle user input.
     *
     * @param gameManager the GameManager to use for game state management
     * @param canvas the Canvas to use for rendering
     */

    public Playing(GameManager gameManager, Canvas canvas) {
        player = new Player(PLAYER_STARTING_X, PLAYER_STARTING_Y, gameManager);
        camera = new Camera(player, gameManager);
        map = new Maps(0);

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.A || e.getCode() == KeyCode.S || e.getCode() == KeyCode.D) {
                keys.put(e.getCode(), true);
            }
        });
        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.A || e.getCode() == KeyCode.S || e.getCode() == KeyCode.D){
                keys.put(e.getCode(), false);
            }
        });
        
        
    }
    /**
     * Updates the game state. This method is called every frame and is responsible 
     * for updating the player character and the entities in the game world.
     *
     * @param gc the GraphicsContext to use for rendering
     */

   public void update(GraphicsContext gc) {
        player.update(knight, yeti, dragon);
        if(!isInDungeon){
            if(yeti.health > 0){
                yeti.update(player);
            } 
            knight.update(player);
        }
        if(isInDungeon){
            dragon.update(player);
           
        }
    }
    
    /**
     * Renders the game world and the entities in it. This method is called every frame and is responsible 
     * for drawing the game world and the entities in it.
     *
     * @param gc the GraphicsContext to use for rendering
     */

    @Override
    public void render(GraphicsContext gc) {
        if(player.isOnExit()){
            switchMap(gc);
        }
        gc.save();  
        camera.render(gc);
        map.render(gc);
        player.render(gc);
        if(!isInDungeon){
                if(yeti.health > 0){
                yeti.render(gc);
            
                } else {
                    if(player.hasKey){
                        stopBug = 1;
                        key = null;
                    } else {
                        stopBug = 2;
                        key.render(gc);
                    }
                }
            
            if(player.doorsVisible){
                doors.render(gc);
            } else {
                doors = null;
            }
            knight.render(gc);
            
        } 
        if(isInDungeon){
            if(dragon.health > 0){
                dragon.render(gc);
            }
        }
        gc.restore();
    }
    
    /**
     * Handles keyboard press events for the game world. This method is called whenever a keyboard 
     * press event is detected and is responsible for controlling the player character.
     *
     * @param event the KeyEvent to handle
     */
    public void keyboardPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                player.setDirection(UP);
                if(isLogging){
                    log.info("W pressed");
                }
                break;
            case S:
                player.setDirection(DOWN);
                if(isLogging){
                    log.info("S pressed");
                }
                break;
            case A:
                player.setDirection(LEFT);
                if(isLogging){
                    log.info("A pressed");
                }
                break;
            case D:
                player.setDirection(RIGHT);
                if(isLogging){
                    log.info("D pressed");
                }
                break;
            case ESCAPE:
                Gamestates.CurrentState = Gamestates.PAUSE;
                if(isLogging){
                    log.info("Game is paused");
                }
                break;
            case E:
                Gamestates.CurrentState = Gamestates.INVENTORY;
                if(isLogging){
                    log.info("Inventory opened");
                }
                break;
            case SPACE:
                if(isLogging){
                    log.info("LOGGING OFF");
                } else {
                    log.info("LOGGING ON");
                }
                isLogging = !isLogging;

                break;
            default:
                break;
        }
    }

    private void switchMap(GraphicsContext gc){
                isInDungeon = !isInDungeon;
                if(isInDungeon){
                        map = new Maps(1);
                        player.render(gc);
                        player.setPosition(PLAYER_ENTERING_DUNGEON_X, PLAYER_ENTERING_DUNGEON_Y);
                } else {
                    map = new Maps(0);
                    player.setPosition(PLAYER_EXITING_DUNGEON_X, PLAYER_EXITING_DUNGEON_Y);
                }
                
        }

    /**
     * Handles keyboard release events for the game world. This method is called whenever a keyboard 
     * release event is detected and is responsible for controlling the player character.
     *
     * @param event the KeyEvent to handle
     */

    public void keyboardRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S || event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
            if (!keys.getOrDefault(KeyCode.W, false) && !keys.getOrDefault(KeyCode.S, false) && !keys.getOrDefault(KeyCode.A, false) && !keys.getOrDefault(KeyCode.D, false)) {
                player.setMoving(false);
            } else {
                if (keys.getOrDefault(KeyCode.W, false)) {
                    player.setDirection(UP);
                } else if (keys.getOrDefault(KeyCode.S, false)) {
                    player.setDirection(DOWN);
                } else if (keys.getOrDefault(KeyCode.A, false)) {
                    player.setDirection(LEFT);
                } else if (keys.getOrDefault(KeyCode.D, false)) {
                    player.setDirection(RIGHT);
                }
            }
        }
    }
    
    /**
     * Handles mouse events for the game world. This method is called whenever a mouse 
     * event is detected and is responsible for controlling the player character.
     *
     * @param event the MouseEvent to handle
     */

    public void mouseHandle(MouseEvent event) {
        switch (event.getButton()) {
            case PRIMARY:
                player.setAttack(true);
                break;
            default:
                break;
        }
    }

    public Collision getCollision() {
        return map.collision;
    }



    public Key getKey() {
        return key;
    }

    

}   
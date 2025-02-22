package cz.cvut.fel.pjv.inputs;

import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.gamestates.Gamestates;

/**
 * The Keyboard class is responsible for handling keyboard input in the game. It is used to 
 * capture keyboard events and delegate them to the appropriate game state.
 
 * The Keyboard class is instantiated with a reference to the Scene and the GameManager. The Scene 
 * is used to set the keyboard event handler, and the GameManager is used to delegate the keyboard 
 * events to the appropriate game state.
 *
 * The Keyboard class contains a constructor that sets the keyboard event handler for the Scene, 
 * and an anonymous inner class that implements the EventHandler interface to handle keyboard events.
 */

public class Keyboard {

    /**
     * Constructor for the Keyboard class. Initializes the Keyboard with a reference to the Scene 
     * and the GameManager, and sets the keyboard event handler for the Scene.
     *
     * @param scene the Scene to set the keyboard event handler for
     * @param gameManager the GameManager to delegate the keyboard events to
     */

    public Keyboard(Scene scene, GameManager gameManager) {

        scene.setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (Gamestates.CurrentState) {
                    case MENU:
                        break;
                    case PLAYING:
                        gameManager.getPlaying().keyboardPress(event);
                        break;
                    case PAUSE:
                        gameManager.getPause().keyboardPress(event);
                        break;
                    case DIALOG:
                        gameManager.getDialog().keyboardPress(event);
                        break;
                    case INVENTORY:
                        gameManager.getInventory().keyboardPress(event);
                        break;
                    default:
                        break;
                    
                }
            }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (Gamestates.CurrentState) {
                    case MENU:
                        break;
                    case PLAYING:
                        gameManager.getPlaying().keyboardRelease(event);
                        break;
                    case PAUSE:
                        gameManager.getPause().keyboardRelease(event);
                        break;
                    case DIALOG:
                        gameManager.getDialog().keyboardRelease(event);
                        break;
                    case INVENTORY:
                        gameManager.getInventory().keyboardRelease(event);
                        break;
                   
                    default:
                        break;
                }
            }
        });

    }

}

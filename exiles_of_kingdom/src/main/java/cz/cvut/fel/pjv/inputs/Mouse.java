package cz.cvut.fel.pjv.inputs;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;

import java.util.logging.Logger;

import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.gamestates.Gamestates;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * The Mouse class is responsible for handling mouse input in the game. It is used to 
 * capture mouse events and delegate them to the appropriate game state.
 *
 * The Mouse class is instantiated with a reference to the Scene and the GameManager. The Scene 
 * is used to set the mouse event handler, and the GameManager is used to delegate the mouse 
 * events to the appropriate game state.
 *
 * The Mouse class contains a constructor that sets the mouse event handler for the Scene, 
 * and an anonymous inner class that implements the EventHandler interface to handle mouse events.

 */

public class Mouse {
    private static final Logger log = Logger.getLogger(Mouse.class.getName());
    /**
     * Constructor for the Mouse class. Initializes the Mouse with a reference to the Scene 
     * and the GameManager, and sets the mouse event handler for the Scene.
     *
     * @param scene the Scene to set the mouse event handler for
     * @param gameManager the GameManager to delegate the mouse events to
     */

    public Mouse(Scene scene, GameManager gameManager) {
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                switch (Gamestates.CurrentState) {
                    case MENU:
                        break;
                    case PLAYING:
                        gameManager.getPlaying().mouseHandle(event);
                        if(isLogging){
                            log.info("Mouse clicked");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        
    }

}
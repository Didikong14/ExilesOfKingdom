package cz.cvut.fel.pjv;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;

import java.util.logging.Logger;

import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.gamestates.Gamestates;
import cz.cvut.fel.pjv.utils.Menu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Exiles_of_Kingdom class is the main class of the game. It extends the Application class from JavaFX, 
 * which means it represents a JavaFX application. It contains the main game loop and handles the game states.
 *
 * 
 * The game loop is encapsulated in the startGameLoop method. It creates a new GameManager, initializes it, 
 * and then starts an AnimationTimer that calls the render and update methods of the GameManager at a set frame rate.
 * 
 */

public class Exiles_of_Kingdom extends Application {
    private static final int FPS_SET = 100;
    private static AnimationTimer gameLoop;
    protected static final Logger log = Logger.getLogger(GameManager.class.getName());

    /**
     * This method is called when the JavaFX application is launched. Depending on the current game state, 
     * it either displays the menu or starts the game loop.
     *
     * @param primStage the primary Stage for this application, onto which the application scene can be set
     * @throws Exception if an error occurs during the execution of the method
     */

    @Override
    public void start(@SuppressWarnings("exports") Stage primStage) throws Exception {
        if(Gamestates.CurrentState == Gamestates.MENU){
           new Menu(primStage);
        }
    }


    /**
     * This method starts the game loop. It creates a new GameManager, initializes it, and then starts an 
     * AnimationTimer that calls the render and update methods of the GameManager at a set frame rate.
     *
     * @param primaryStage the primary Stage for this application, onto which the application scene can be set
     */

    public static void startGameLoop(@SuppressWarnings("exports") Stage primaryStage) {
        GameManager gameManager = new GameManager(primaryStage);
        gameManager.init();

        gameLoop = new AnimationTimer() { 
            int fps = 0;
            long lastUpdate = 0;
            long lastFpsPrint = 0;


                @Override
                public void handle(long now) { // called every frame
                    if (now - lastUpdate >= 1_000_000_000 / FPS_SET) { // 1 second in nanoseconds
                      
                        gameManager.render();
                        gameManager.update();
                        fps++; 
                        lastUpdate = now;
                    }

                    if (now - lastFpsPrint >= 1000000000) {
                        if(isLogging){
                            log.info("FPS: " + fps);
                        }
                        
                        fps = 0;
                        lastFpsPrint = now;
                    }

               }
            };
            gameLoop.start();   
    }

}

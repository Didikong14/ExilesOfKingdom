package cz.cvut.fel.pjv.gamestates;

import cz.cvut.fel.pjv.engine.GameManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.*;


/**
 * The Pause class represents the pause state in the game. It implements the Statemethods interface, 
 * which provides a standard set of methods for game states.
 *
 * The Pause class is responsible for rendering the pause screen on the game screen and handling user 
 * input to unpause the game. The pause screen is displayed as a semi-transparent rectangle in the center 
 * of the screen, with the text "PAUSED" displayed in the middle.

 * The Pause class is instantiated with a reference to the GameManager and the game's Canvas, which 
 * are used to render the pause screen and handle user input. The constructor also initializes the pause 
 * screen's dimensions and position.
 * 
 * The Pause class also contains methods to render the pause screen, handle mouse and keyboard input, 
 * and manage the game's state.

 */
public class Pause implements Statemethods{


    public Pause(GameManager gameManager, Canvas canvas) {
        
    }

    /**
     * Renders the pause screen on the screen. This method is called every frame and is responsible
     * for drawing the pause screen and the text "PAUSED".
     * 
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setFont(new Font(20));
        gc.fillText("PAUSED", WINDOW_WIDTH / 8, WINDOW_HEIGHT / 2);
    }


    @Override
    public void mouseHandle(MouseEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseHandle'");
    }

    /**
     * Handles keyboard input for the pause screen. If the user presses the ESCAPE key, the game is unpaused.
     * 
     */
    @Override
    public void keyboardPress(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE) {
            if (isLogging) {
                log.info("Unpaused");
            }
            
            Gamestates.CurrentState = Gamestates.PLAYING;
            System.out.println("Unpaused");
        }
        if(event.getCode() == KeyCode.SPACE){
            isLogging = !isLogging;
        }
    }
    
    
    @Override
    public void keyboardRelease(KeyEvent event) {

    }
    


    
}

package cz.cvut.fel.pjv.gamestates;

import cz.cvut.fel.pjv.engine.GameManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The WinState class represents the winning state in the game. It implements the Statemethods interface, 
 * which provides a standard set of methods for game states.
 
 * The WinState class is responsible for rendering the win screen on the game screen. The win screen is 
 * displayed as a text "You Win!" in the center of the screen.

 * The WinState class is instantiated with a reference to the GameManager and the game's Canvas, which 
 * are used to render the win screen.

 * The WinState class also contains methods to render the win screen, handle mouse and keyboard input.
 */
public class WinState implements Statemethods{

    /**
     * Constructor for the WinState class. Initializes the WinState with a reference to the GameManager 
     * and the game's Canvas, which are used to render the win screen.
     *
     * @param gameManager the GameManager to use for game state management
     * @param canvas the Canvas to use for rendering
     */
    public WinState(GameManager gameManager, Canvas canvas) {
        
    }

    /**
     * Renders the win screen on the screen. This method is called every frame and is responsible 
     * for drawing the "You Win!" text.
     *
     * @param gc the GraphicsContext to use for rendering
     */
    
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        gc.fillText("You Win!", 250, 250); // Adjust the position as needed
    }

    @Override
    public void mouseHandle(MouseEvent event) {
        
    }

    @Override
    public void keyboardPress(KeyEvent event) {
        
    }

    @Override
    public void keyboardRelease(KeyEvent event) {
       
    }
    
}

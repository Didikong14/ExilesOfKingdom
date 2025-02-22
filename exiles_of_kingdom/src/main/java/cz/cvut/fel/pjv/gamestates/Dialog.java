package cz.cvut.fel.pjv.gamestates;

import cz.cvut.fel.pjv.engine.GameManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


import static cz.cvut.fel.pjv.utils.Constanz.GameSize.*;

/**
 * The Dialog class represents a dialog box in the game. It implements the Statemethods interface, 
 * which provides a standard set of methods for game states.
 * 
 * The Dialog class is responsible for rendering the dialog box on the game screen, handling user 
 * input to progress through the dialog, and managing the current dialog text.

 * The Dialog class contains several properties to manage the dialog box, such as the current dialog 
 * text, the prompt text, and the dimensions and position of the dialog box. The dimensions and 
 * position of the dialog box are calculated based on the window size to ensure that the dialog box 
 * is always centered and takes up a consistent portion of the screen.

 * The Dialog class is instantiated with a reference to the GameManager and the game's Canvas, which 
 * are used to render the dialog box and handle user input. The constructor also initializes the 
 * dialog box's dimensions and position.
 */

public class Dialog implements Statemethods{

    public static String currentDialog = "";
    public String text = "Press Enter for next dialog...";

    int width = (int)(WINDOW_WIDTH * 0.9); 
    int height = (int)(WINDOW_HEIGHT * 0.3); // Take up 30% of the height
    int x = (WINDOW_WIDTH - width) / 2; // Center horizontally
    int y = (int)(WINDOW_HEIGHT * 0.6); // Start from 70% of the height

    /**
     * Constructor for the Dialog class. Initializes the Dialog with a reference to the GameManager 
     * and the game's Canvas, which are used to render the dialog box and handle user input.
     *
     * @param gameManager the GameManager to use for game state management
     * @param canvas the Canvas to use for rendering
     */
    public Dialog(GameManager gameManager, Canvas canvas) {
        
    }

    /**
     * Renders the dialog box on the screen. This method is called every frame and is responsible 
     * for drawing the dialog box and the current dialog text.
     *
     * @param gc the GraphicsContext to use for rendering
     */

    @Override
    public void render(GraphicsContext gc) {
        drawSubwindow(gc);
    }


    @Override
    public void mouseHandle(MouseEvent event) {
        
    }

    private void drawSubwindow(GraphicsContext gc) {
        int arcWidth = 30; // Width of the corner arcs
        int arcHeight = 30; // Height of the corner arcs
    
        Color color = new Color(0.0, 0.0, 0.0, 0.3);
        gc.setFill(color);
        gc.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    
        // Draw a white border
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3); // Set the line width to 3
        gc.strokeRoundRect(x, y, width, height, arcWidth, arcHeight);
        
        drawDialog(gc);
        // Display the dialog
        gc.setFont(new Font(15));
        gc.fillText(text, x + width * 0.8, y + height * 0.9);

    }

    private void drawDialog(GraphicsContext gc) {
        // Set the text color to white
        gc.setFill(Color.WHITE); 

        // Set the font size to 20
        gc.setFont(new Font(20));

        // Center the text
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        // Display the dialog
        // Display the dialog
      
        gc.fillText(currentDialog, x + width / 2, y + height / 2);
        
        
    }
    /**
     * Handles keyboard input for the dialog box. This method is called when the user presses a key
     */
    @Override
    public void keyboardPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Gamestates.CurrentState = Gamestates.PLAYING;
        }
    }

    @Override
    public void keyboardRelease(KeyEvent event) {
    
    }
}

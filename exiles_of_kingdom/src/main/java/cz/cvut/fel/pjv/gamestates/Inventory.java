package cz.cvut.fel.pjv.gamestates;

import cz.cvut.fel.pjv.engine.GameManager;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.*;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.KEY;

/**
 * The Inventory class represents the player's inventory in the game. It implements the Statemethods interface, 
 * which provides a standard set of methods for game states.
 *
 
 * The Inventory class is responsible for rendering the inventory on the game screen, handling user 
 * input to navigate through the inventory, and managing the items in the inventory. The inventory is 
 * displayed as a semi-transparent rectangle on the right side of the screen, with slots for items and a cursor 
 * to select items.
 
 * The Inventory class contains several properties to manage the inventory, such as the dimensions and position 
 * of the inventory, the current selected slot, and the items in the inventory. The dimensions and position of 
 * the inventory are calculated based on the window size to ensure that the inventory takes up a consistent 
 * portion of the screen.
 *
 * The Inventory class also contains methods to render the inventory, handle mouse and keyboard input, 
 * draw the inventory and items on the screen, and manage the player's quests.

 */

public class Inventory implements Statemethods{

    int frameWidth = (int)(WINDOW_WIDTH * 0.419); 
    int frameHeight = (int)(WINDOW_HEIGHT * 0.15); // Take up 30% of the height
    int frameX = (WINDOW_WIDTH - frameWidth); // Center horizontally
    int frameY = (int)(WINDOW_HEIGHT * 0.1); // Start from 70% of the height
    int slotCol = 0;
    int slotRow = 0;
    boolean hasKey;
    int setQuest;

    private Image keyImage = LoadFiles.LoadImage(KEY);

    /**
     * Constructor for the Inventory class. Initializes the Inventory with a reference to the GameManager 
     * and the game's Canvas, which are used to render the inventory and handle user input.
     *
     * @param gameManager the GameManager to use for game state management
     * @param canvas the Canvas to use for rendering
     */

    public Inventory(GameManager gameManager, Canvas canvas) {

    }

    /**
     * Renders the inventory on the screen. This method is called every frame and is responsible 
     * for drawing the inventory and the items in it.
     *
     * @param gc the GraphicsContext to use for rendering
     */

    @Override   
    public void render(GraphicsContext gc) {
        drawSubwindow(gc);
        drawQuest(gc);  
    }


    @Override
    public void mouseHandle(MouseEvent event) {
        
    }

    private void drawSubwindow(GraphicsContext gc) {
        int arcWidth = 30; // Width of the corner arcs
        int arcHeight = 30; // Height of the corner arcs
    
        Color color = new Color(0.0, 0.0, 0.0, 0.3);
        gc.setFill(color);
        gc.fillRoundRect(frameX, frameY, frameWidth, frameHeight, arcWidth, arcHeight);
    
        // Draw a white border
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3); // Set the line width to 3
        gc.strokeRoundRect(frameX, frameY, frameWidth, frameHeight, arcWidth, arcHeight);
        

        //SLOTS
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        final int slotX = slotXstart;
        final int slotY = slotYstart;

        // Draw the slots


       
        if (this.hasKey) {    
            gc.drawImage(keyImage, slotX, slotY, 50, 50);
        }
        
        
        //CURSOR    
        int cursorX = slotXstart + (slotCol * 50);
        int cursorY = slotYstart + (slotRow * 50);
        int cursorWidth = 50;
        int cursorHeight = 50;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


    }

    
    /**
     * Handles keyboard input for navigating the inventory and selecting items. This method is called
     * when the user presses a key.
     */
    @Override
    public void keyboardPress(KeyEvent event) {
        if (event.getCode() == KeyCode.E) {
            Gamestates.CurrentState = Gamestates.PLAYING;
        }
        if(event.getCode() == KeyCode.A) {
            if(isLogging){
                log.info("Moving cursor left");
            }
            slotCol--;
            if(slotCol < 0) {
                slotCol = 0;
            }
        }
        if(event.getCode() == KeyCode.D) {
            if(isLogging){
                log.info("Moving cursor right");
            }
            slotCol++;
            if(slotCol >= 5){
                slotCol = 5;
            }
        }
        if(event.getCode() == KeyCode.SPACE){
            isLogging = !isLogging;
        }
    }

    private void drawQuest(GraphicsContext gc) {
        int width = (int)(WINDOW_WIDTH * 0.3); // Take up 20% of the width
        int height = (int)(WINDOW_HEIGHT * 0.2); // Take up 20% of the height
        int x = 0; // Start from the left edge
        int y = 0; // Start from the top edge
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, width, height);
        gc.setFill(Color.WHITE);
        // Set the text color to white
        gc.setFill(Color.WHITE);

        // Set the font size to 20
        gc.setFont(new Font(20));

        // Center the text
        gc.setTextAlign(TextAlignment.CENTER);

        // Display the quest
        gc.fillText(writeQuest(setQuest), x + width / 2, y + height / 2);
    }

    public void setQuest(int setQuest){
        this.setQuest = setQuest;
    }

    private String writeQuest(int setQuest){
        this.setQuest = setQuest;
        
        switch (setQuest) {
            case 0:
                return "Go talk to the knight";
            case 1:
                return "Kill the yeti";
            case 2:
                return "Find the Key";
            case 3:
                return "Go talk to the knight";
            case 4:
                return "Open the door";
            case 5: 
                return "Kill the dragon";
            case 6: 
                return "You won!";
            default:
                return null;
        }
    }

    @Override
    public void keyboardRelease(KeyEvent event) {
    
    }

	public void setKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    
}

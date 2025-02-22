package cz.cvut.fel.pjv.engine;


import cz.cvut.fel.pjv.entity.Player;
import cz.cvut.fel.pjv.gamestates.Dialog;
import cz.cvut.fel.pjv.gamestates.Gamestates;
import cz.cvut.fel.pjv.gamestates.Inventory;
import cz.cvut.fel.pjv.gamestates.Pause;
import cz.cvut.fel.pjv.gamestates.Playing;
import cz.cvut.fel.pjv.gamestates.WinState;
import cz.cvut.fel.pjv.inputs.Keyboard;
import cz.cvut.fel.pjv.inputs.Mouse;
import cz.cvut.fel.pjv.map.Collision;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.WINDOW_HEIGHT;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.WINDOW_WIDTH;

import java.util.logging.Logger;



/**
 * The GameManager class is the central hub of the game's logic. It manages the game's state, 
 * handles rendering and updates, and provides access to key game components such as the player, 
 * the game states, and the input handlers.
 *
 * The GameManager is responsible for initializing the game, including setting up the game window 
 * and creating the game states. It also handles the game loop, which includes rendering the current 
 * game state and updating the game logic.
 *
 * The GameManager provides getter methods for accessing the current game state and other key game 
 * components. This allows other parts of the game to interact with these components without having 
 * to know the details of their implementation.
 */

public class GameManager {

    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private Stage stage;
    public Player player;
    private Playing playing;
    private Pause pause;
    private Dialog dialog;
    public Keyboard keyboard;
    private Inventory inventory;
    private WinState winstate;
    
    protected final Logger log;  
    

     /**
     * Constructs a new GameManager for the specified stage.
     * This constructor initializes the game window and the game states, and sets up the input handlers.
     *
     * @param stage the stage for the game
     */

    public GameManager(Stage stage) {
        log = Logger.getLogger(GameManager.class.getName());
        this.stage = stage;

        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        pane = new Pane(canvas);
        scene = new Scene(pane);
        
        
        playing = new Playing(this, canvas);
        pause = new Pause(this, canvas);
        dialog = new Dialog(this, canvas);
        inventory = new Inventory(this, canvas);
        winstate = new WinState(this, canvas);

        new Keyboard(scene, this);
        new Mouse(scene, this);
        if(isLogging){
            log.info("GameManager created");
        }
    }

    /**
     * Initializes the game window. This includes setting the window title, size, and scene, 
     * and displaying the window.
     */
    public void init() {
        stage.setTitle("Exiles_of_Kingdoms");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        if(isLogging){
            log.info("Game window initialized");
        }
    }
    /**
     * Renders the current game state. This includes clearing the canvas and drawing the game elements 
     * for the current state.
     */
    public void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        switch (Gamestates.CurrentState) {
            case PLAYING:
                playing.render(gc);
                break;
            case MENU: 
                break;
            case PAUSE:
                pause.render(gc);
                break;
            case DIALOG:
                dialog.render(gc);
                break;
            case INVENTORY:
                inventory.render(gc);
                break;
            case WINSTATE:
                winstate.render(gc);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the game logic for the current game state. This includes updating the game elements 
     * and checking for game events.
     */
    public void update() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        switch (Gamestates.CurrentState) {
            case PLAYING:
                playing.update(gc);
                break;
            case MENU:
                break;
            case PAUSE:
                break;
            case DIALOG:
                break;
            case INVENTORY:
                break;
            
            default:
                break;
        }
    }

    
    public Scene getScene() {
        return scene;
    }

    public Player getPlayer() {
        return player;
    }

    
    public Playing getPlaying() {
        return playing;
    }

    public static GameManager getInstance() {
        return null;
    }

    
    public Pause getPause() {
       return pause;
    }

    
    public Dialog getDialog() {
        return dialog;
    }

    
    public Inventory getInventory(){
        return inventory;
    }

    
    public Collision getCollision() {
        return playing.getCollision();
    }

}

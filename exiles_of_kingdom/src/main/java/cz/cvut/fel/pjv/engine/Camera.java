package cz.cvut.fel.pjv.engine;

import cz.cvut.fel.pjv.entity.Player;
import javafx.scene.canvas.GraphicsContext;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.*;

/**
 * The Camera class is responsible for tracking the player's position in the game world.
 * It adjusts its position to keep the player centered on the screen, unless the player
 * is near the edge of the game world, in which case it stops moving to prevent showing
 * areas outside of the game world.
 */
public class Camera {
    private Player player;
    @SuppressWarnings("unused")
    private GameManager gameManager;
    private double cameraX, cameraY;

    /**
     * Constructs a new Camera that will follow the specified player.
     *
     * @param player the player that this camera will follow
     */
    public Camera(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }


    private void moveCameraCoord() {
        cameraX = player.getX() - WINDOW_WIDTH / 2 + PLAYER_WIDTH / 2;
        cameraY = player.getY() - WINDOW_HEIGHT / 2 + PLAYER_HEIGHT / 2;

        checkOutOfBounds();
        
    }

    /**
     * Renders the camera's view on the given GraphicsContext.
     * This method also updates the camera's position before rendering.
     *
     * @param gc the GraphicsContext on which to render the camera's view
     */
    public void render(GraphicsContext gc) {
        gc.translate(-cameraX, -cameraY);
        moveCameraCoord();
    }

    
    private void checkOutOfBounds() {
        cameraX = Math.max(0, Math.min(cameraX, MAP_HEIGHT - WINDOW_WIDTH)); 
        cameraY = Math.max(0, Math.min(cameraY, MAP_WIDTH - WINDOW_HEIGHT)); 
        
    }
  
    public double getX() {
        return cameraX;
    }

    public double getY() {
        return cameraY;
    }
}
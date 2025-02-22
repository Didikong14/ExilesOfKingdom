package cz.cvut.fel.pjv.map;

import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.DUNGEON;
import static cz.cvut.fel.pjv.utils.Constanz.Strings.MAP;
import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.MAP_HEIGHT;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.MAP_WIDTH;

import java.util.logging.Logger;

/**
 * The Maps class is responsible for loading and rendering the game maps. It uses an image file 
 * and a collision map loaded from a text file to represent each map.
 *
 * The Maps class is instantiated with an index to select the appropriate map from an array 
 * of map names. The selected map image is then loaded into an Image object, and the corresponding 
 * collision map is loaded into a Collision object.
 *
 * The Maps class contains methods to render the map image on a GraphicsContext, and a public 
 * Collision object that can be used to check for collisions with the map.
*
 */

public class Maps {
    private Image mapImage;
    public Collision collision;
    private static final Logger log = Logger.getLogger(Maps.class.getName());
    /**
     * Constructor for the Maps class. Initializes the Maps with a selected map image and collision 
     * map, which are loaded based on the provided map index.
     *
     * @param mapIndex the index of the map to load
     */

    public Maps(int mapIndex) {
        if(isLogging){
            log.info("Creating map");
        }
        String[] mapString = {MAP, DUNGEON};
        mapImage = LoadFiles.LoadImage(mapString[mapIndex]);
        collision = new Collision(mapIndex);
    }

    /**
     * Renders the map image on a GraphicsContext. The map image is drawn at the top-left corner 
     * of the GraphicsContext, and is scaled to fit the size of the map as defined by MAP_HEIGHT 
     * and MAP_WIDTH.
     *
     * @param gc the GraphicsContext to render the map image on
     */
    public void render(GraphicsContext gc) {
        if (mapImage != null) {
            gc.drawImage(mapImage, 0, 0, MAP_HEIGHT, MAP_WIDTH);
        }
    }
}


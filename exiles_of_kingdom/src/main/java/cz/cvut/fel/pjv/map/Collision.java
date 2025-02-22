package cz.cvut.fel.pjv.map;

import java.util.ArrayList;
import cz.cvut.fel.pjv.utils.LoadFiles;
import javafx.scene.shape.Rectangle;

import static cz.cvut.fel.pjv.utils.Constanz.Strings.COLLISION;
import static cz.cvut.fel.pjv.utils.Constanz.Strings.DUNGEONCOLLISON;

/**
 * The Collision class is responsible for handling collision detection in the game. It uses a 
 * collision map loaded from a text file to determine whether a given tile is solid or not.
 
 * The Collision class is instantiated with an index to select the appropriate map from an array 
 * of map names. The selected map is then loaded into a 2D ArrayList of Integers, where each 
 * Integer represents a tile in the map.
 * 
 * The Collision class contains methods to check whether a given tile is solid, whether a given 
 * tile is an exit, and whether a given entity (represented by a Rectangle) is colliding with a 
 * solid tile.
 * 
 */

public class Collision {
    private ArrayList<ArrayList<Integer>> collisionMap;

    /**
     * Constructor for the Collision class. Initializes the Collision with a selected map, which 
     * is loaded into a 2D ArrayList of Integers.
     *
     * @param MapIndex the index of the map to load
     */
    public Collision(int MapIndex) {
        String[] MapNames = {COLLISION, DUNGEONCOLLISON};
        collisionMap = LoadFiles.LoadTxt(MapNames[MapIndex]);

    }

    /**
     * Checks whether a given tile is solid. A tile is considered solid if its value in the 
     * collision map is 1.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return true if the tile is solid, false otherwise
     */
    public boolean isSolid(double x, double y) {
        int tileX = (int) (x / 36);
        int tileY = (int) (y / 40);
    
        return collisionMap.get(tileY).get(tileX) == 1;
    }

    /**
     * Checks whether a given tile is an exit. A tile is considered an exit if its value in the 
     * collision map is 2.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return true if the tile is an exit, false otherwise
     */
    public boolean isOnExit(double x, double y) {
        int tileX = (int) (x / 36);
        int tileY = (int) (y / 40);
    
        return collisionMap.get(tileY).get(tileX) == 2;
    }

    

    /**
     * Checks whether a given entity (represented by a Rectangle) is colliding with a solid tile. 
     * This is done by checking each corner of the Rectangle against the collision map.
     *
     * @param hitbox the Rectangle representing the entity
     * @return true if the entity is colliding with a solid tile, false otherwise
     */
    public boolean checkCollision(Rectangle hitbox) {
        return isSolid(hitbox.getX(), hitbox.getY()) ||
                isSolid(hitbox.getX() + hitbox.getWidth(), hitbox.getY()) ||
                isSolid(hitbox.getX(), hitbox.getY() + hitbox.getHeight()) ||
                isSolid(hitbox.getX() + hitbox.getWidth(), hitbox.getY() + hitbox.getHeight());
    }

}
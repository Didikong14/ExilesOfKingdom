package cz.cvut.fel.pjv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * The LoadFiles class provides static methods for loading game assets such as images and text files.
 * These assets are loaded from the resource path of the class.
 * The LoadFiles class contains methods to load an image and store it in an Image object, load a sprite sheet 
 * and store it in a 2D array of Image objects, and load a text file and store it in a 2D ArrayList of Integers.
 * This class is inspired by my fellow Asian friend @transonn
 *
 */

public class LoadFiles {

    /**
     * Loads an image from the resource path and stores it in an Image object.
     *
     * @param sheet the path of the image file
     * @return the loaded Image object, or null if an error occurred
     */
    public static Image LoadImage(String sheet) {
        URL url = LoadFiles.class.getResource(sheet);
        Image img = null;
        try {
            img = new Image(url.toString());
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Loads a sprite sheet from the resource path and stores it in a 2D array of Image objects.
     * Each sprite in the sheet is assumed to be a square of size pixSize.
     *
     * @param sheet the path of the sprite sheet file
     * @param pixSize the size of each sprite in the sheet
     * @return the 2D array of Image objects representing the sprites in the sheet
     */
    public static Image[][] LoadSubImages(String sheet, int pixSize) {
        Image img = LoadImage(sheet);
        
        PixelReader reader = img.getPixelReader();
        int width = (int) img.getWidth() / pixSize;
        int height = (int) img.getHeight() / pixSize;
        Image sprites[][] = new Image[height][width];
        
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                sprites[j][i] = new WritableImage(reader, i * pixSize, j * pixSize, pixSize, pixSize);
            }
        }
        return sprites;
         
    }

    /**
     * Loads a text file from the resource path and stores it in a 2D ArrayList of Integers.
     * Each line in the text file is assumed to be a row in the 2D ArrayList, and each character in the line 
     * is assumed to be an integer in the row.
     *
     * @param sheet the path of the text file
     * @return the 2D ArrayList of Integers representing the contents of the text file
     */
    public static ArrayList<ArrayList<Integer>> LoadTxt(String sheet) {
        String line;
        char c;
        ArrayList<ArrayList<Integer>> collisionMap;
        ArrayList<Integer> row;

         // using arraylist inside of arraylist to store 2D array
         // we don't know the size of it
        collisionMap = new ArrayList<ArrayList<Integer>>();
    
        URL url = LoadFiles.class.getResource(sheet);
        
        try {
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            while ((line = reader.readLine()) != null) {
                row = new ArrayList<Integer>();
                line = line.replaceAll("\\s", ""); // remove all whitespaces

                // adding each number from the line to the ArrayList row that is inside of the ArrayList collisionMap
                for (int i = 0; i < line.length(); i++) {
                    c = line.charAt(i); // get character at index of String line
                    row.add(Character.getNumericValue(c)); // change ASCII value of number to normal integer
                }
                collisionMap.add(row);
            }
            
            reader.close();
        } catch (IOException e) {
            
            System.out.println("Error loading txt: " + e.getMessage());
            e.printStackTrace();
        }

        
        return collisionMap;
    }

}

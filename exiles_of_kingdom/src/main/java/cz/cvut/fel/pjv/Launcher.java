package cz.cvut.fel.pjv;


/**
 * The Launcher class is responsible for launching the game. It contains the main method that
 * starts the GameManager application. From this class, the game is suppose to be started
 */

public class Launcher {

    public static void main(String[] args) {
        // Launch the game by starting the GameManager application
        Exiles_of_Kingdom.launch(Exiles_of_Kingdom.class, args);
    }
}

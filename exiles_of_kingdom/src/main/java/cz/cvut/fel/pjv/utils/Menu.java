package cz.cvut.fel.pjv.utils;

import static cz.cvut.fel.pjv.Exiles_of_Kingdom.startGameLoop;
import static cz.cvut.fel.pjv.utils.Constanz.isLogging;
import static cz.cvut.fel.pjv.utils.Constanz.GameSize.*;
import static cz.cvut.fel.pjv.utils.Constanz.Strings.MENU;
import static cz.cvut.fel.pjv.utils.LoadFiles.LoadImage;

import java.util.logging.Logger;

import cz.cvut.fel.pjv.gamestates.Gamestates;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Menu class represents the main menu of the game. It provides methods for setting up the menu,
 * handling button actions, and displaying the menu on a Stage.
 *
 * The Menu class contains a constructor that takes a Stage as a parameter and sets up the menu on that Stage.
 * It also contains private methods for setting up the VBox layout, setting the title, setting the background,
 * styling the buttons, and setting up the Stage.

 *
 */

public class Menu { 
    private Button startbutton, settingbutton, exitbutton;
    private static final Logger log = Logger.getLogger(Menu.class.getName());
    /**
     * Constructs a new Menu on the specified Stage.
     *
     * @param primStage the Stage on which to display the menu
     */

    public Menu(Stage primStage) {
        log.info("Menu created");
        VBox vbox = new VBox(10);
        Image image = LoadImage(MENU);
        StackPane root = new StackPane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);


        setBackground(root, image);
        
        setTitle(vbox);

        startbutton = new Button("Play");
        settingbutton = new Button("Controls");
        exitbutton = new Button("Exit");
        startbutton.setOnAction(e -> {
            log.info("Start button clicked");
            Gamestates.CurrentState = Gamestates.PLAYING;
            startGameLoop(primStage);
        });

        settingbutton.setOnAction(e -> {
            log.info("Settings button clicked");
            VBox settingsVBox = new VBox(10);
            settingsVBox.setAlignment(Pos.CENTER);
        
            Label label1 = new Label("Use the WASD to move the player");
            label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            label1.setTextFill(Color.BLACK);
            settingsVBox.getChildren().add(label1);
        
            Label label2 = new Label("Use Primary Mouse Button to attack");
            label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            label2.setTextFill(Color.BLACK);
            settingsVBox.getChildren().add(label2);
        
            Label label3 = new Label("Use E to open invetory, you will see there quest!");
            label3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            label3.setTextFill(Color.BLACK);
            settingsVBox.getChildren().add(label3);
        
            Button backButton = new Button("Back");
            backButton.setOnAction(event -> primStage.setScene(scene));
            if(isLogging){
                log.info("Back button clicked");
            }
            settingsVBox.getChildren().add(backButton);
        
            Scene settingsScene = new Scene(settingsVBox, WINDOW_WIDTH, WINDOW_HEIGHT);
            primStage.setScene(settingsScene);
        });

        exitbutton.setOnAction(e -> {
            log.info("Exit button clicked");
            primStage.close();
        });


        setUpVBOX(vbox, root);
        styleButton(startbutton);
        styleButton(settingbutton);
        styleButton(exitbutton);


        setStage(primStage, scene);
    }
    
    

    private void setStage(Stage primaryStage, Scene scene) {
        primaryStage.setTitle("Exiles of the Kingdom");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setUpVBOX(VBox vbox, StackPane root) {
        vbox.setSpacing(20); // Add some space between the buttons
        vbox.getChildren().addAll(startbutton, settingbutton, exitbutton);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
    }
    
    private void setTitle(VBox vBox) {
        Text title = new Text("EXILES OF THE KINGDOM");
        title.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 50)); // Change the font
        title.setFill(Color.BLACK);
        vBox.getChildren().add(title);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0, 0, 150, 0)); // Add space at the top
    }
    
    private void setBackground(StackPane root, Image image) {
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage)); // Set the background image
    }
    
    private void styleButton(Button button) {
        button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20)); // Set the font of the button
        button.setPrefWidth(200); // Set the preferred width of the button
        button.setTextFill(Color.rgb(205, 133, 63)); // Set the text color of the button to a brownish color
        button.setStyle("-fx-background-color: #8B4513; -fx-border-color: #CD853F; -fx-border-width: 4px; -fx-border-radius: 10px; -fx-background-radius: 10px;"); // Set the background color and border of the button to a darker brown and round the corners
    
        // Change the color of the button when the mouse is over it
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #CD853F; -fx-text-fill: #8B4513; -fx-border-color: #CD853F; -fx-border-width: 4px; -fx-border-radius: 10px; -fx-background-radius: 10px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #8B4513; -fx-text-fill: #CD853F; -fx-border-color: #CD853F; -fx-border-width: 4px; -fx-border-radius: 10px; -fx-background-radius: 10px;"));
    }

}

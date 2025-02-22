package cz.cvut.fel.pjv.gamestates;


import java.util.logging.Logger;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface Statemethods {
   Logger log = Logger.getLogger(Statemethods.class.getName());

   

   public void render(GraphicsContext gc);

   public void mouseHandle(MouseEvent event);

   public void keyboardPress(KeyEvent event);

   public void keyboardRelease(KeyEvent event);
   

}

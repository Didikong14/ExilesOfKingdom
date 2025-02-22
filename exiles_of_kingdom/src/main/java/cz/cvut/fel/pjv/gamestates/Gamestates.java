package cz.cvut.fel.pjv.gamestates;

public enum Gamestates {
    PLAYING, MENU, PAUSE, DIALOG, INVENTORY, GAMEOVER, WINSTATE;

    public static Gamestates CurrentState = MENU;
}

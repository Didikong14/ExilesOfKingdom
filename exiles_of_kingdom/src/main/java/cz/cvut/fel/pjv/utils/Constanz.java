package cz.cvut.fel.pjv.utils;

    /**
     * The Constanz class provides a set of constanz, that are used for the game, so the code
     * is much more cleaner. It contains the constanz for the game size, strings, player and npc
     * constanz, directions and enemy constanz.
     * 
     */
public class Constanz {

        /**
     * The GameSize class contains constants related to the size and positioning of game elements.
     */

    public static class GameSize {
        public static final int WINDOW_WIDTH = 800;
        public static final int WINDOW_HEIGHT = 600;
        public static final int PLAYER_WIDTH = 64;
        public static final int PLAYER_HEIGHT = 64;
        public static final int MAP_WIDTH = 2000;
        public static final int MAP_HEIGHT = 1800;
        public static final int PLAYER_STARTING_X = 400;
        public static final int PLAYER_STARTING_Y = 1200;  
        public static final int PLAYER_ENTERING_DUNGEON_X = 800;
        public static final int PLAYER_ENTERING_DUNGEON_Y = 72;
        public static final int PLAYER_EXITING_DUNGEON_X = 72;
        public static final int PLAYER_EXITING_DUNGEON_Y = 1850;
    }

    /**
     * The Strings class contains string constants used as identifiers for game assets.
     */

    public static class Strings {
        public static final String DRAGON = "BlueDragon.png";
        public static final String MAP = "NewMap.png";
        public static final String PLAYER = "player.png";
        public static final String COLLISION = "map_collision.txt";
        public static final String KNIGHT = "npc.png";
        public static final String MENU = "Menu.png";
        public static final String YETI = "Yeti.png";
        public static final String KEY = "Key.png";
        public static final String DOORS = "Doors.png";
        public static final String DUNGEON = "Dungeon.png";
        public static final String DUNGEONCOLLISON = "dungeons_collision.txt";
    }

    /**
     * The Playerconstans class contains constants related to player actions and a method to get the number of sprites for a given action.
     */

    public static class Playerconstans{
        public static final int IDLEFORWARD = 0;
        public static final int RUNLEFT = 1;
        public static final int IDLEBACK = 2;
        public static final int RUNFORWARD = 3;
        public static final int RUNRIGHT = 4;
        public static final int RUNBACK = 5;
        public static final int FIGHTFORWARD = 6;
        public static final int FIGHTRIGHT = 7;
        public static final int FIGHTBACK = 8;
        public static final int FIGHTLEFT = 9;
   
    
    public static int getSpriteAmout(int player_action){
        switch (player_action) {
            case IDLEFORWARD:
                return 6;
            case RUNLEFT:
                return 6;
            case IDLEBACK:
            case RUNFORWARD:
            case RUNRIGHT:
            case RUNBACK:
                return 6;
            case FIGHTFORWARD:
            case FIGHTRIGHT:
            case FIGHTBACK:
                return 4;
            case FIGHTLEFT:
                return 3;
            default:
                return 1;
        }
    }

    }   
    /**
     * The Directions class contains constants related to the direction of movement.
     */

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
        public static final int UP_LEFT = 4;
        public static final int UP_RIGHT = 5;
        public static final int DOWN_LEFT = 6;
        public static final int DOWN_RIGHT = 7;
    }
    /**
     * The NPC class contains constants related to NPC actions and a method to get the number of sprites for a given action.
     */
    public static class NPC {
        public static final int RUNLEFT = 3;
        public static final int RUNFORWARD = 0;
        public static final int RUNBACK = 1;
        public static final int RUNRIGHT = 2;

    public static int getSpriteAmount(int knight_action){
            
        switch (knight_action) {
                case RUNLEFT:
                    return 5;
                case RUNFORWARD:
                case RUNRIGHT:
                case RUNBACK:
                    return 5;
        
                default:
                    return 1;
            }
        }
    }
    /**
     * The EnemyConstans class contains constants related to enemy types and states and a method to get the number of sprites for a given type and state.
     */
    public static class EnemyConstans{
        public static final int Yeti = 0;
        public static final int Dragon = 1;

        public static final int RUNFORWARD = 0;
        public static final int RUNBACK = 1;
        public static final int RUNRIGHT = 2;
        public static final int RUNLEFT = 3;
        public static final int IDLE = 4;
        public static final int DEAD = 7;

        public static int GetSpriteAmount(int enemy_type, int enemy_state){
            switch (enemy_type) {
                case Yeti:
                    switch (enemy_state) {
                        case IDLE:
                        case RUNBACK:
                        case RUNRIGHT:
                        case RUNLEFT:
                            return 5;     
                        case DEAD: 
                            return 6;                       
                        default:
                            break;
                    }
                    break;
            
                default:
                    break;
                case Dragon:
                    switch (enemy_state) {
                        case IDLE:
                        return 2;
                        case RUNBACK:
                        case RUNRIGHT:
                        case RUNLEFT:
                        case RUNFORWARD:
                        return 4;
                        default:
                            break;
                    }
            }
            return enemy_state;
        }
    }

    public static boolean isLogging = true;
    public static int stopBug = 1;
}
package cz.cvut.fel.pjv.entity;
import static cz.cvut.fel.pjv.utils.Constanz.EnemyConstans.*;


/**
 * The Enemy class represents an enemy entity in the game. It extends the Entity class and 
 * adds additional properties and behaviors specific to enemies, such as animation ticks, 
 * animation speed, enemy type, and enemy action.
 *
 * The Enemy class also handles the enemy's animation updates and setting the animation 
 * based on the enemy's movement.
 */

public class Enemy extends Entity{

    private int aniTick, aniIndex, aniSpeed = 20;
    public int enemy_type, enemy_action;

    /**
     * Constructs a new Enemy at the specified coordinates with the specified health, damage, and enemy type.
     * This constructor also initializes the enemy's hitbox.
     *
     * @param x the x-coordinate where the enemy will be placed
     * @param y the y-coordinate where the enemy will be placed
     * @param health the health of the enemy
     * @param damage the damage the enemy can inflict
     * @param enemy_type the type of the enemy
     */

    public Enemy(float x, float y, int health, int damage,int enemy_type) {
        super(x, y, health, damage);
        this.enemy_type = enemy_type;
        initHitbox(x, y, health, enemy_type);
    }
    
    /**
     * Updates the enemy's animation tick and sets the animation based on the enemy's movement.
     */

    public void update(){
        updateAnimationTick();
        setAnimation();
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemy_type, enemy_action)) { 
                aniIndex = 0;
            }
         }
    }

    private void setAnimation(){
        if (moving)
            enemy_action = RUNFORWARD;
        else
            enemy_action = IDLE;
        
    }

    public int getAniIndex(){
        return aniIndex;
    }
    
}

    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Special attack for Mage class. Large fireball that deals AOE damage.
 * 
 * @author Evan Ma, Kenneth Jin
 * @version V1
 */
public class Fireball extends Projectiles
{
    
    private static final int SET_SPEED = 6;
    private static final int SET_DIRECTION = 1;
    private static final double SET_YVEL = 8;

    private int explosionRange;
    private int damage;

    private GreenfootImage[] fireBallSprites;

    private int animationIndex;

    /**
     * Main constructor for Fireball class.
     * @param explosionRange The size of the AOE of the fireball
     * @param damage The damage the explosion deals.
     */
    public Fireball(int explosionRange, int damage){
        super(SET_SPEED, SET_DIRECTION, SET_YVEL, false);
        this.explosionRange = explosionRange;
        this.damage = damage;
        animationIndex = 0;

        fireBallSprites = new GreenfootImage[5];
        for (int i = 0; i < fireBallSprites.length; i++)
        {
            GreenfootImage image = new GreenfootImage("FB00" + (i + 1) + ".png");
            image.scale((int) (image.getWidth() * 2), (int) (image.getHeight() * 2));
            fireBallSprites[i] = image;
        }
    
        setImage(fireBallSprites[0]);
    }

    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.

        super.act();
        if (!getRemoved())
        {
            checkHitEnemy();
        }

        animationIndex++;
        if (animationIndex == fireBallSprites.length)
        {
            animationIndex = 0;
        }
        setImage(fireBallSprites[animationIndex]);
    }
    
    private void checkHitEnemy(){
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if(enemy != null){
            if (enemy.getX()+5 <= this.getX()) {
                explode();
                removeProjectile();
            }
        }
    }
    
    private void explode(){
      List<Enemy> enemies = getObjectsInRange(explosionRange, Enemy.class);
      for(Enemy e: enemies){
          e.takeDamage(damage);// adjust to mage's damage
          //explode animations?
          MainWorld.increaseDamageDealt(damage);
      }
      getWorld().addObject(new Explosion(6), getX(), getY() - 40);
    }
}

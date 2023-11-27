import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Projectile fired by Dark Mage, pierces multiple enemies before disappearing.
 * 
 * @author Evan Ma 
 * @version V1
 */
public class ThunderBolt extends Projectiles
{
    private GreenfootSound [] small; // sounds for small fireball
    
    private static final int SET_SPEED = 12;
    private static final int SET_DIRECTION = 1;
    private static final double SET_YVEL = 8;
    
    private int damage;
    
    private GreenfootImage[] sprites;
    
    private int animationIndex;
    private int totalTargets = 1;
    private ArrayList<Enemy> hitTargets;
    
    private GreenfootSound sfx;

    /**
     * Main ThunderBolt constructor
     * @param damage The damage the thunder does.
     */
    public ThunderBolt(int damage){
        super(SET_SPEED, SET_DIRECTION, SET_YVEL, false);
        sfx = new GreenfootSound("thunder.mp3");
        sfx.setVolume(75);
        sfx.play();
        animationIndex = 0;
        
        sprites = new GreenfootImage[12];
        for (int i = 0; i < sprites.length; i++)
        {
            GreenfootImage image = new GreenfootImage("lightning" + (i + 1) + ".png");
            image.scale((int) (image.getWidth() * 2), (int) (image.getHeight() * 2));
            sprites[i] = image;
        }
        
        small = new GreenfootSound[3];
        for(int i = 0; i < small.length; i++){
            small[i] = new GreenfootSound("SmallFireBall.mp3");
        }
        this.damage = damage;
        
        hitTargets = new ArrayList<Enemy>();
    }
    
    /**
    * Act - do whatever the ThunderBolt wants to do. This method is called whenever
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
        if (animationIndex == sprites.length)
        {
            animationIndex = 0;
        }
        setImage(sprites[animationIndex]);
    }
    
    private void checkHitEnemy(){
        
        List<Enemy> enemies = getObjectsInRange(100, Enemy.class);
        if (enemies.size() != 0) {
            for(Enemy e: enemies){
                boolean dealDmg = true;
                
                for (Enemy hit : hitTargets)
                {
                    if (hit == e)
                    {
                        dealDmg = false;
                    }
                }
                if (dealDmg)
                {
                    hitTargets.add(e);
                    e.takeDamage(damage);
                    MainWorld.increaseDamageDealt(damage);
                }
                
                if (hitTargets.size() > totalTargets)
                {  
                    removeProjectile();
                    return;
                }
            }
        }
    }
}

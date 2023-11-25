import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectiles
{
    private GreenfootSound [] small; // sounds for small fireball
    private GreenfootSound [] big; //sounds for big fireball
    
    private static final int SET_SPEED = 4;
    private static final int SET_DIRECTION = 1;
    private static final double SET_YVEL = 8;
    
    private int explosionRange;
    private int damage;

    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Fireball(int explosionRange, int damage){
        super(SET_SPEED, SET_DIRECTION, SET_YVEL, true);
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        small = new GreenfootSound[3];
        for(int i = 0; i < small.length; i++){
            small[i] = new GreenfootSound("SmallFireBall.mp3");
        }
        big = new GreenfootSound[3];
        for(int i = 0; i < big.length; i++){
            big[i] = new GreenfootSound("BigFireBall.mp3");
        }
        
        this.explosionRange = explosionRange;
        this.damage = damage;
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
    }
    
    public void checkHitEnemy(){
      Actor enemy = getOneIntersectingObject(Enemy.class);
      if(enemy != null){
          explode();
          removeProjectile();
      }
    }
    
    public void explode(){
      List<Enemy> enemies = getObjectsInRange(explosionRange, Enemy.class);
      for(Enemy e: enemies){
          e.takeDamage(damage);// adjust to mage's damage
          //explode animations?
      }
    }
}

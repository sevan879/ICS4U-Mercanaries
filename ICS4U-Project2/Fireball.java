import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Fireball class, projectile for mage's attack
 * 
 * @author Kenneth Jin
 * @version November 2023
 */
public class Fireball extends Projectiles
{
    private GreenfootSound [] small; // sounds for small fireball
    private GreenfootSound [] big; //sounds for big fireball
    
    private static final int speed = 4;
    private static final int direction = 1;
    private static final int maxHeight = 200;
    private static final double yVelocity = 8;
    private static final int range = 30;


    public Fireball(){
        super(speed, direction, maxHeight, yVelocity);
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        small = new GreenfootSound[3];
        for(int i = 0; i < small.length; i++){
            small[i] = new GreenfootSound("SmallFireBall.mp3");
        }
        big = new GreenfootSound[3];
        for(int i = 0; i < big.length; i++){
            big[i] = new GreenfootSound("BigFireBall.mp3");
        }
    }
    
 
    
  /**
   * Act - do whatever the Fireball wants to do. This method is called whenever
   * the 'Act' or 'Run' button gets pressed in the environment.
   */
  public void act()
  {
      // Add your action code here.
      super.act();
        
  }
  
  /**
   * check if the fireball hits any enemies, explodes and remove itself from the world if it hits an enemy
   */
  public void checkHitEnemy(){
      Actor enemy = getOneIntersectingObject(Enemy.class);
      if(enemy != null){
          explode();
          getWorld().removeObject(this);
      }
  }
  
  /**
   * explode and deals damage to enemies in range
   */
  public void explode(){
      List<Enemy> enemies = getObjectsInRange(range, Enemy.class);
      for(Enemy e: enemies){
          e.takeDamage(3);// adjust to mage's damage
          //explode animations?
      }
  }
  
  
    
  
  

    
    
}

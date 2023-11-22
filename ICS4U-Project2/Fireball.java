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
  private static final int speed = 3;
  private static final int direction = 1;
  private static final int maxHeight = 200;
  private static final int range = 30;
  public Fireball(){
    super(speed, direction, maxHeight);
     
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
  
  public void checkHitEnemy(){
      Actor enemy = getOneIntersectingObject(Enemy.class);
      if(enemy != null){
          explode();
          getWorld().removeObject(this);
      }
  }
  
  public void explode(){
      List<Enemy> enemies = getObjectsInRange(range, Enemy.class);
      for(Enemy e: enemies){
          e.takeDamage(3);// adjust to mage's damage
          //explode animations?
      }
  }
  
  
    
  
  

    
    
}

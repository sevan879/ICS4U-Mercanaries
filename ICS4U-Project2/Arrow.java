import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectiles
{
    private static final int speed = 2;
    private static final int direction = -1;
    private static final int maxHeight = 200;
    public Arrow(){
        super(speed, direction, maxHeight);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
    }
    
    public void checkHitParty(){
      Actor enemy = getOneIntersectingObject(Enemy.class);
      if (enemy != null) {
          if (enemy instanceof Enemy) {
              ((Enemy) enemy).takeDamage(3);
              getWorld().removeObject(this);  // Remove the arrow after hitting an enemy
          }
      }
      
    }
    
    
}

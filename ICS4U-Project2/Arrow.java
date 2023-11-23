import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectiles
{
    private static final int speed = 6;
    private static final int direction = -1;
    private static final int maxHeight = 200;
    private static final double yVelocity = 6;
    public Arrow(){
        super(speed, direction, maxHeight, yVelocity);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        super.act();
        checkHitParty();
    }
    
    /*
    public void checkHitParty(){
      Actor party = getOneIntersectingObject(Party.class);
      if (party != null) {
          //if (party instanceof Party) {
              System.out.println("hit");
              ((Party) party).takeDamage(3);
              getWorld().removeObject(this);  // Remove the arrow after hitting an enemy
          //}
      }
      
    }
    */
    
    public void checkHitParty(){
    {
        if(isTouching(Party.class))
        {
            Actor party = getOneIntersectingObject(Party.class);
            ((Party)party).takeDamage(3);
            MyWorld world = (MyWorld) getWorld();
            world.removeObject(this);
            
        }
    }
    }
    
    
}

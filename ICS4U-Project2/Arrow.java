import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectiles
{
    private GreenfootSound[] a;// sound effect for arrow release and whoosh noise
    
    private static final int speed = 6;
    private static final int direction = -1;
    private static final int maxHeight = 200;
    private static final double yVelocity = 6;
    public Arrow(){
        super(speed, direction, maxHeight, yVelocity);

        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        a = new GreenfootSound[3];
        for(int i = 0; i < a.length; i++){
            a[i] = new GreenfootSound("Arrow.wav");
        }
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
            World world = getWorld();
            world.removeObject(this);
            
        }
    }
    }
    
    
}

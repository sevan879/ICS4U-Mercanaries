import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super class for all projectiles
 * 
 * @author Kenneth Jin 
 * @version November 2023
 */
public abstract class Projectiles extends Actor
{
    protected int speed;
    protected int direction; //-1 = left, 1 = right
    protected int maxHeight;
    protected double yVelocity;
    private static final double accleration = 0.2;
    
    /**
     * Main constructor for Projectiles class
     * 
     * @param spd projectile's speed
     * @param direction projectile's direction
     * @param yVel projectile's vertical velocity
     */
    public Projectiles(int spd, int direction, int maxH, double yVel){
        speed = spd;
        this.direction = direction;
        maxHeight = maxH;
        yVelocity = yVel;
        
        
    }
    
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        moveInParabola();
    }
    
    boolean reachedMaxHeight = false;
    /**
     * have the projectile move in an arc
     */
    public void moveInArc(){
      
      if(!reachedMaxHeight){
          setLocation(getX() + speed * direction, getY() - speed);
      }else{
          setLocation(getX() + speed * direction, getY() + speed);
      }
      
      if(getY() <= maxHeight){
          reachedMaxHeight = true;
      } 
    }
    
    
    /**
     * have the projectile move in a parabola
     */
    public void moveInParabola(){
        setLocation(getX() + speed * direction, getY() - (int)yVelocity);
        yVelocity = yVelocity - accleration;
    }
    
    
        
    
}

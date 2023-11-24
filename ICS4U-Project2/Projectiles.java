import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectiles extends Actor
{
    protected int speed;
    protected int direction; //-1 = left, 1 = right
    protected int maxHeight;
    protected double yVelocity;
    private static final double accleration = 0.2;
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Projectiles(int spd, int direction, int maxH, double yVel){
        speed = spd;
        this.direction = direction;
        maxHeight = maxH;
        yVelocity = yVel;
        
        
    }
    
    public void act()
    {
        // Add your action code here.
        moveInParabola();
    }
    
    boolean reachedMaxHeight = false;
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
    
    
    
    public void moveInParabola(){
        setLocation(getX() + speed * direction, getY() - (int)yVelocity);
        yVelocity = yVelocity - accleration;
    }
    
    
        
    
}

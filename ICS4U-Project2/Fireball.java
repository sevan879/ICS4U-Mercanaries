import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectiles
{
    private static final int speed = 2;
    private static final int direction = 1;
    private static final int maxHeight = 150;
    private static  int delay = 50;
    public Fireball(){
        super(speed, direction);
        
    }
    
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        moveInArc();
        
    }
    
    /*
    private void moveInArc() {
        //int startX = getX();
        //int startY = getY();
        int initialSpeed= speed;
        double radianAngle = Math.toRadians(45);
        int distanceMoved = 0;
        int x;
        int y;
        
        while(initialSpeed > 0){
            x = (int) (getX() + initialSpeed * Math.cos(radianAngle));
            y = (int) (getY() - initialSpeed * Math.sin(radianAngle));
            setLocation(x, y);
            initialSpeed = initialSpeed - 1;
        }
        x = (int) (getX() + initialSpeed * Math.cos(radianAngle));
        y = (int) (getY() + initialSpeed * Math.sin(radianAngle));
        setLocation(x, y);
        
    }
    
   
   private void moveInArc(){
       int velocityUp = speed;
       if(velocityUp > 0){
           setLocation(getX() + speed, getY() - speed/2);
           velocityUp--;
       }else{
           setLocation(getX() + speed, getY() - speed/2);
       }
   }
   
   
  
  private void moveInArc(){
      int actualSpeed = speed;
      if(getY() > maxHeight){
          
          setLocation(getX() + speed, getY() - actualSpeed/2);
      }else{
          actualSpeed = actualSpeed * -1;
          while(getY() < 330){
              if(delay == 0){
                  setLocation(getX() + speed, getY() - actualSpeed/2);
                  delay = 50;
              }else{
                  delay--;
              }
              
              if(getY() >= 330){
                  break;
              }
          }
          
          
      }
  }
  */
  
  boolean reachedMaxHeight = false;
  private void moveInArc(){
      
      if(!reachedMaxHeight){
          setLocation(getX() + speed, getY() - speed);
      }else{
          setLocation(getX() + speed, getY() + speed);
      }
      
      if(getY() <= maxHeight){
          reachedMaxHeight = true;

      }
      
      
  }
  

    
    
}

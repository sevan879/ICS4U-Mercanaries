import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectiles
{
    private static final int speed = 10;
    private static final int direction = 1;
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
    
    private void moveInArc() {
        int startX = getX();
        int startY = getY();
        int initialSpeed = speed;
        double radianAngle = Math.toRadians(45);
        int distanceMoved = 0;
        while(distanceMoved <= 30){
            System.out.println("movedup");
            int x = (int) (startX + initialSpeed * Math.cos(radianAngle));
            int y = (int) (startY - initialSpeed * Math.sin(radianAngle));
            setLocation(x, y);
            distanceMoved += initialSpeed;
        }
        int x = (int) (startX + initialSpeed * Math.cos(radianAngle));
        int y = (int) (startY + initialSpeed * Math.sin(radianAngle));
        setLocation(x, y);

        initialSpeed -= 1;
    }

    
    
}

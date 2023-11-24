import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectiles extends Actor
{
    //Settings
    protected int speed;
    protected int direction; //-1 = left, 1 = right
    protected int maxHeight;
    protected double yVelocity;
    private static final double accleration = 0.2;
    
    private boolean removed;
    private boolean hasGravity;
    
    private boolean reachedMaxHeight = false;
    
    
    
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Projectiles(int spd, int direction, int maxH, double yVel, boolean gravityProj){
        speed = spd;
        this.direction = direction;
        maxHeight = maxH;
        yVelocity = yVel;
        hasGravity = gravityProj;
        removed = false;
    }
    
    public void act()
    {
        // Add your action code here.
        if (hasGravity)
        {
            moveInParabola();
        }
        else
        {
            moveInLine();
        }
        if (!removed)
        {
            checkOutOfWorld();
        }
    }
    
    public void moveInParabola(){
        setLocation(getX() + speed * direction, getY() - (int)yVelocity);
        yVelocity = yVelocity - accleration;
    }
    
    public void moveInLine()
    {
        setLocation(getX() + speed * direction, getY());
    }
    
    private void checkOutOfWorld()
    {
        World world = getWorld();
        if (getX() <= 5 || getX() >= world.getWidth() -5 || getY() <= 5 || getY() >= world.getHeight() - 5)
        {
            removeProjectile();
        }
    }
    
    protected void removeProjectile()
    {
        removed = true;
        getWorld().removeObject(this);
    }
    
    protected boolean getRemoved()
    {
        return removed;
    }
}

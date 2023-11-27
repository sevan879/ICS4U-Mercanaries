import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract superclass for all moving projectiles. Accomodates for parabolic motion and linear motion.
 * 
 * @author Kenneth Jin
 * @version V1
 */
public abstract class Projectiles extends SuperSmoothMover
{
    //Settings
    protected double speed;
    protected int direction; //-1 = left, 1 = right
    protected double yVelocity;
    protected static double acceleration = -0.2;
    
    private double range;
    
    private boolean removed;
    private boolean hasGravity;
    
    
    /**
     * Main constructor for Projectiles Class
     * @param spd Speed of projectile
     * @param direction Direction of projectile. -1 = left. 1 = right
     * @param yVel Initial yVelocity of projectile. Only matters for arcing projectiles.
     * @param gravityProj Set to true if projectile should have gravity applied.
     */
    public Projectiles(double spd, int direction, double yVel, boolean gravityProj){
        speed = spd;
        this.direction = direction;
        yVelocity = yVel;
        hasGravity = gravityProj;
        removed = false;
        if (hasGravity)
        {
             range = speed * ((-2*yVelocity)/(acceleration));
        }
        
        
    }
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
    /**
     * Set the speed of the projectile
     * @param spd Double representing the speed to set the object to.
     */
    public void setSpeed(double spd)
    {
        speed = spd;
    }
    /**
     * Set the vertical velocity of the projectile
     * @param yVel Double representing the Y Velocity to set the object to.
     */
    public void setYVelocity(double yVel)
    {
        yVelocity = yVel;
    }
    
    private void moveInParabola(){
        setLocation(getX() + speed * (double)direction, getY() - (int)yVelocity);
        yVelocity = yVelocity + acceleration;
    }
    
    private void moveInLine()
    {
        setLocation(getX() + speed * (double)direction, getY());
    }
    
    private void checkOutOfWorld()
    {
        World world = getWorld();
        if (getX() <= 5 || getX() >= world.getWidth() -5 || getY() <= 5 || getY() >= world.getHeight() - 5)
        {
            removeProjectile();
        }
    }
    /**
     * Remove projectile once it leaves the world
     */
    protected void removeProjectile()
    {
        removed = true;
        getWorld().removeObject(this);
    }
    /**
     * Return true if Projectile has been removed
     * @return boolean
     */
    protected boolean getRemoved()
    {
        return removed;
    }
}

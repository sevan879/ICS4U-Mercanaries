import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
    
    public void setSpeed(double spd)
    {
        speed = spd;
    }
    
    public void setYVelocity(double yVel)
    {
        yVelocity = yVel;
    }
    
    public void moveInParabola(){
        setLocation(getX() + speed * (double)direction, getY() - (int)yVelocity);
        yVelocity = yVelocity + acceleration;
    }
    
    public void moveInLine()
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

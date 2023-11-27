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
    
    private static final double SET_SPEED = 4;
    private static final int SET_DIRECTION = -1;
    private static final double SET_YVEL = 6;
    private static final int SET_DAMAGE = 5;
    
    private int damage;
    
    private GreenfootImage arrowUpImage;
    private GreenfootImage arrowDownImage;
    private GreenfootImage arrowFlatImage;
    private GreenfootImage arrowInclineImage;
    private GreenfootImage arrowDeclineImage;
    
    public Arrow(double speed, int direction, double yVel, int damage){
        super(speed, direction, yVel, true);
        this.damage = damage;
        
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        a = new GreenfootSound[3];
        for(int i = 0; i < a.length; i++){
            a[i] = new GreenfootSound("Arrow.wav");
        }
        
        arrowUpImage = new GreenfootImage("ArrowUp.png");
        arrowUpImage.scale((int) (arrowUpImage.getWidth() * 1.3), (int) (arrowUpImage.getHeight() * 1.3));
        arrowDownImage = new GreenfootImage("ArrowDown.png");
        arrowDownImage.scale((int) (arrowUpImage.getWidth() * 1.1), (int) (arrowDownImage.getHeight() * 1.1));
        arrowFlatImage = new GreenfootImage("ArrowFlat.png");
        arrowFlatImage.scale((int) (arrowFlatImage.getWidth() * 1.3), (int) (arrowFlatImage.getHeight() * 1.3));
        arrowInclineImage = new GreenfootImage("ArrowIncline.png");
        arrowInclineImage.scale((int) (arrowInclineImage.getWidth() * 1.3), (int) (arrowInclineImage.getHeight() * 1.3));
        arrowDeclineImage = new GreenfootImage("ArrowDecline.png");
        arrowDeclineImage.scale((int) (arrowDeclineImage.getWidth() * 1.3), (int) (arrowDeclineImage.getHeight() * 1.3));
        if (direction == 1)
        {
            arrowUpImage.mirrorHorizontally();
            arrowDownImage.mirrorHorizontally();
            arrowFlatImage.mirrorHorizontally();
            arrowInclineImage.mirrorHorizontally();
        }
        //arrowImage.rotate(20);
        setImage(arrowUpImage);
    }
    
    public Arrow(int aimedDistance, int damage){
        this (SET_SPEED, SET_DIRECTION, SET_YVEL, damage);
        double baseSpeedUnit = (aimedDistance * acceleration)/(-48);
        setSpeed(2*baseSpeedUnit);
        setYVelocity(6*baseSpeedUnit);
        
    }
    
    public Arrow(){
        this (SET_SPEED, SET_DIRECTION, SET_YVEL, SET_DAMAGE);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        
        super.act();
        if (!getRemoved())
        {
            checkHitParty();
        }
        if (yVelocity > 2.5)
        {
            setImage(arrowUpImage);
        }
        else if (yVelocity > 0.6)
        {
            setImage(arrowInclineImage);
        }
        else if (yVelocity > -0.6)
        {
            setImage(arrowFlatImage);
        }
        else if (yVelocity > -2.5)
        {
            setImage(arrowDeclineImage);
        }
        else
        {
            setImage(arrowDownImage);
        }
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
            ((Party)party).takeDamage(damage);
            MainWorld.increaseDamageTaken(damage);
            
            removeProjectile();
            
        }
    }
    }
    
    
}

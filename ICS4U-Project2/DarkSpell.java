import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class DarkSpell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DarkSpell extends Projectiles
{
    private GreenfootSound[] a;// sound effect for arrow release and whoosh noise
    
    private static final double SET_SPEED = 4;
    private static final int SET_DIRECTION = 1;
    private static final double SET_YVEL = 6;
    private static final int SET_DAMAGE = 5;
    
    private int explosionRange = 300;
    
    private int damage;
    
    private GreenfootImage[] images;
    private int animationIndex;
    private int animationDelay;
    private int animationCounter;
    /**
     * Main Constructor for DarkSpell. Includes speed, direction, YVelocity, and Damage.
     * @param speed Speed of arrow
     * @param direction Direction of arrow
     * @param yVel Initial vertical velocity of the arrow
     * @param damage Damage the arrow deals
     */    
    public DarkSpell(double speed, int direction, double yVel, int damage){
        super(speed, direction, yVel, true);
        this.damage = damage;
        
        animationDelay = 2;
        animationIndex = 0;
        
        // note to make sure when implementing the sound to check if it reaches index out of bounds for counter
        a = new GreenfootSound[3];
        for(int i = 0; i < a.length; i++){
            //a[i] = new GreenfootSound(".wav");
        }
        
        images = new GreenfootImage[30];
        for (int i = 0; i < images.length; i++)
        {
            GreenfootImage frame = new GreenfootImage("darkSpell (" + (1 + i) + ").png");
            frame.scale((int)(frame.getWidth()*3), (int)(frame.getHeight()*3));;
            images[i] = frame;
        }
    }
    /**
     * Unique constructor that uses Kinematic formulas to aim the Spell at a specified distance away.
     * @param aimedDistance Horozontal distance the arrow should travel.
     * @param damage The damage of the spell
     */
    public DarkSpell(int aimedDistance, int damage){
        this (SET_SPEED, SET_DIRECTION, SET_YVEL, damage);
        double baseSpeedUnit = (aimedDistance * acceleration)/(-48);
        setSpeed(2*baseSpeedUnit);
        setYVelocity(6*baseSpeedUnit);
        
    }
    
    /**
     * Act - do whatever the DarkSpell wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        
        super.act();
        if (!getRemoved())
        {
            checkHitEnemy();
        }
        
        animationIndex ++;
        if (animationIndex == images.length)
        {
            animationIndex = 0;
        }
        else
        {
            setImage(images[animationIndex]);
    
        }
    }
    
    private void checkHitEnemy(){
        List<Enemy> eList = getObjectsInRange(150, Enemy.class); 
        if(eList.size() != 0){
            explode();
            removeProjectile();
        }
    }

    private void explode(){
        List<Enemy> enemies = getObjectsInRange(explosionRange, Enemy.class);
        if (enemies.size() != 0) {
            for(Enemy e: enemies){
                e.takeDamage(damage);// adjust to mage's damage
                //explode animations?
                MainWorld.increaseDamageDealt(damage);
            }
            getWorld().addObject(new DarkBurst(4), getX(), getY() - 40);
        }
    }
    
    
}

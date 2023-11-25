import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class SmallFireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmallFireball extends Projectiles
{
    private GreenfootSound [] small; // sounds for small fireball
    
    private static final int SET_SPEED = 8;
    private static final int SET_DIRECTION = 1;
    private static final double SET_YVEL = 8;
    
    private int explosionRange;
    private int damage;
    
    private GreenfootImage[] fireBallSprites;
    
    private int animationIndex;

    /**
     * Act - do whatever the SmallFireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SmallFireball(int explosionRange, int damage){
        super(SET_SPEED, SET_DIRECTION, SET_YVEL, false);
        
        animationIndex = 0;
        
        fireBallSprites = new GreenfootImage[5];
        for (int i = 0; i < fireBallSprites.length; i++)
        {
            GreenfootImage image = new GreenfootImage("FB00" + (i + 1) + ".png");
            image.scale((int) (image.getWidth() * 1), (int) (image.getHeight() * 1));
            fireBallSprites[i] = image;
        }
        
        small = new GreenfootSound[3];
        for(int i = 0; i < small.length; i++){
            small[i] = new GreenfootSound("SmallFireBall.mp3");
        }
        
        this.explosionRange = explosionRange;
        this.damage = damage;
    }
    
    /**
    * Act - do whatever the SmallFireball wants to do. This method is called whenever
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
        
        animationIndex++;
        if (animationIndex == fireBallSprites.length)
        {
            animationIndex = 0;
        }
        setImage(fireBallSprites[animationIndex]);
    }
    
    public void checkHitEnemy(){
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if(enemy != null){
          enemy.takeDamage(damage);
          removeProjectile();
        }
    }
}

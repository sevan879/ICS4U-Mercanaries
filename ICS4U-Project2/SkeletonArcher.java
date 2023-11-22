import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.concurrent.Phaser;
import java.util.List;

/**
 * Write a description of class Archer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkeletonArcher extends Enemy
{
    private static final int HP = 2;
    private static final double SPEED = 3;
    private static final int DELAY = 25;
    private static final int DAMAGE = 3;
    private static final int ATTACK_RANGE = 200;

    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private static final int RELOAD_TIME = 120; 
    private int reloadTimer = 0;
    /**
     * Act - do whatever the Archer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {

        move();
        action(targetPastTanks());

    }

    public SkeletonArcher(){
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);

    }

    protected void action(Party targetPlayer){
        targetPlayer.takeDamage(DAMAGE + Greenfoot.getRandomNumber(1));
        //attack animations
    }

    public Party targetPastTanks(){
        List<Party> players = getObjectsInRange(attackRange, Party.class);

        if (!players.isEmpty()) {
            if(targetPlayer() instanceof Knight){
                players.remove(0);
            }
            return players.get(0); 
        }

        return null;

    }

    public void running() {

    }

    public void attackAnimation() {

    }

    public void death() {
        isDying = true;
        setLocation(getX(), getY() + 20);
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                deathAnimationIndex = 0;
                getWorld().removeObject(this);
            }
            // Apply new image to this Actor
            setImage (deathPics[deathAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
    }
}

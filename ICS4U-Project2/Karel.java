import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Karel enemy. Simple melee enemy summoned by Cohen.
 * 
 * @author Arthur Tian
 * @version V1
 */
public class Karel extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 4;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 75;
    private static boolean movable = true;

    //Animation   
    private GreenfootImage[] deathPics;
    private int deathAnimationIndex;
    private int deathAnimationDelay;
    private int deathAnimationCounter;

    private GreenfootImage[] runningPics;
    private int runningAnimationIndex;
    private int runningAnimationDelay;
    private int runningAnimationCounter;

    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] attackThreePics;
    private int attackThreeAnimationIndex;
    private int attackThreeAnimationDelay;
    private int attackThreeAnimationCounter;
    private GreenfootImage karel;

    /**
     * Main constructor for Karel
     */
    public Karel() {
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
    }

    //act method
    public void act()
    {
        super.act();
    }

    protected void action(Party targetPlayer){
        if(playersUpClose() != null){
            for(Party p: playersUpClose()){
                p.takeDamage(DAMAGE);
            }
        }

        if(playersFurtherAway() != null){
            for(Party p: playersFurtherAway()){
                p.takeDamage(DAMAGE - 1);
            }
        }
    }
    
    public void attackAnimation() {
    }

    //ANIMATION
    public void running() {
    }

    public void death() {
                isDying = true;
        boolean remove = false;
        if (deathAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            deathAnimationCounter = deathAnimationDelay; // reset counter to max 
            deathAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (deathAnimationIndex == deathPics.length){
                remove = true;
                deathAnimationIndex = 0;
            }
            if (deathAnimationIndex > 8) {
                setLocation(getX(), getY()+25);
            }
            // Apply new image to this Actor
            setImage (deathPics[deathAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            deathAnimationCounter--;
        }
        if (remove) {
            getWorld().removeObject(this);
        }
    }

    public void animationConstructor() {
        karel = new GreenfootImage("Karel.png");
        karel.scale(100, 100);
        setImage(karel);

        deathPics = new GreenfootImage[10];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("KarelD" + (i+1) + ".png");
            if (i < 8) {
                deathPics[i].scale(100, 100);
            }
            else {
                deathPics[i].scale(100, 34);
            }
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;
    }
}

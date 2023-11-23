import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniCohen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniCohen extends Enemy
{
    private static final int HP = 5;
    private static final double SPEED = 4;
    private static final int DELAY = 10;
    private static final int DAMAGE = 2;
    private static final int ATTACK_RANGE = 95;
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
    //constructor
    public MiniCohen() {
        super(HP, SPEED, DELAY, DAMAGE, true, ATTACK_RANGE);
        setImage(runningPics[1]);
    }

    //act method
    public void act()
    {
        if (Greenfoot.isKeyDown("w")) {
            running();
            setLocation(getX()-2, getY());
        }
        //super.act();
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
        attackOne();
    }

    //ANIMATION
    public void running() {
        if (runningAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            runningAnimationCounter = runningAnimationDelay; // reset counter to max 
            runningAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (runningAnimationIndex == runningPics.length){
                runningAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (runningPics[runningAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            runningAnimationCounter--;
        }
    }

    public void attackOne() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackOneAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackOneAnimationCounter = attackOneAnimationDelay; // reset counter to max 
            attackOneAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackOneAnimationIndex == attackOnePics.length){
                animationTracker++;
                attackOneAnimationIndex = 0;
            }
            if (attackOneAnimationIndex == 3) {
                setLocation(getX() - 20, getY()-9);
            }
            if (attackOneAnimationIndex == 4) {
                setLocation(getX() + 20, getY()+9);
            }
            // Apply new image to this Actor
            setImage (attackOnePics[attackOneAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }

    public void death() {
    }

    public void animationConstructor() {
        animationTracker = 0;
        attackTracker = 0;
        //death
        deathPics = new GreenfootImage[4];
        for (int i = 0; i < deathPics.length; i++) {
            deathPics[i] = new GreenfootImage("SWD" + (i+1) + ".png");
            deathPics[i].scale(deathPics[i].getWidth()*2, deathPics[i].getHeight()*2);
        }
        deathAnimationIndex = 0;
        deathAnimationDelay = 10;
        deathAnimationCounter = deathAnimationDelay;

        //running
        runningPics = new GreenfootImage[6];
        for (int i = 0; i < runningPics.length; i++) {
            runningPics[i] = new GreenfootImage("TCR" + (i+1) + ".png");
            runningPics[i].scale(runningPics[i].getWidth()*2, runningPics[i].getHeight()*2);
        }
        runningAnimationIndex = 0;
        runningAnimationDelay = 8;
        runningAnimationCounter = runningAnimationDelay;

        attackOnePics = new GreenfootImage[5];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("SWA1" + (i+1) + ".png");
            attackOnePics[i].scale(attackOnePics[i].getWidth()*2, attackOnePics[i].getHeight()*2);
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 8;
        attackOneAnimationCounter = attackOneAnimationDelay;
    }
}

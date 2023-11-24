import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cohen here.
 * Performs actions for 10 seconds, then break for 5 seconds, thats when party members deal damage
 * ultimate attack at 20 seconds, 40 seconds...
 * 
 * 
 * @Arthur
 * @version (a version number or a date)
 */
public class Cohen extends Boss
{
    private static final int SET_HP = 5;
    private static final int ACTION_DELAY = 120;

    //ultimate attack
    private GreenfootImage[] attackOnePics;
    private int attackOneAnimationIndex;
    private int attackOneAnimationDelay;
    private int attackOneAnimationCounter;

    private GreenfootImage[] attackTwoPics;
    private int attackTwoAnimationIndex;
    private int attackTwoAnimationDelay;
    private int attackTwoAnimationCounter;

    private GreenfootImage[] summonPics;
    private int summonAnimationIndex;
    private int summonAnimationDelay;
    private int summonAnimationCounter;

    private GreenfootImage[] idlePics;
    private int idleAnimationIndex;
    private int idleAnimationDelay;
    private int idleAnimationCounter;

    private GreenfootImage smileCohen;
    private GreenfootImage angryCohen;
    public Cohen()
    {
        super(SET_HP, ACTION_DELAY);
    }

    public void act()
    {
        super.act();
        if (Greenfoot.isKeyDown("w")) {
            attackOne();
        }
        if (Greenfoot.isKeyDown("a")) {
            attackTwo();
        }
        if (Greenfoot.isKeyDown("s")) {
            summon();
        }
    }

    public void action(int attackNum)
    /**
     * POSSIBLE ACTIONS
     * summons wave 
     *  - karel the dog
     *  - minicohen
     * attack one party member
     *  - 
     * ultimate attack (say voicelines)
     *  - laser eyes
     *  - laser beam from mouth
     * 
     * 
     */
    {
        if (attackNum == 0) { //single attack
            //do nithing
            System.out.println("0");
        }
        if (attackNum == 1) //summon
        {
            summon();  
            System.out.println("1");
        }
        else if (attackNum == 2)
        {
            attackOne();
            System.out.println("2");
        }
        else if (attackNum == 3)
        {
            attackTwo();
            System.out.println("3");
        }
    }

    private void singleTarget()
    {

    }

    public void death() {

    }

    public void summon() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (summonAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            summonAnimationCounter = summonAnimationDelay; // reset counter to max 
            summonAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (summonAnimationIndex == 2){
                if (Greenfoot.getRandomNumber(2) == 0) {
                    getWorld().addObject(new MiniCohen(), 900, 700);
                }
                else {
                    getWorld().addObject(new MiniCohen(), 900, 700);
                }
            }
            if (summonAnimationIndex == summonPics.length){
                summonAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (summonPics[summonAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            summonAnimationCounter--;
        }
    }

    public void idle() {
        if (idleAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            idleAnimationCounter = idleAnimationDelay; // reset counter to max 
            idleAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, go back to first image
            if (idleAnimationIndex == idlePics.length){
                idleAnimationIndex = 0;
            }
            // Apply new image to this Actor
            setImage (idlePics[idleAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            idleAnimationCounter--;
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
                attackOneAnimationIndex = 0;
                animationTracker++;
            }
            else {
                // Apply new image to this Actor
                setImage (attackOnePics[attackOneAnimationIndex]);
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }

    public void attackTwo() {
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (attackTwoAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackTwoAnimationCounter = attackTwoAnimationDelay; // reset counter to max 
            attackTwoAnimationIndex++; // this will be used to set the image to the next frame
            if (attackTwoAnimationIndex == 5) {
                if (partyMembersInWorld().size() != 0) {
                    int n = getWorld().getObjects(Party.class).size();
                    int random = Greenfoot.getRandomNumber(n);
                    getWorld().removeObject(partyMembersInWorld().get(random));
                }
            }
            // If the image index has passed the last image, stop animation
            if (attackTwoAnimationIndex == attackTwoPics.length){
                attackTwoAnimationIndex = 0;
                animationTracker++;
            }
            // Apply new image to this Actor
            setImage (attackTwoPics[attackTwoAnimationIndex]);
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }

    public void animationConstructor() {
        animationTracker = 0;

        smileCohen = new GreenfootImage("SmilingCohen-removebg-preview.png");
        smileCohen.scale(smileCohen.getWidth()*2, smileCohen.getHeight()*2);
        setImage(smileCohen);

        idlePics = new GreenfootImage[8];
        for (int i = 0; i < idlePics.length; i++) {
            idlePics[i] = new GreenfootImage("CohenIdle" + (i+1) + "-removebg-preview.png");
        }
        idleAnimationIndex = 0;
        idleAnimationDelay = 10;
        idleAnimationCounter = idleAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[6];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("AngryCohenLaser" + (i+1) + "-removebg-preview.png");
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 11;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[6];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("AngryCohenLaser" + (i+1) + "-removebg-preview.png");
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 11;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[12];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("SmileCohenLaser" + (i+1) + "-removebg-preview.png");
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 11;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        summonPics = new GreenfootImage[3];
        for (int i = 0; i < summonPics.length; i++) {
            summonPics[i] = new GreenfootImage("CohenSummon" + (i+1) + ".png");
            summonPics[i].scale(summonPics[i].getWidth()*2, summonPics[i].getHeight()*2);
        }
        summonAnimationIndex = 0;
        summonAnimationDelay = 15;
        summonAnimationCounter = summonAnimationDelay;
    }
}

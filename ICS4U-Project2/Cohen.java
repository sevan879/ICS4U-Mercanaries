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
    private static final int SET_HP = 450;
    private static final int ACTION_DELAY = 200;
    private static final int ATTACK_COUNT = 8;
    private static final int downTimeDelay = 240;
    private int downTimeCounter;

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

    private GreenfootImage[] hitPics;
    private int hitAnimationIndex;
    private int hitAnimationDelay;
    private int hitAnimationCounter;

    private GreenfootImage smileCohen;
    private GreenfootImage angryCohen;
    private boolean fallen;
    
    private GreenfootSound coffee;
    private GreenfootSound vendingMachine;
    private GreenfootSound no;
    private GreenfootSound modular;
    private GreenfootSound modular2;

    private int introCount;
    private static final int introTotalWait = 180;
    
    public Cohen()
    {
        super(SET_HP, ACTION_DELAY, ATTACK_COUNT);
        setImage(idlePics[0]);
        downTimeCounter = 0;
        introCount = 0;
        fallen = false;
        coffee = new GreenfootSound("coffee.mp3");
        vendingMachine = new GreenfootSound("vendingMachine.mp3");
        no = new GreenfootSound("no.mp3");
        no.setVolume(90);
        modular = new GreenfootSound("modular.mp3");
        modular2 = new GreenfootSound("modular2.mp3");
    }

    public void act()
    {
        if (introCount < introTotalWait) {
            GreenfootImage img = new GreenfootImage("SecretCohen-removebg-preview.png");
            img.scale(img.getWidth()*2, img.getHeight()*2);
            setImage(new GreenfootImage(img));
            introCount++;
        }
        else { //actual act method
            //knock down
            if (numOfActionsSoFar%5 == 0 && numOfActionsSoFar != 0) {
                if (downTimeCounter == 0) {
                    cohenDownAndHit();
                    for (Enemy e : enemiesInWorld()) {
                        getWorld().removeObject(e);
                    }
                    for (Party member : partyMembersInWorld()) {
                        //member.setLocation(member.getCohenAttackXPos(), member.getY());
                        member.setCohenExists(true);
                    }
                    
                }
                //start loop
                if (fallen) {
                    if (downTimeCounter < downTimeDelay) {
                        if (downTimeCounter%2 == 0) {
                            setImage(new GreenfootImage("CohenDown4-removebg-preview.png"));
                        }
                        else {
                            setImage(new GreenfootImage("CohenDown5-removebg-preview.png"));
                            health--;
                        }
                        downTimeCounter++;
                    }
                    else { //reset values
                        for (Party member : partyMembersInWorld()) {
                            //member.setLocation(member.getCohenAttackXPos(), member.getY());
                            member.setCohenExists(false);
                        }

                        downTimeCounter = 0;
                        numOfActionsSoFar++;
                        fallen = false;
                    }
                }
            }
            else //actual ACTUAL act method lol
            {
                super.act();
            }
        }
    }

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
    public void action(int attackNum)
    {
        attacking = true;
        setLocation(463, 311);
        if (attackNum == 1) { //single attack
            attackTwo();
        }
        if (attackNum%2 == 0) //summon
        {
            summon();
        }
        else if (attackNum == 3 || attackNum == 5)
        {
            attackOne();
        }
        else if (attackNum == 7)
        {
            attackTwo();
        }
    }

    public void death() {
        getWorld().setBackground(new GreenfootImage("BackstoryBackground.png"));
        Greenfoot.setWorld(new EndScreen(false));
    }

    public void summon() {
        modular2.play();  
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        if (summonAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            summonAnimationCounter = summonAnimationDelay; // reset counter to max 
            summonAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (summonAnimationIndex == 2){
                if (Greenfoot.getRandomNumber(9)%3 != 0) {
                    getWorld().addObject(new MiniCohen(), 900, 605);
                }
                else {
                    getWorld().addObject(new Karel(), 900, 605);
                }
            }
            if (summonAnimationIndex == summonPics.length){
                summonAnimationIndex = 0;
                animationTracker++;
                numOfActionsSoFar++;
                attacking = false;
            }
            else {
                // Apply new image to this Actor
                setImage (summonPics[summonAnimationIndex]); 
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            summonAnimationCounter--;
        }
    }

    public void idle() {
        setLocation(593, 361);
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
        coffee.play();
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        else if (attackOneAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackOneAnimationCounter = attackOneAnimationDelay; // reset counter to max 
            attackOneAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (attackOneAnimationIndex == attackOnePics.length){
                attackOneAnimationIndex = 0;
                animationTracker++;
                numOfActionsSoFar++;
                attacking = false;
            }
            else {
                // Apply new image to this Actor
                setImage (attackOnePics[attackOneAnimationIndex]);
                for (Party member : partyMembersInWorld()) {
                    member.takeDamage(2);
                }
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackOneAnimationCounter--;
        }
    }

    public void attackTwo() {
        modular.play();
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        else if (attackTwoAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            attackTwoAnimationCounter = attackTwoAnimationDelay; // reset counter to max 
            attackTwoAnimationIndex++; // this will be used to set the image to the next frame
            if (attackTwoAnimationIndex == 5) {
                if (partyMembersInWorld().size() != 0) {
                    int n = getWorld().getObjects(Party.class).size();
                    int random = Greenfoot.getRandomNumber(n);
                    Party m = partyMembersInWorld().get(random);
                    if (Greenfoot.getRandomNumber(2) == 1) {
                        getWorld().removeObject(m);
                    }
                    else {
                        m.takeDamage(m.getMaxHP()/2);
                    }
                }
            }
            // If the image index has passed the last image, stop animation
            if (attackTwoAnimationIndex == attackTwoPics.length){
                attackTwoAnimationIndex = 0;
                numOfActionsSoFar++;
                animationTracker++;
                attacking = false;
            }
            else {
                // Apply new image to this Actor
                setImage (attackTwoPics[attackTwoAnimationIndex]);
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            attackTwoAnimationCounter--;
        }
    }

    public void cohenDownAndHit() {
        no.play();
        if (!animationIsRunning()) { //animationTracker is even, so we add one cuz we are starting animation
            animationTracker++;
        }
        else if (hitAnimationCounter == 0){ // counter reaches 0 means ready for next frame
            hitAnimationCounter = hitAnimationDelay; // reset counter to max 
            hitAnimationIndex++; // this will be used to set the image to the next frame

            // If the image index has passed the last image, stop animation
            if (hitAnimationIndex == hitPics.length){
                hitAnimationIndex = 0;
                animationTracker++;
                attacking = false;
                fallen = true;
            }
            else {
                setImage (hitPics[hitAnimationIndex]);
            }
        } else {// not ready to animate yet, still waiting
            // so just decrement the counter          
            hitAnimationCounter--;
        }
    }

    public void animationConstructor() {
        animationTracker = 0;

        smileCohen = new GreenfootImage("SmilingCohen-removebg-preview.png");
        smileCohen.scale(smileCohen.getWidth()*2, smileCohen.getHeight()*2);
        setImage(smileCohen);

        idlePics = new GreenfootImage[6];
        for (int i = 0; i < idlePics.length; i++) {
            idlePics[i] = new GreenfootImage("CohenIdle" + (i+3) + "-removebg-preview.png");
        }
        idleAnimationIndex = 0;
        idleAnimationDelay = 14;
        idleAnimationCounter = idleAnimationDelay;

        //attack one
        attackOnePics = new GreenfootImage[6];
        for (int i = 0; i < attackOnePics.length; i++) {
            attackOnePics[i] = new GreenfootImage("AngryCohenLaser" + (i+1) + "-removebg-preview.png");
        }
        attackOneAnimationIndex = 0;
        attackOneAnimationDelay = 8;
        attackOneAnimationCounter = attackOneAnimationDelay;

        //attack two
        attackTwoPics = new GreenfootImage[12];
        for (int i = 0; i < attackTwoPics.length; i++) {
            attackTwoPics[i] = new GreenfootImage("SmileCohenLaser" + (i+1) + "-removebg-preview.png");
        }
        attackTwoAnimationIndex = 0;
        attackTwoAnimationDelay = 4;
        attackTwoAnimationCounter = attackTwoAnimationDelay;

        summonPics = new GreenfootImage[3];
        for (int i = 0; i < summonPics.length; i++) {
            summonPics[i] = new GreenfootImage("CohenSummon" + (i+1) + ".png");
            summonPics[i].scale(summonPics[i].getWidth()*2, summonPics[i].getHeight()*2);
        }
        summonAnimationIndex = 0;
        summonAnimationDelay = 15;
        summonAnimationCounter = summonAnimationDelay;

        //attack two
        hitPics = new GreenfootImage[4];
        for (int i = 0; i < hitPics.length; i++) {
            hitPics[i] = new GreenfootImage("CohenDown" + (i+1) + "-removebg-preview.png");
        }
        hitAnimationIndex = 0;
        hitAnimationDelay = 16;
        hitAnimationCounter = hitAnimationDelay;
    }
}

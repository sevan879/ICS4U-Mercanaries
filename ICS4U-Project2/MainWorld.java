import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Main world where all the action takes place. This is the main world where the simulation occurs.
 * 
 * @author Evan Ma, Arthur Tian, Justin Wu
 * @version V1
 * <head>
 * <title> ICS4U Mercenaries </title>
 * </head>
 * 
 * <p> This game is about a  mysterious wizard who is exiled from their village and 
 * tries to take revenge by developing enemies to slowly kill off a village. 
 * The village head takes notice and send a group of mercenaries to kill this mysterious being. 
 * </p>
 *  
 * <h1>Assets:</h1>
 * <p>
 * https://craftpix.net/freebies/free-cartoon-forest-game-backgrounds/?num=1&count=85&sq=forest&pos=11
 * <br>https://craftpix.net/freebies/free-rpg-battleground-asset-pack/
 * <br>https://craftpix.net/freebies/free-villagers-sprite-sheets-pixel-art/?num=1&count=4&sq=villager&pos=0
 * <br>https://craftpix.net/freebies/free-werewolf-sprite-sheets-pixel-art/?num=1&count=13&sq=wolf&pos=2
 * <br>https://craftpix.net/freebies/free-werewolf-sprite-sheets-pixel-art/?num=1&count=13&sq=wolf&pos=2
 * <br>https://www.youtube.com/watch?v=AIS8KbfVIcE
 * <br>https://www.youtube.com/watch?v=k895zKWPEIg
 * <br>https://www.youtube.com/watch?v=V77xa-SDI-I
 * <br>https://elements.envato.com/large-fireball-special-effect-5TL4Z6Q
 * <br>https://elements.envato.com/heal-64KWBLR
 * <br>https://elements.envato.com/arrow-whoosh-bow-release-BXJTFKM
 * <br>https://www.youtube.com/watch?v=1rwAvUvvQzQ
 * <br>https://craftpix.net/freebies/free-skeleton-pixel-art-sprite-sheets/
 * <br>https://craftpix.net/freebies/free-knight-character-sprites-pixel-art/
 * <br>https://craftpix.net/freebies/free-wizard-sprite-sheets-pixel-art/
 * <br>https://xyezawr.itch.io/gif-free-pixel-effects-pack-12-mini-magick-shoots-3
 * <br>https://untiedgames.itch.io/super-pixel-effects-mini-pack-1?download
 * </p>
 * 
 * <title>Key Features </title>
 * <h1>MainWorld (gameplay loop)</h1>
 * <p>
 * //add
 * </p>
 * 
 * <h1>Backstory</h1>
 * <p>Pressing spacebar activates a new sentence spoken by either the villager or the knight about the situation at hand
 * Uses Mr. Cohen�s SuperTextBox</p>
 * 
 * <h1>Settings</h1>
 * <p>Controls the number of enemies and party members (party composition)
 * Controls the difficulty of the enemy waves</p>
 * 
 * <h1>Entities</h1>
 * <h2>Party</h2>
 * <p>
 * The mercenaries (good guys) that kill the enemies (bad guys)
 * All party members have attack animations, death animations, idle animations, and running animations
 * Mage, Dark Mage, and Healer consume mana. Knight does not.
 * </p>
 * <h3>PARTY TYPES</p3>
 * <h4>Knight</p4>
 * <p> Close quarters combat 
 * The tank of the party (most hp, attracts enemy attacks)</p>
 * <h4>Mage</h4>
 * <p>Attacks with small or big fireball</p>
 * <h4>Dark Mage</h4>
 * <p>Same as mage, but with different attacks (different projectiles)</p>
 * <h4>Healer</h4>
 * <p>Heals party members, can either heal just one member or all of them</p>
 * <h2>Enemy</h2>
 * <p>All enemies have attack animations, death animations, and running animations</p>
 * <h3>ENEMY TYPES</h3>
 * <h4>Karel</h4>
 * <p>Karel The Dog</p>
 * <p>Burns when it dies</p>
 * <h4>MiniCohen</h4>
 * <p>Some sort of squid monster</p>
 * <p>Custom made sprite</p>
 * <p>Standard enemy otherwise</p>
 * <h4>SkeletonArcher</h4>
 * <p>Long range enemy type</p>
 * <h4>SkeletonSpear</h4>
 * <p>Close range, but can deal piercing damage</p>
 * <h4>SkeletonWarrior</h4>
 * <p>Standard enemy</p>
 * <h4>Wolf</h4>
 * <p>Standard enemy </p>
 * <h2>Boss</h2>
 * <h3>Mr. Cohen</h3>
 * <p>Actions
 * <br>MouthLaser: shoots a laser from mouth than does AOE damage to all party members
 * <br>EyeLaser: shoots a deadly laser from eyes that kills of does 50% hp to one party member, chosen at random
 * <br>Summon: summon either karel or minicohen
 * <br>After a certain number of actions, Mr. Cohen goes down, this is when the party members attack and deal damage to him. 
 * <br>After Mr. Cohen dies, the simulation ends
 * <h2>Projectiles</h2>
 * <h3>Arrow</h3>
 * <p>Animations and sound for arrow flying through the sky using speed direction and velocity</p>
 * <h3>Fireball</h3>
 * <p>Explodes and gives off damage to enemies that get hit in range</p>
 * <h3>smallFireball</h3>
 * <p>Smaller version of fireball, but no splash damage is applied</p>
 * <h2>Effects</h2>
 * <h3>Explosion</h3>
 * <p> //
 * 
 * 
 * <h3>LoadingScreen</h3>
 * <p>Fade in and fade out effect
 * <br>Acts as a transition between worlds and background (main world levels)</p>
 * <h2>Background</h2>
 * <p>A giant image, endless scrolling (looping image)
 * <br>Background image is based on what level </p>
 * <h2>Button</h2>
 * <p>Each button has its own image and function, determined in constructor and act method</p>
 */
public class MainWorld extends World
{    
    private Background background;
    private int partyRunningSpeed;
    private boolean waveOver = false;
    private boolean waitForWaveToEnd;
    private int wave = 1;
    private int enemiesSpawned = 0;

    private int worldYLevel = 605;
    private int volume;

    //Spawning Party Variables
    private int numOfKnights;
    private int numOfMages;
    private int numOfHealers;
    private int numOfDarkMages;
    private int spacingBetween = 70;
    private int spawningXParty = 50;

    //Spawning Enemies Variables
    private int waveOneEnemies;
    private int waveTwoEnemies;
    private int waveThreeEnemies;
    private int spawningXEnemy;

    //Transition Variables
    private int transitionDelay = 300;
    private int transitionCounter = 0;
    //private int backgroundSwapCounter; // makes sure background only changes when fader is black
    private int fadeTime = 120;
    private int worldLvl;

    private int tempSpeed = 0;

    private GreenfootSound mainWorldMusic; //level 1 and 2 music
    private GreenfootSound bossMusic; //boss music

    /**
     * Constructor for MainWorld
     */
    public MainWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        spawningXEnemy = getWidth();
        background = new Background();

        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        volume = 0;
        mainWorldMusic = new GreenfootSound("RegWorld.mp3");
        mainWorldMusic.setVolume(volume);
        bossMusic = new GreenfootSound("BossMusic.mp3");
        bossMusic.setVolume(volume);
        addObject(background, 1086, 360); //add background first, so its behind everything
        started();

        waitForWaveToEnd = false;

        numOfKnights = Settings.getNumOfKnights();
        numOfHealers = Settings.getNumOfHealers();
        numOfMages = Settings.getNumOfMages();
        numOfDarkMages = Settings.getNumOfDarkMages();
        
        waveOneEnemies = Settings.getWaveOne();
        waveTwoEnemies = Settings.getWaveTwo();
        waveThreeEnemies = Settings.getWaveThree();
        
        spawnParty();
        //spawnEnemies();
    }

    /**
     * Act method.
     */
    public void act() {
        partyActions();
        spawnWaves();
        checkGameOver();
    }

    private void partyActions()
    {
        //actual stuff
        if (partyIsRunning()) {
            for (Party member : partyMembersInWorld()) { //set speed for scrolling background and scroll if party is running
                tempSpeed = member.getRunningSpeed();
            }
            background.scrollBackground(tempSpeed);
        }
        else { //at least one party member in combat!
            for (Party member : partyMembersInWorld()) { //get all party members in world
                if (enemiesInWorld().size() != 0) { //there are enemies in the world
                    member.setRunningSpeed(0); //make all party members stop running
                    if (!member.isIdle()) { //member is not idle (animation)
                        member.setIdle(); //make it idle if it is not in combat
                    }
                }
                else { // no enemies in the world
                    member.setRunningSpeed(member.getRunningSpeed());
                    member.setInCombat(false);
                }
            }
        }
    }

    //tells us if the party members are running or not, if one member stops running, all members stop also
    private boolean partyIsRunning() {
        boolean continueRunning = true; //true by default
        for (Party member : partyMembersInWorld()) {
            if (member.getRunningSpeed() == 0) { //speed of running is 0, there is an enemy, stop all party members from running
                continueRunning = false; //stop running!
            }
        }
        return continueRunning;
    }

    private ArrayList<Party> partyMembersInWorld() {
        ArrayList<Party> partyList = (ArrayList<Party>) (getObjects(Party.class));
        return partyList;
    }

    private ArrayList<Enemy> enemiesInWorld() {
        ArrayList<Enemy> enemyList = (ArrayList<Enemy>) (getObjects(Enemy.class));
        return enemyList;
    }

    private void spawnParty(){
        for (int i = 0; i < numOfHealers; i++)
        {
            addObject(new Healer(), spawningXParty += spacingBetween, worldYLevel);
        }
        for (int i = 0; i < numOfMages; i++)
        {
            addObject(new Mage(), spawningXParty += spacingBetween, worldYLevel);
        }
        for (int i = 0; i < numOfDarkMages; i++)
        {
            addObject(new DarkMage(), spawningXParty += spacingBetween, worldYLevel);
        }
        for (int i = 0; i < numOfKnights; i++)
        {
            addObject(new Knight(), spawningXParty += spacingBetween, worldYLevel);
        }
    }

    private void spawnWaves(){
        if (!waitForWaveToEnd)
        {
            if (waveOver)
            {
                if (transitionCounter <= 0)
                {
                    waveOver = false;
                }
                else
                {
                    transitionCounter--;
                    return;
                }
            }

            if(!waveOver || wave != 4){
                if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                    if (Greenfoot.getRandomNumber(20)%3 == 0) { // 
                        if (worldLvl == 1) {
                            addObject(new Wolf(), spawningXEnemy, worldYLevel);
                            enemiesSpawned++;
                        }
                        else if(worldLvl == 2){
                            int enemyType = Greenfoot.getRandomNumber(4);
                            if (enemyType == 0) {
                                addObject(new SkeletonSpear(), spawningXEnemy, worldYLevel); //modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 1) {
                                addObject(new SkeletonArcher(), spawningXEnemy, worldYLevel); // modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 2) {
                                addObject(new SkeletonWarrior(), spawningXEnemy, worldYLevel); // modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 3) {
                                if (Greenfoot.getRandomNumber(2) == 0)
                                {
                                    addObject(new BigSkeletonSpear(), spawningXEnemy, worldYLevel - 30); // modify placement after
                                    enemiesSpawned++;
                                }
                            }
                        } else if(worldLvl == 3){
                            int enemyType = Greenfoot.getRandomNumber(3);
                            if (enemyType == 0) {
                                addObject(new SkeletonSpear(), spawningXEnemy, worldYLevel); //modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 1) {
                                addObject(new SkeletonArcher(), spawningXEnemy, worldYLevel); // modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 2) {
                                addObject(new SkeletonWarrior(), spawningXEnemy, worldYLevel); // modify placement after
                                enemiesSpawned++;
                            }
                            else if (enemyType == 3) {
                                if (Greenfoot.getRandomNumber(2) == 0)
                                {
                                    addObject(new BigSkeletonSpear(), spawningXEnemy, worldYLevel - 30); // modify placement after
                                    enemiesSpawned++;
                                }
                            }
                            if (enemiesSpawned == waveThreeEnemies - 1) {
                                addObject(new MiniCohen(), spawningXEnemy, worldYLevel);
                                enemiesSpawned++;
                            }
                        }
                    }
                }
            }
        }
        if(wave == 1){
            if(enemiesSpawned >= waveOneEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay;
                    worldLvl = 2;

                    addObject(new LoadingScreen(false, 60, false, background, this, worldLvl), getWidth()/2,getHeight()/2);
                }
            }
        }else if(wave == 2){
            if(enemiesSpawned >= waveTwoEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    worldLvl = 3;
                    transitionCounter = transitionDelay; 
                    addObject(new LoadingScreen(false, 60, false, background, this, worldLvl), getWidth()/2,getHeight()/2);
                }                
            }
        }else if(wave == 3){
            if(enemiesSpawned >= waveThreeEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay; 
                    worldLvl = 4;
                    addObject(new LoadingScreen(false, 60, false, background, this, worldLvl), getWidth()/2,getHeight()/2);
                    addObject(new Cohen(), 463, 311);
                    stopped();
                    started();
                }
            }
        }
    }

    private boolean checkWaveOver()
    {
        ArrayList<Enemy> enemyList = (ArrayList<Enemy>) (getObjects(Enemy.class));
        if (enemyList.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void checkGameOver()
    {
        ArrayList<Party> memberList = (ArrayList<Party>) (getObjects(Party.class));
        if (memberList.isEmpty())
        {
            Greenfoot.setWorld(new EndScreen(true));
        }
    }
    /**
     * Gets the distance between two actors
     * @param a First Actor
     * @param b Second Actor
     * @return double
     */
    public static double getDistance (Actor a, Actor b)
    {
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
    }
    //to start playing the music when pressed run
    /**
     * Method that plays music when start button is clicked.
     */
    public void started(){
        if(worldLvl == 4){
            if (bossMusic.getVolume() < 70) {
                bossMusic.setVolume(volume);
                volume++;
            }
            bossMusic.playLoop();
        }
        else {
            if (mainWorldMusic.getVolume() < 70) {
                mainWorldMusic.setVolume(volume);
                volume++;
            }
            mainWorldMusic.playLoop();
        }
    }
    //to stop playing the music when pressed pause or reset
    /**
     * Method that stops music when end button is clicked.
     */
    public void stopped(){
        if (bossMusic.getVolume() > 0) {
            bossMusic.setVolume(volume);
            volume--;
        }
        if (mainWorldMusic.getVolume() >0) {
            mainWorldMusic.setVolume(volume);
            volume--;
        }
        if (mainWorldMusic.getVolume() <= 0) {
            mainWorldMusic.stop();
        }
        if (bossMusic.getVolume() <= 0) {
            bossMusic.stop();
        }
    }
    
    /**
     * Returns the worldYLevel of the given world
     * @return int
     */
    public int getWorldY()
    {
        return worldYLevel;
    }
}

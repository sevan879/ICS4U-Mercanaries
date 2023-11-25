import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    //Spawning Party Variables
    private int numOfKnights = 4;
    private int numOfMages = 0;
    private int numOfHealers = 1;
    private int spacingBetween = 60;
    private int spawningXParty = 50;

    //Spawning Enemies Variables
    private int waveOneEnemies = 5;
    private int waveTwoEnemies = 4;
    private int waveThreeEnemies = 2;
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

    //constructor
    public MainWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        spawningXEnemy = getWidth();
        background = new Background();

        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        wave = 0;

        addObject(background, 1086, 360); //add background first, so its behind everything

        waitForWaveToEnd = false;

        spawnParty();
        //spawnEnemies();

        mainWorldMusic = new GreenfootSound("RegWorld.mp3");
        mainWorldMusic.setVolume(44);
        bossMusic = new GreenfootSound("BossMusic.mp3");
        bossMusic.setVolume(44);
    }

    //act method
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
    public boolean partyIsRunning() {
        boolean continueRunning = true; //true by default
        for (Party member : partyMembersInWorld()) {
            if (member.getRunningSpeed() == 0) { //speed of running is 0, there is an enemy, stop all party members from running
                continueRunning = false; //stop running!
            }
        }
        return continueRunning;
    }

    public ArrayList<Party> partyMembersInWorld() {
        ArrayList<Party> partyList = (ArrayList<Party>) (getObjects(Party.class));
        return partyList;
    }

    public ArrayList<Enemy> enemiesInWorld() {
        ArrayList<Enemy> enemyList = (ArrayList<Enemy>) (getObjects(Enemy.class));
        return enemyList;
    }

    public void spawnParty(){
        for (int i = 0; i < numOfMages; i++)
        {
            addObject(new Mage(), spawningXParty += spacingBetween, worldYLevel);
        }
        for (int i = 0; i < numOfHealers; i++)
        {
            addObject(new Healer(), spawningXParty += spacingBetween, worldYLevel);
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
                    // stop background
                }
                else
                {
                    transitionCounter--;
                    return;
                }
            }

            if(!waveOver && wave != 4){
                if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                    int enemyType = Greenfoot.getRandomNumber(4);
                    if(enemyType == 0){
                        //addObject(new SkeletonSpear(), spawningXEnemy, worldYLevel); //modify placement after
                        //enemiesSpawned++;
                    } else if(enemyType == 1){
                        //addObject(new Archer(), 0, 0); // modify placement after
                        //enemiesSpawned++;
                    } else if(enemyType == 2){
                        addObject(new SkeletonWarrior(), spawningXEnemy, worldYLevel); // modify placement after
                        enemiesSpawned++;
                    } else if(enemyType == 3){
                        //addObject(new Flying(), 0, 0); //ylocation should be higher
                        //enemiesSpawned++;
                    }
                }
            }
        }

        if(wave == 1){
            if(enemiesSpawned == waveOneEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay; 
                    //background.setWorldBackground(1);
                    addObject(new Fader(fadeTime, 1, background), getWidth()/2, getHeight()/2);
                    worldLvl = 1;
                    stopped();
                    started();
                }
            }
        }else if(wave == 2){
            if(enemiesSpawned == waveTwoEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay; 
                    //background.setWorldBackground(2);
                    addObject(new Fader(fadeTime, 2, background), getWidth()/2, getHeight()/2);
                    background.setWorldBackground(1);
                    worldLvl = 2;
                    stopped();
                    started();
                }                
            }
        }else if(wave == 3){
            if(enemiesSpawned == waveThreeEnemies){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay; 
                    addObject(new Fader(fadeTime, -1, background), getWidth()/2, getHeight()/2);
                    background.setWorldBackground(2);
                    addObject(new Cohen(), 463, 311);
                    worldLvl = 3;
                    stopped();
                    started();
                }
            }
        }
    }

    private void partyRun()
    {
        ArrayList<Party> partyList = (ArrayList<Party>) (getObjects(Party.class));
        for (Party member : partyList)
        {
            // make them run
        }
    }

    private void partyIdle()
    {
        ArrayList<Party> partyList = (ArrayList<Party>) (getObjects(Party.class));
        for (Party member : partyList)
        {
            // make them idle
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

    public static double getDistance (Actor a, Actor b)
    {
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
    }
    //to start playing the music when pressed run
    public void started(){
        if(worldLvl == 1 || worldLvl == 2){
            mainWorldMusic.playLoop();
        }
        if(worldLvl == 3){
            bossMusic.playLoop();
        }
    }
    //to stop playing the music when pressed pause or reset
    public void stopped(){
        mainWorldMusic.stop();
        bossMusic.stop();
    }
}

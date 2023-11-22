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
    private Knight k;
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
    private int waveTwoEnemies = 10;
    private int waveThreeEnemies = 15;
    private int spawningXEnemy;
    
    //Transition Variables
    private int transitionDelay = 300;
    private int transitionCounter = 0;
    //private int backgroundSwapCounter; // makes sure background only changes when fader is black
    private int fadeTime = 120;
    private int worldLvl;
    private GreenfootSound forest; //level 1 background music
    private GreenfootSound boss; // level 3 background music (boss)

    //constructor
    public MainWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1068, 720, 1); 
        spawningXEnemy = getWidth();
        background = new Background();
        k = new Knight();
        
        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        
        addObject(background, 1086, 360); //add background first, so its behind everything
        
        waitForWaveToEnd = false;
        
        spawnParty();
        //spawnEnemies();
        
        forest = new GreenfootSound("Forest.mp3");
        boss = new GreenfootSound("Boss.mp3");
    }
    
    //act method
    public void act() {
        if (partyIsRunning()) {
            background.scrollBackground(k.getRunningSpeed());
        }
        
        spawnWaves();
        checkGameOver();
    }
    
    //tells us if the party members are running or not, info used by background class
    public boolean partyIsRunning() {
        return (k.runningSpeed != 0);
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
                        addObject(new SkeletonSpear(), spawningXEnemy, worldYLevel); //modify placement after
                        enemiesSpawned++;
                    } else if(enemyType == 1){
                        //addObject(new Archer(), 0, 0); // modify placement after
                        //enemiesSpawned++;
                    } else if(enemyType == 2){
                        //addObject(new Sword(), 0, 0); // modify placement after
                        //enemiesSpawned++;
                    } else if(enemyType == 3){
                        //addObject(new Flying(), 0, 0); //ylocation should be higher
                        //enemiesSpawned++;
                    }
                }
            }
        }
        
        
        if(wave == 1){
            if(enemiesSpawned == 50){
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
                }
            }
        }else if(wave == 2){
            if(enemiesSpawned == 10){
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
                }                
            }
        }else if(wave == 3){
            if(enemiesSpawned == 15){
                waitForWaveToEnd = true;
                if (checkWaveOver())
                {
                    waitForWaveToEnd = false;
                    waveOver = true;
                    enemiesSpawned = 0;
                    wave = wave + 1;
                    transitionCounter = transitionDelay; 
                    addObject(new Fader(fadeTime, -1, background), getWidth()/2, getHeight()/2);
                }
            }
        }else if(wave == 4){
            //spawnboss
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
        ArrayList<Party> playerList = (ArrayList<Party>) (getObjects(Party.class));
        if (playerList.isEmpty())
        {
            // Game Over stuff
        }
    }
    
    public static double getDistance (Actor a, Actor b)
    {
        return Math.hypot (a.getX() - b.getX(), a.getY() - b.getY());
    }
    //to start playing the music when pressed run
    public void started(){
        if(worldLvl == 1){
            forest.playLoop();
        }
        //if(worldLvl == 2){
        //    ____.playLoop(); // will find music for this
        //}
        if(worldLvl == 3){
            boss.playLoop();
        }
    }
    //to stop playing the music when pressed pause or reset
    public void stopped(){
        forest.stop();
        boss.stop();
    }
}

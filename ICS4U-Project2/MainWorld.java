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
    private int wave = 1;
    private int enemiesSpawned = 0;
    
    private int worldYLevel = 305;
    
    //Spawning Party Variables
    private int numOfKnights = 10;
    private int numOfMages = 0;
    private int numOfHealers = 0;
    private int spacingBetween = 40;
    private int spawningXParty = 50;

    //Spawning Enemies Variables
    private int waveOneEnemies = 5;
    private int waveTwoEnemies = 10;
    private int waveThreeEnemies = 15;
    private int spawningXEnemy;
    
    //Transition Variables
    private int transitionDelay = 300;
    private int transitionCounter = 0;
    
    private int worldLvl;
    private GreenfootSound forest; //level 1 background music
    private GreenfootSound boss; // level 3 background music (boss)

    /**
     * Constructor for objects of class MainWorld.
     * 
     */
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(543, 360, 1); 
        spawningXEnemy = getWidth();
        background = new Background();
        k = new Knight();
        
        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        
        addObject(background, 543, 180); //add background first, so its behind everything
        //addObject(k, 200, 305);
        
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
        
        if(!waveOver && wave != 4){
            if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                int enemyType = Greenfoot.getRandomNumber(4);
                if(enemyType == 0){
                    addObject(new Spear(), spawningXEnemy, worldYLevel); //modify placement after
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
        
        if(wave == 1){
            if(enemiesSpawned == 5){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
                transitionCounter = transitionDelay; 
                background.setWorldBackground(1);
            }
        }else if(wave == 2){
            if(enemiesSpawned == 10){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
                transitionCounter = transitionDelay; 
                background.setWorldBackground(2);
            }
        }else if(wave == 3){
            if(enemiesSpawned == 15){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
                transitionCounter = transitionDelay; 
            }
        }else if(wave == 4){
            //spawnboss
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

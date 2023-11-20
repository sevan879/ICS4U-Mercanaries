import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainWorld extends World
{   
    private Background b;
    private Knight k;
    private boolean waveOver;
    private int wave;
    private int enemiesSpawned;
    
    private int worldYLevel = 305;
    
    //Spawning Party Variables
    private int numOfKnights = 1;
    private int numOfMages = 0;
    private int numOfHealers = 0;
    private int spacingBetween = 40;
    private int spawningXParty = 50;

    //Spawning Enemies Variables
    private int numOfSpears = 2;
    private int numOfSwords = 0;
    private int numOfArchers = 0;
    private int spawningXEnemy;
    
    
    private int worldLvl;
    private GreenfootSound forest; //level 1 background music
    private GreenfootSound boss; // level 3 background music (boss)

    //constructor
    public MainWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1086, 720, 1); 
        spawningXEnemy = getWidth();
        b = new Background();
        k = new Knight();
        
        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        
        addObject(b, 1086, 360); //add background first, so its behind everything
        
        spawnParty();
        spawnEnemies();
        
        forest = new GreenfootSound("Forest.mp3");
        boss = new GreenfootSound("Boss.mp3");
    }
    
    //act method
    public void act() {
        if (partyIsRunning()) {
            b.scrollBackground(k.getRunningSpeed());
        }
    }
    
    //tells us if the party members are running or not, info used by background class
    public boolean partyIsRunning() {
        return (k.runningSpeed != 0);
    }
    public void spawnParty(){
        for (int i = 0; i < numOfMages; i++)
        {
            addObject(new Mage(), spawningXParty += spacingBetween, worldYLevel+300);
        }
        for (int i = 0; i < numOfHealers; i++)
        {
            addObject(new Healer(), spawningXParty += spacingBetween, worldYLevel+300);
        }
        for (int i = 0; i < numOfKnights; i++)
        {
            addObject(new Knight(), spawningXParty += spacingBetween, worldYLevel+300);
        }
    }
    
    public void spawnEnemies(){
        for (int i = 0; i < numOfSpears; i++)
        {
            addObject(new SkeletonSpear(), spawningXEnemy -= spacingBetween, worldYLevel+300);
        }
    }
    
    private void spawnWaves(){
        if(!waveOver){
            if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                int enemyType = Greenfoot.getRandomNumber(4);
                if(enemyType == 0){
                    addObject(new SkeletonSpear(), 0, 0); //modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 1){
                    addObject(new SkeletonArcher(), 0, 0); // modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 2){
                    addObject(new SkeletonWarrior(), 0, 0); // modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 3){
                    addObject(new Flying(), 0, 0); //ylocation should be higher
                    enemiesSpawned++;
                }
            }
        }
        
        if(wave == 1){
            if(enemiesSpawned == 30){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
            }
        }else if(wave == 2){
            if(enemiesSpawned == 40){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
            }
        }else if(wave == 3){
            if(enemiesSpawned == 50){
                waveOver = true;
                enemiesSpawned = 0;
                wave = wave + 1;
            }
        }else if(wave == 4){
            //spawnboss
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

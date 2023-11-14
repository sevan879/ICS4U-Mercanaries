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
        b = new Background();
        k = new Knight();
        
        //starts the level (there are a total of 3 levels) at level 1
        worldLvl = 1;
        
        addObject(b, 543, 180); //add background first, so its behind everything
        addObject(k, 200, 305);
        
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
    public void spawnMercenary(){
        if(Greenfoot.getRandomNumber(60) == 0){
            int type = Greenfoot.getRandomNumber(4);
            if(type == 0){
                addObject(new Knight() , 0, getHeight()/2 );
            }
            else if(type == 1){
                //addObject(new Archer(), 0, getHeight()/2 );
            }
            /*
            else if(type == 2){
                addObject(new , 0, getHeight()/2 );
            }
            else if(type == 3){
                addObject(new , 0, getHeight()/2 );
            }
            */
        }
    }
    public void spawnEnemy(){
        if(Greenfoot.getRandomNumber(60) == 0){
            int type = Greenfoot.getRandomNumber(4);
            if(type == 0){
                addObject(new SkeletonKnight() , 0, getHeight()/2 );
            }
            /*
            else if(type == 1){
                addObject(new SkeletonArcher(), 0, getHeight()/2 );
            }
            else if(type == 2){
                addObject(new SkeletonSpear(), 0, getHeight()/2 );
            }
            else if(type == 3){
                addObject(new SkeletonCrow(), 0, getHeight()/2 );
            }
            */
        }
    }
    
    private void spawnEnemies(){
        if(!waveOver){
            if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                int enemyType = Greenfoot.getRandomNumber(4);
                if(enemyType == 0){
                    addObject(new Spear(), 0, 0); //modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 1){
                    addObject(new Archer(), 0, 0); // modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 2){
                    addObject(new Sword(), 0, 0); // modify placement after
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

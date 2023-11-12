import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private boolean waveOver;
    private int wave;
    private int enemiesSpawned;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    
    private void spawnEnemies(){
        if(!waveOver){
            if(Greenfoot.getRandomNumber(20 - (wave + 1)) == 0){
                int enemyType = Greenfoot.getRandomNumber(3);
                if(enemyType == 0){
                    addObject(new Spear(), 0, 0); //modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 1){
                    addObject(new Archer(), 0, 0); // modify placement after
                    enemiesSpawned++;
                } else if(enemyType == 2){
                    addObject(new Sword(), 0, 0);
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
}

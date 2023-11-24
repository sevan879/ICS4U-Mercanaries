import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    private GreenfootImage gameOver;
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {    
        super(1068, 720, 1); 
        gameOver = new GreenfootImage("GameOverBackGround.jpg");
        setBackground(gameOver);
        
        
        addObject(new GameOverText(), 534, 150);
        addObject(new Button(3), 534, 300);
    }
}

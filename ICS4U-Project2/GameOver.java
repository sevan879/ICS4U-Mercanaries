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
    private speakCohen c;
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver()
    {
        super(1068, 720, 1); 
        gameOver = new GreenfootImage("GameOverBackGround.jpg");
        setBackground(gameOver);
        c = new speakCohen();
        
        addObject(new GameOverText(), 534, 150);
        addObject(new Button(4), 534, 300);
        addObject(c, 801, 450);
    }
    public void act(){
        c.cohenSmile();
    }
}

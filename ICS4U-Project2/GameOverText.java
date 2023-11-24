import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverText extends Actor
{
    private GreenfootImage text;
    
    public GameOverText(){
        text = new GreenfootImage("GameOverText.png");
        text.scale(1100, 600);
        setImage(text);
    }
}

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
    private SuperTextBox sp;
    private String[] text;
    
    private Font f;
    
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
        f = new Font("Comic Sans MS", false, false, 16);
        
        addObject(new GameOverText(), 534, 150);
        addObject(new Button(4), 534, 300);
        addObject(c, 850, 475);
        
        text = new String[1];
        text[0] = "Too easy";
        sp = new SuperTextBox(text, Color.WHITE, Color.BLACK, f, false, 100, 10, Color.BLACK);
    }
    
    public void act(){
        c.cohenSmile();
        addObject(sp, 700, 525);
    }
}

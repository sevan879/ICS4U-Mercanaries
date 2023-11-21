import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Backstory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Backstory extends World
{
    private GreenfootImage background;
    private speakKnight k;
    private speakWolf w;
    private SuperTextBox p;
    private Font f;
    private String [] sentence;
    
    //constructor
    public Backstory()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("BackstoryBackground.png");
        setBackground(background);
        k = new speakKnight();
        w = new speakWolf();
        addObject(k, 200,400);
        addObject(w, 900, 300);
        f = new Font ("Comic Sans MS", false, false, 16);
        sentence = new String[3];
        sentence[0] = "Sir Wolf, there appears to be another death in our village!";
        sentence[1] = "It seems like this viral disease you have been speculating recently is true.";
        sentence[2] = "What do we do wolf?!";
        
        p = new SuperTextBox(sentence, Color.WHITE, Color.BLACK, f, false, 200,  10, Color.BLACK);
    }
    public void act(){
        conversation();
    }
    public void conversation(){
        if(Greenfoot.isKeyDown("space")){
            addObject(p, 100, 200);
        }
    }
}

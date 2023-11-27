import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World for the backstory of the simulation. Dialogue world.
 * 
 * @author Justin Wu
 * @version V1
 */
public class Backstory extends World
{
    private GreenfootImage background;
    private speakKnight k;
    private speakVillager c;
    private SuperTextBox p1;
    private String [] sentence1;
    private SuperTextBox p2;
    private String [] sentence2;
    private SuperTextBox p3;
    private String [] sentence3;
    private SuperTextBox p4;
    private String [] sentence4;
    private SuperTextBox p5;
    private String [] sentence5;
    private Button b;
    
    
    
    private Font f;
    private int p;
    private int counter;
    private TitleScreen w;
    private int resize;
    
    
    /**
     * When simulation is stopped
     */
    public void stopped()
    {
        Button.stopped();
    }
    /**
     * Main Constructor for Backstory
     */
    public Backstory()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("castle bridge.png");
        resize = 2;
        background.scale(background.getWidth()/resize, background.getHeight()/resize);
        setBackground(background);
        k = new speakKnight();
        c = new speakVillager();
        addObject(k, 200,400);
        addObject(c, 900, 400);
        f = new Font ("Comic Sans MS", false, false, 16);
        p = 2;
        counter = 0;
        w = new TitleScreen();
        b = new Button(3);
        
        sentence1 = new String[4];
        sentence1[0] = "Village Chief! There appears to be another death";
        sentence1[1] = "in our village! It seems like the";
        sentence1[2] = "coding-maniac-sorcerer-exile, Sir Cohen, is causing";
        sentence1[3] = "trouble again from the forest! What do we do?!";
        p1 = new SuperTextBox(sentence1, Color.WHITE, Color.BLACK, f, false, 400,  10, Color.BLACK);
        
        sentence2 = new String[6];
        sentence2[0] = "That is terrible. Seems like he is creating an";
        sentence2[1] = "army to get revenge on us for kicking him out!";
        sentence2[2] = "He's never understood that our people need to";
        sentence2[3] = "learn English before understanding his words.";
        sentence2[4] = "Hearing his coding-jargon makes people go insane!";
        sentence2[5] = "WE MUST END THIS!";
        p2 = new SuperTextBox(sentence2, Color.WHITE, Color.BLACK, f, false, 400,  10, Color.BLACK);
        
        sentence3 = new String[4];
        sentence3[0] = "Lots of civilians’ died from his powerful coding";
        sentence3[1] = "language! He has created enemies to kill our villagers.";
        sentence3[2] = "At this rate, if his army keeps advancing";
        sentence3[3] = "our village will be destroyed!";
        p3 = new SuperTextBox(sentence3, Color.WHITE, Color.BLACK, f, false, 400,  10, Color.BLACK);
        
        sentence4 = new String[5];
        sentence4[0] = "I've had enough of him!";
        sentence4[1] = "We must find him and end this!";
        sentence4[2] = "I will send mercenaries in the forest.";
        sentence4[3] = "Knights! Mages! Dark Mages! Healers! GO FIGHT!";
        sentence4[4] = "KILL THE ENEMIES AND SIR COHEN";
        p4 = new SuperTextBox(sentence4, Color.WHITE, Color.BLACK, f, false, 400,  10, Color.BLACK);
        
        sentence5 = new String[1];
        sentence5[0] = "aighbet.";
        p5 = new SuperTextBox(sentence5, Color.WHITE, Color.BLACK, f, false, 150,  10, Color.BLACK);
        
        addObject(p1, 300, 225);
        
    }
    public void act(){
        if(counter == 10){
            conversation();
            counter = 0;
        }
        counter++;
    }
    private void conversation(){
        if(Greenfoot.isKeyDown("space")){
            if(p == 2){
                removeObject(p1);
                addObject(p2, 750, 200);
                p++;
            }
            else if(p == 3){
                removeObject(p2);
                addObject(p3, 300, 225);
                p++;
            }
            else if(p == 4){
                removeObject(p3);
                addObject(p4, 750, 200);
                p++;
            }
            else if(p == 5){
                removeObject(p4);
                addObject(p5, 300, 225);
                p++;
            }
            else if(p == 6){
                removeObject(p5);
                addObject(new Button(16), 534, 600);
            }
        }
    }
}

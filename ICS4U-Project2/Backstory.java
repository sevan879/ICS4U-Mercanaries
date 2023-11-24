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
    private speakCohen c;
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
    
    //add background music too
    
    //constructor
    public Backstory()
    {    
        super(1068, 720, 1);
        background = new GreenfootImage("castle bridge.png");
        resize = 2;
        background.scale(background.getWidth()/resize, background.getHeight()/resize);
        setBackground(background);
        k = new speakKnight();
        c = new speakCohen();
        addObject(k, 200,400);
        addObject(c, 900, 400);
        f = new Font ("Comic Sans MS", false, false, 16);
        p = 2;
        counter = 0;
        w = new TitleScreen();
        b = new Button(3);
    
        sentence1 = new String[4];
        sentence1[0] = "Sir Cohen, there appears to be another death";
        sentence1[1] = "in our village! It seems like this viral";
        sentence1[2] = "disease you have been speculating about";
        sentence1[3] = "is true. What do we do?!";
        p1 = new SuperTextBox(sentence1, Color.WHITE, Color.BLACK, f, false, 375,  10, Color.BLACK);
        
        sentence2 = new String[1];
        sentence2[0] = "That is terrible. Hmmm...what should we do?";
        p2 = new SuperTextBox(sentence2, Color.WHITE, Color.BLACK, f, false, 375,  10, Color.BLACK);
        
        sentence3 = new String[3];
        sentence3[0] = "Lots of civilians’ health have not only";
        sentence3[1] = "been affected, it is also affecting our";
        sentence3[2] = "businesses and food is more scarce!";
        p3 = new SuperTextBox(sentence3, Color.WHITE, Color.BLACK, f, false, 375,  10, Color.BLACK);
        
        sentence4 = new String[3];
        sentence4[0] = "Okay, so I will send you along with some other";
        sentence4[1] = "mercenaries. I will send mages and healers to";
        sentence4[2] = "support your quest. We will start right away!";
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
    public void conversation(){
        if(Greenfoot.isKeyDown("space")){
            if(p == 2){
                removeObject(p1);
                c.cohenSmile();
                addObject(p2, 750, 250);
                p++;
            }
            else if(p == 3){
                removeObject(p2);
                c.cohenRest();
                addObject(p3, 300, 225);
                p++;
            }
            else if(p == 4){
                removeObject(p3);
                c.cohenYell();
                addObject(p4, 750, 250);
                p++;
            }
            else if(p == 5){
                removeObject(p4);
                c.cohenSmile();
                addObject(p5, 300, 225);
                p++;
            }
            else if(p == 6){
                removeObject(p5);
                addObject(new Button(3), 534, 140);
                p++;
            }
            else if(p == 7){
                Greenfoot.setWorld(w);
            }
        }
    }
}

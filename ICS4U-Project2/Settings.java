import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Settings here.
 * 
 * @author Justin Wu 
 * @version V0
 */
public class Settings extends World
{
    private GreenfootImage background;

    private int numKnights;
    private int knightLevel;
    private int numHealers;
    private int healerLevel;
    private int numMages;
    private int magesLevel;
    private int difficulty;
    
    private GreenfootImage[] buttons;
    
    //constructor
    public Settings()
    {    
        buttons = new GreenfootImage[2];
        super(1068, 720, 1);
        background = new GreenfootImage("SettingsBackground.png");
        setBackground(background);
        
        buttons[0] = new GreenfootImage("up.png");
        buttons[0].scale(buttons[0].getWidth()/8, buttons[0].getHeight()/8);
        buttons[1] = new GreenfootImage("down.png");
        buttons[1].scale(buttons[1].getWidth()/8, buttons[1].getHeight()/8);
        
        buttons[2] = new GreenfootImage("up.png");
        buttons[2].scale(buttons[2].getWidth()/8, buttons[2].getHeight()/8);
        buttons[3] = new GreenfootImage("down.png");
        buttons[3].scale(buttons[3].getWidth()/8, buttons[3].getHeight()/8);
        
        buttons[4] = new GreenfootImage("up.png");
        buttons[4].scale(buttons[4].getWidth()/8, buttons[4].getHeight()/8);
        buttons[5] = new GreenfootImage("down.png");
        buttons[5].scale(buttons[5].getWidth()/8, buttons[5].getHeight()/8);
        
        buttons[6] = new GreenfootImage("up.png");
        buttons[6].scale(buttons[6].getWidth()/8, buttons[6].getHeight()/8);
        buttons[7] = new GreenfootImage("down.png");
        buttons[7].scale(buttons[7].getWidth()/8, buttons[7].getHeight()/8);
        
        buttons[8] = new GreenfootImage("up.png");
        buttons[8].scale(buttons[8].getWidth()/8, buttons[8].getHeight()/8);
        buttons[9] = new GreenfootImage("down.png");
        buttons[9].scale(buttons[9].getWidth()/8, buttons[9].getHeight()/8);
        
        buttons[10] = new GreenfootImage("up.png");
        buttons[10].scale(buttons[10].getWidth()/8, buttons[10].getHeight()/8);
        buttons[11] = new GreenfootImage("down.png");
        buttons[11].scale(buttons[11].getWidth()/8, buttons[11].getHeight()/8);
        
        buttons[12] = new GreenfootImage("up.png");
        buttons[12].scale(buttons[12].getWidth()/8, buttons[12].getHeight()/8);
        buttons[13] = new GreenfootImage("down.png");
        buttons[13].scale(buttons[13].getWidth()/8, buttons[13].getHeight()/8);
        
        buttons[14] = new GreenfootImage("up.png");
        buttons[14].scale(buttons[14].getWidth()/8, buttons[14].getHeight()/8);
        buttons[15] = new GreenfootImage("down.png");
        buttons[15].scale(buttons[15].getWidth()/8, buttons[15].getHeight()/8);
        
        
        addObject(new Button(5), 350, 100);
        addObject(new Button(4), 700, 100);
        
        addObject(new Button(5), 350, 175);
        addObject(new Button(4), 700, 175);
        
        addObject(new Button(5), 350, 250);
        addObject(new Button(4), 700, 250);
        
        addObject(new Button(5), 350, 325);
        addObject(new Button(4), 700, 325);
        
        addObject(new Button(5), 350, 400);
        addObject(new Button(4), 700, 400);
        
        addObject(new Button(5), 350, 475);
        addObject(new Button(4), 700, 475);
        
        addObject(new Button(5), 350, 550);
        addObject(new Button(4), 700, 550);
        
        addObject(new Button(5), 350, 625);
        addObject(new Button(4), 700, 625);
        
        setImage(buttons[4]);
        setImage(buttons[5]);

        image = buttons[buttonChooser];
        this.buttonChooser = buttonChooser;

    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this)) {
            if (buttonChooser == 0) {
                m = new MainWorld();
                Greenfoot.setWorld(m);
                m.started();
            }
            if (buttonChooser == 1) {
                s = new Settings();
                Greenfoot.setWorld(s);
            }
            if (buttonChooser == 2) {
                b = new Backstory();
                Greenfoot.setWorld(b);
            }    
            if(buttonChooser == 3){
                t = new TitleScreen();
                Greenfoot.setWorld(t);
            }
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Rain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rain extends LocalEffects
{
    private int actsLeft;
    private int speed;
    private int speed2;
    /**
     * Act - do whatever the Rain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Rain(){
        actsLeft = 300;
        speed = 10;
        speed2 = 2;
    }
    
    public void addedToWorld (World w){
        if(image == null){
            image = drawRain (getWorld().getWidth() * 2, getWorld().getHeight(), 1000);
        }
        setImage(image);
    }
    
    public void act()
    {
        actsLeft--;
        
        int randomChange = Greenfoot.getRandomNumber(7) - 3;
        double change  = randomChange / 10.0;
        
        setLocation (getX() - speed2, getY() + speed);
        
        if(actsLeft == 0){
            getWorld().removeObject(this);
        }
    }
    
    public static GreenfootImage drawRain (int width, int height, int density){
        Color [] swatch = new Color [32];
        
        int green = 170;
        
        for(int i = 0; i < swatch.length; i++){
            swatch[i] = new Color(242, green, 20);
            green += 2;
        }
        
        GreenfootImage temp = new GreenfootImage (width, height);
        
        for(int i = 0; i < density; i++){
            for(int k = 0; k < 100; k++){
                int randSize;
                
                int randColor = Greenfoot.getRandomNumber(swatch.length);
                
                temp.setColor(swatch[randColor]);
                
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);
                
                int tempVal = Greenfoot.getRandomNumber(250);
                if(tempVal >= 1){
                    temp.drawRect(randX, randY, 0, 0);
                }
                else{
                    randSize = Greenfoot.getRandomNumber(2) + 2;
                    temp.fillOval (randX, randY, randSize, randSize);
                }
            }
        }
        return temp;
    }
}

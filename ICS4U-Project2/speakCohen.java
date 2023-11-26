import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class speakWolf here.
 * 
 * @author Justin
 * @version (a version number or a date)
 */
public class speakCohen extends SuperSmoothMover
{
    private GreenfootImage w;
    /**
     * Main constructor for Fireball class.
     * @param explosionRange The size of the AOE of the fireball
     * @param damage The damage the explosion deals.
     */
    public speakCohen(){
        w = new GreenfootImage("rest.png");
        int percent = 100; 
        w.scale(w.getWidth()*percent/100, w.getHeight()*percent/100);
        setImage(w);
    }
    public void cohenAngry(){
        w = new GreenfootImage("angry.png");
        setImage(w);
    }
    public void cohenSmile(){
        w = new GreenfootImage("smile.png");
        setImage(w);
    }
    public void cohenRest(){
        w = new GreenfootImage("rest.png");
        setImage(w);
    }
    public void cohenYell(){
        w = new GreenfootImage("yell.png");
        setImage(w);
    }
}

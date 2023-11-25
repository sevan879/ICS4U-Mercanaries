import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Light here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Light extends Effects
{
    private GreenfootImage[] images;
    private int animationDelay;
    private int animationCounter;
    private int animationIndex;

   public Light(){
        animationDelay = 1;
        
        //setImage(images[1]);
   }
   
    public void animate(){
       images = new GreenfootImage[10];
       for (int i = 0; i < images.length; i++) {
            //System.out.println("animated");
            images[i] = new GreenfootImage("H0" + i + ".png");
            //images[i].scale(images[i].getWidth()*2, images[i].getHeight()*2);
       }
        if (animationCounter == 0){ 
            animationCounter = animationDelay; 
            animationIndex++; 
            setImage (images[animationIndex-1]);
            if(animationIndex == 9){
                animationIndex = 0;
                getWorld().removeObject(this);
            }
       } else {   
            animationCounter--;
       }
   }
    
   
    
    /*
    public void animate(){
        images = new GreenfootImage[8];
        for (int i = 0; i < images.length; i++) {
            //System.out.println("animated");
            images[i] = new GreenfootImage("H0" + (i+1) + ".png");
            images[i].scale(images[i].getWidth()*2, images[i].getHeight()*2);
            
            if(animationCounter == 0){
                setImage (images[i]);
                animationCounter = animationDelay;
            }else{
                animationCounter--;
            }
        }
        getWorld().removeObject(this);
        
        
    }
    */
   
   
    /**
     * Act - do whatever the Light wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        animate();
        
        // Add your action code here.
    }
}

/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/** Units that interact with the player */
public abstract class Villager extends Unit{
	
	//Abstract methods to be implemented
    public abstract void interact(Player player, double delta, boolean talk);
	public abstract String getDialogue();
    public abstract Image get_Sprite();
    public abstract String get_Name();
    
    /** Renders the Villagers and the dialogues with the player 
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    {
        this.get_Sprite().drawCentered((int) this.getpos()[0], (int) this.getpos()[1]);
        
        // Panel colours
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int dialogue_x ,dialogue_y; 

        float health_percent;       // Player's health, as a percentage
       
        // Gets a suitable width for the bar
        if ((g.getFont().getWidth(this.get_Name())) > 72){
        	bar_width = (g.getFont().getWidth(this.get_Name())) + 6;
        } else {
        	bar_width = 72;
        }
        bar_height = 20;
        
        bar_x = (int)this.getpos()[0]- bar_width/2;
        bar_y = (int)this.getpos()[1]-50;
        text_y = bar_y;      

        //Renders the name and health bar
        health_percent = (float)(this.getHealth()/this.getMaxHealth());     
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(this.get_Name())) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(this.get_Name(), text_x, text_y);
        
        // Creates the dialogue
        if (this.getDialogue() == ""){
        	return;
        }
        dialogue_x = (int)this.getpos()[0] - g.getFont().getWidth(this.getDialogue()) / 2;
        dialogue_y = (int)this.getpos()[1] - 75;
        
    	bar_width = (g.getFont().getWidth(this.getDialogue())) + 6;
    	
        g.setColor(BAR_BG);
        g.fillRect(dialogue_x, dialogue_y, bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(this.getDialogue(), dialogue_x, dialogue_y);
    }	
}
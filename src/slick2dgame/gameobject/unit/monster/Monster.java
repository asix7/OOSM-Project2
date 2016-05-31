package slick2dgame.gameobject.unit.monster;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import slick2dgame.core.World;
import slick2dgame.gameobject.unit.Player;
import slick2dgame.gameobject.unit.Unit;

/** Creatures that are part of the World that can be attacked 
 *  by the player 
 */
public abstract class Monster extends Unit{
	
	// Abstract methods to be implemented	
	public abstract void update(World world, Player player, int delta);
	public abstract String get_Name();
	public abstract void set_healt(double damage_dealt);
	public abstract double getHealth();
	
	/** Change the monster health when reciving damage 
	 * @param damage_dealt
	 */
	public void update_health(double damage_dealt) {
		this.set_healt(damage_dealt);		
	}

	/** Checks if the monster is dead */
	public boolean is_dead(){
		if (this.getHealth() <= 0){
			return true;
		}
		return false;
	}
	
	/** Renders the Monsters into the World. 
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
	
	    float health_percent;       // Player's health, as a percentage
	   
	    // Calculates the bar width to be rendered
	    if ((g.getFont().getWidth(this.get_Name())) > 72){
	    	bar_width = (g.getFont().getWidth(this.get_Name())) + 6;
	    } else {
	    	bar_width = 72;
	    }
	    bar_height = 20;
	    
	    bar_x = (int)this.getpos()[0]- bar_width/2;
	    bar_y = (int)this.getpos()[1]-50;
	    text_y = bar_y;
	    
        // Displays the current health of the mosnter
	    health_percent = (float)(this.getHealth()/this.getMaxHealth());     
	    hp_bar_width = (int) (bar_width * health_percent);
	    
	    text_x = bar_x + (bar_width - g.getFont().getWidth(this.get_Name())) / 2;
	    g.setColor(BAR_BG);
	    g.fillRect(bar_x, bar_y, bar_width, bar_height);
	    g.setColor(BAR);
	    g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
	    g.setColor(VALUE);
	    g.drawString(this.get_Name(), text_x, text_y);
	    
    }

}

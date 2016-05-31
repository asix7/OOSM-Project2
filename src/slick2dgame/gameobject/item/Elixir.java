package slick2dgame.gameobject.item;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Elixir extends Item {
	
	// Item position
	private double[] position = new double[2];
    // Image to render
	private Image sprite;
	// Status of the item
	private boolean collected=false;
	// Type of item
	private String type="elixir";
	
	/** Creates the item elixir */
    public Elixir(String image_path,double position[])
        	throws SlickException
        {
        	sprite = new Image(image_path);
            this.position = position;
        }
	
	/** Gets the actual position in the map */
	public double[] getpos() {
		return position;
	}
	/** Changes the position of the item */
	public void setposition(int x, int y) {
		position[0]=x;
		position[1]=y;		
	}
	/** Gets the sprite to render */
	public Image get_Sprite() {
		return sprite;
	}
	/** Tells if the item has been collected*/	
	public boolean getStatus(){
		return collected;
	}
	/** Sets the status to the item */
	public void setStatus(boolean status) {
		collected=status;
	}	
	/** Tells the type of object */
	public String getType() {
		return type;
	}
}
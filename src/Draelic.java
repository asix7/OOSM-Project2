/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Draelic extends Aggressive_Monster{
    private Image sprite = null;
    private Image sprite_flipped = null;

    // In pixels
    private double[] position = new double[2];
    private boolean face_left = false;
    
    private final int DAMAGE = 30;
    private final double COOLDOWN = 400;
    private double current_cd = 0;
    private double max_health = 140;
    private double current_health;
    private String name;    
    /** Get name to display*/
    public String get_Name(){
    	return name;
    }
    
    /** Get the current position of the monster */
	public double[] getpos() {
		return position;
	}
	/** Get the maximun damage */
	public int getDamage(){
		return DAMAGE;
	}
	/** update health after taking damage */
	public void set_healt(double damage_dealt) {
		current_health-=damage_dealt;		
	}
	/** Get the current health */
	public double getHealth() {
		return current_health;
	}
	/** Get the maximun health */
	public double getMaxHealth() {
		return max_health;
	}
	
	/** Get the imaget to render */
	public Image get_Sprite() {
		Image which_img = this.face_left ? this.sprite_flipped : this.sprite;
		return which_img;
	}
	
	/** Get the natural cooldown of the monster */
	public double getCD() {
		return COOLDOWN;
	}
	/** Get the current cooldown of the monster */
	public double get_currentCD() {
		return current_cd;
	}
	/** updates the cooldwon of the mosnter */
	public void set_currentCD(double cd) {
		if (cd<0){
			current_cd+=cd;
		} else{
			current_cd=cd;
		}
	} 
	/** Set where the mosnter is facing */
	public void setFace(boolean left){
		face_left=left;
	}
    
	/** Creates a draelic in the map */
    public Draelic(String image_path, double[] initial_position) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
        sprite_flipped = sprite.getFlippedCopy(true, false);
        current_health = max_health;
        this.position = initial_position; 
        name="Draelic";
    } 

}
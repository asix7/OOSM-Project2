package slick2dgame.gameobject.unit.monster.aggresive;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Skeleton extends Aggressive_Monster{
    private Image sprite = null;
    private Image sprite_flipped = null;

    // In pixels
    private double[] position = new double[2];
    private boolean face_left = false;

    private final int DAMAGE = 16;
    private final double COOLDOWN = 500;
    private double current_cd = 0;
    private double max_health = 100;
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
    
	/** Creates a skeleton in the map */
    public Skeleton(String image_path, double[] initial_pos) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
        sprite_flipped = sprite.getFlippedCopy(true, false);
        current_health = max_health;
    	position[0] = initial_pos[0];
    	position[1] = initial_pos[1];
    	name="Skeleton";
    }

}
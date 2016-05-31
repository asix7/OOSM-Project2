package slick2dgame.gameobject.unit.monster.passive;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Giant_Bat extends Passive_Monster {


    private Image sprite = null;
    private Image sprite_flipped = null;

    // In pixels
    private double[] position = new double[2];
    private boolean face_left = false;    
    
    private int current_health = 100;
    private int max_health = 100;
    
    private int wait = 3000;
    private int safe = 5000;
    private int[] new_dir = {0,0};
    private String name;
    
    public String get_Name(){
    	return name;
    }
    
    private boolean under_attack = false;

	public double[] getpos() {
		return position;
	}
	public void set_healt(double damage_dealt) {
		under_attack = true;
		current_health -= damage_dealt;		
	}
	public double getHealth() {
		return current_health;
	}
	public Image get_Sprite() {
		Image which_img = this.face_left ? this.sprite_flipped : this.sprite;
		return which_img;
	}
	public double getMaxHealth() {
		return max_health;
	}
	

    
    public Giant_Bat(String image_path, double[] initial_position) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
        sprite_flipped = sprite.getFlippedCopy(true, false);
        this.position=initial_position; 
        name="Giant Bat";
    }

	public void setFace(boolean left) {
		face_left=left;		
	}

	public int[] get_new_dir() {
		return new_dir;
	}

	public int getwait() {
		return wait;
	}

	public void setwait(int time) {
		if (time <0){
			wait += time;
		} else {
			wait = time;
		}
	}

	public void set_new_dir(int[] dir) {
		new_dir=dir;		
	}

	public void set_under_attack(boolean logic) {
		under_attack=logic;		
	}

	public void setsafe(int time) {
		if (time <0){
			safe += time;
		} else {
			safe = time;
		}	
	}

	public int getsafe() {
		return safe;
	}

	public boolean under_attack() {
		return under_attack;
	}    

}


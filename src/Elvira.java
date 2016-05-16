/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**Healer and helper rescue the player when he dies */
public class Elvira extends Villager {

    private static final double INTER_RANGE = 50;
	private Image sprite = null;
    private double[] position = new double[2];
    private String[] dialogue;
    private int which_dialogue = 2;
    private int time=0;
    private int health = 1;
    private int max_health = 1;
    private String name;
    
    /** Gets the name to display */
    public String get_Name(){
    	return name;
    }
    /** gets the current position in the map */
	public double[] getpos() {
		return position;
	}
	/** Get the Images to render */
	public Image get_Sprite() {
		return sprite;
	}
	/** Get the Current Health */
	public double getHealth() {
		return health;
	}
	/** Get the Maximun Health */
	public double getMaxHealth() {
		return max_health;
	}
	
	/** Gets the correct dialogue to render */
	public String getDialogue() {
		if (which_dialogue == 2){
			return "";
		}
		return dialogue[which_dialogue];
	}   
	

	/** Creates the shaman in the world */
    public Elvira(String image_path, double[] initial_pos) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
    	position = initial_pos;
        dialogue = new String[2];
        dialogue[0] = "Return to me if you ever need healing.";
        dialogue[1] = "You're looking much healthier now.";
        name = "Elvira";
    }
    
    /** Heals the player and change dialogue accordign 
     * to the situation. Displays the first dialogue for
     *  2 secs.
     *  @param player Player to heal
     *  @ delta Time passed since last frame (milliseconds). 
     */
    public void interact(Player player, double delta, boolean talk)
    {
    	double distance[] = new double[2];
    	distance = player.distanceTo(this);
    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);
    	
		if(dis_total<= INTER_RANGE){
			if(player.getHealth()== player.getMaxHealth()){
				if (time<=0 && talk){
					which_dialogue = 0;
				}
			} else {
				if (talk){
					which_dialogue = 1;
					// 2 Secs before changing dialogue
					time=2000;
				}
				player.update_health(player.getHealth()-player.getMaxHealth());				
			}
			time -= delta;
		} else {
			which_dialogue = 2;
		}
    }
}
package slick2dgame.gameobject.unit.villager;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import slick2dgame.gameobject.unit.Player;


public class Prince extends Villager {

    private static final double INTER_RANGE = 50;
	private Image sprite = null;
    private double[] position = new double[2];
    
    private int health = 1;
    private int max_health = 1;
    private String[] dialogue;
    private int which_dialogue;
    private String name;
    private boolean elixir = false;
        
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

	/** Creates the Price that needs your help */
    public Prince(String image_path, double[] initial_pos) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
    	position = initial_pos;
        dialogue = new String[2];
        dialogue[0] = "Please seek out the Elixir of Life to cure the king.";
        dialogue[1] =  "The elixir! My father is cured! Thankyou!";
        name = "Prince Aldric";
    }
    
    /** Ask for help to get the elixir, if the player bring 
     * the elixir it shows the end game dialogue
     * @param player The player who has the 
     * mission to bring the elixir
     * @ delta Time passed since last frame (milliseconds).
     */ 
    public void interact(Player player, double delta, boolean talk)
    {
    	double distance[] = new double[2];
    	distance = player.distanceTo(this);
    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);
    	
		if(dis_total<= INTER_RANGE && talk){
		    if((player.getInventory()[3] == null) && !elixir){
		    	which_dialogue=0;
		    }else{
		    	which_dialogue=1;
		    	elixir = true;
		    	player.end_game();
		    }
       	} else {
       		which_dialogue=2;
       	}
    }
}
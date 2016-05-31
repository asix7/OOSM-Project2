package slick2dgame.gameobject.unit.villager;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import slick2dgame.gameobject.unit.Player;


public class Garth extends Villager {

    private static final double INTER_RANGE = 50;
	private Image sprite = null;
    private double[] position = new double[2];
    
    private int health = 1;
    private int max_health = 1;
    private String[] dialogue;
    private int which_dialogue;
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
		if (which_dialogue == 5){
			return "";
		}
		return dialogue[which_dialogue];
	}   
	

    public Garth(String image_path, double[] initial_pos) 
    		throws SlickException
    {
    	sprite = new Image(image_path);
    	position = initial_pos;
        dialogue = new String[4];
        dialogue[0] = "Find the Amulet of Vitality, across the river to the west.";
        dialogue[1] = "Find the Sword of Strength - cross the river and back, on the east side.";
        dialogue[2] = "Find the Tome of Agility, in the Land of Shadows.";
        dialogue[3] = "You have found all the treasure I know of.";
        name = "Garth";
    }
    
    /** Garth tells where to find useful items depending
     *  the player's inventory.
     * @player The player to give advice
     * @delta Time passed since last frame (milliseconds).
     */
    public void interact(Player player, double delta, boolean talk)
    {
    	double distance[] = new double[2];
    	distance = player.distanceTo(this);
    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);
    	
		if(dis_total <= INTER_RANGE && talk){
			if(player.getInventory()[0] == null){
				which_dialogue = 0;
			} else if (player.getInventory()[2] == null){
				which_dialogue = 1;
			} else if (player.getInventory()[1] == null){
				which_dialogue = 2;
			} else {
				which_dialogue = 3;
			}		
       	} else {
       		which_dialogue=5;
       	}
    }
}
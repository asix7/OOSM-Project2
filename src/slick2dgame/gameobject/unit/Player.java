package slick2dgame.gameobject.unit;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import slick2dgame.core.World;
import slick2dgame.gameobject.item.Item;
import slick2dgame.gameobject.unit.monster.Monster;

/** The character which the user plays as.
 */
public class Player extends Unit
{
    private Image sprite = null;
    private Image sprite_flipped = null;
    private boolean face_left = false;

    // In pixels
    private double[] position = new double[2];
    private double[] respawn_pos = {756, 870};
    private static final double ATTACK_RANGE = 50;

    // Pixels per millisecond
    private static final double SPEED = 0.25;
    
    //Max damage
    private int damage = 30;
    private boolean attack = false;
    
    // In milliseconds
    private double cooldown = 400;
    private double current_cd = 0;
    
    // Health Status
    private double health;
    private double max_health = 100;
    
    // Inventory with 4 slots
	private Item[] inventory = new Item[4];
	
	/** Get the image to render
	 *  as default 
	 */
	public Image get_Sprite() {
		return sprite;
	}
	
	/** Get the actual position of
	 *  the player 
	 */
	public double[] getpos() {
		return position;
	}
	
	/** Max Damage getter 
	 * @return Possible damage
	 */
    public double getDamage()
    {
        return damage;
    }
    
    /** Cooldown getter 
     * @return max cooldown value
     */
    public double getCD()
    {
        return cooldown;
    }

	/** Check if player has attacked 
	 * @return true or false
	 */
    public boolean getAttack()
    {
        return attack;
    }
    
    /**Max Health getter
    */
    public double getMaxHealth()
    {
        return max_health;
    }
    
    /**Health getter
     * @return Current health
     */
    public double getHealth()
    {
        return health;
    }
    
    /** Update health if a monster has dealt damage 
     * @param damage_dealt Damage from monster 
     */
	public void update_health(double damage_dealt) {
		health-=damage_dealt;		
	}
	
	/** Inventory getter 
	 * @return updated inventory
	 */
	public Item[] getInventory() {
		return inventory;
	}
	
    /** Retrive elixir after delivery 
     */
	public void end_game(){
		inventory[3] = null;
	}
    
    /** Creates a new Player.
     * @param image_path Path of player's image file.
     * @param initial_pos Position(x,y) where the adventure 
     * starts.
     */
    public Player(String image_path, double[] initial_pos)
        throws SlickException
    {
        sprite = new Image(image_path);
        sprite_flipped = sprite.getFlippedCopy(true, false);
        position = initial_pos;
        health = max_health; 
    }    
    /** Player attacks a monster
     * @param monster Target
     * @param delta Time passed since last frame (milliseconds)
     */
    public void attack(Monster monster, int delta){
    	double damage_dealt = 0;
    	double distance[] = new double[2];
    	distance = monster.distanceTo(this);
    	if((Math.abs(distance[0]) <= ATTACK_RANGE) && (Math.abs(distance[1]) <= ATTACK_RANGE)){
    		if (current_cd <= 0){
    	    	if (attack == true){
    	    		damage_dealt = Math.random()*damage;
    	    		monster.update_health(damage_dealt);
    	    		current_cd = cooldown;
    	    	}    			
    		}
    	}	    
    }

    /** Move the player in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing. Make the player respawn in a given 
     * area and make the player able to attack.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @param attack Vulue that says when the player is attaking;
     */
    public void update(World world, double dir_x, double dir_y, int delta, boolean attack)
    {
        // Update player facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;

        // Move the player by dir_x, dir_y, as a multiple of delta * speed
        double new_pos[] = new double[2];
        this.attack = attack;
        
        new_pos[0] = position[0] + dir_x * delta * SPEED;
        new_pos[1] = position[1] + dir_y * delta * SPEED;
        
        if(health <= 0){
        	position = respawn_pos;
        	return;
        }
        if(health>max_health){
        	health = max_health;
        }
        moveTo(world,new_pos);
      	current_cd -= delta;   
    }


	/** Updates the player's inventory and stats according to
	 *  the collected item. 
	 * @param item Item collected
	 */
	public void collect_items(Item item){
		String type = item.getType();
		if (type == "amulet"){
			health += 80;
			max_health += 80;	
			inventory[0]=item;
		} else if (type == "book"){
			cooldown -= 300;
			inventory[1]=item;
		} else if (type == "sword"){
			damage += 30;
			inventory[2]=item;
		} else if (type == "elixir"){
			inventory[3]=item;
		}
	}
	
	/** Renders the player in the current position facing
	 * a determined direction. 
	 */
	public void render() {
        Image which_img;
        which_img = this.face_left ? this.sprite_flipped : this.sprite;
        which_img.drawCentered((int) position[0], (int) position[1]);
        
	}
}


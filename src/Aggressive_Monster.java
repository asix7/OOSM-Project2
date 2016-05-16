/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

/** Monsters that attack the player */
public abstract class Aggressive_Monster extends Monster{
	
	// Common fields for Aggressive_Monsters
	private static final double AGGRO_RANGE = 150;
	private static final double ATTACK_RANGE = 50;
	private static final double SPEED = 0.25;

	//Abstract methods to be defined
	public abstract int getDamage();
    public abstract double getCD();
    public abstract double get_currentCD();
    public abstract void set_currentCD(double cd);
	public abstract void setFace(boolean left);
	
	/** Attack the player with a damge between 0 and max damage
	 * @param player The player that the monsters attacks
	 */
	public void attack(Player player) {
	  	double damage_dealt= Math.random()*this.getDamage();
       	player.update_health(damage_dealt);
	}
	
	/** Updates the actions of the monsters in game.
	 * @param world The current World.
	 * @param player The player to interact.
	 * @param delta Time passed since last frame (milliseconds). 
	 */
    public void update(World world, Player player, int delta)
    { 
    	// Calculates distances
    	double distance[] = new double[2];
    	distance = player.distanceTo(this);
    	double new_pos[] = new double[2];
    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);
    	
        // Update player facing based on X direction
    	int dir_x = (int) Math.signum(distance[0]);
       
    	// Actions taking place when the player enters the monster vision
		if(dis_total <= AGGRO_RANGE){
        	new_pos[0] = this.getpos()[0] + (distance[0]/dis_total) * delta * SPEED;
        	new_pos[1] = this.getpos()[1] + (distance[1]/dis_total) * delta * SPEED;
        	
        	// Where the monster is facing
	        if (dir_x > 0){
	        	setFace(false);
	        }else if (dir_x < 0){
	            setFace(true);
	        }
	        
	        // Actions when attacking
        	if(dis_total <= ATTACK_RANGE){
        		new_pos[0] -= (distance[0]/dis_total) * delta * SPEED;
        		new_pos[1] -= (distance[1]/dis_total) * delta * SPEED;
        		if (this.get_currentCD() == 0){
        			this.attack(player);
        			set_currentCD(this.getCD());
        		}
        	}
       	}
		// Monster state updates
		player.attack(this,delta);
        moveTo(world, new_pos);
        
        //Updates the cooldown system
        if (this.get_currentCD() <= 0){
        	set_currentCD(0);
        } else {
        	set_currentCD(-delta);
        }
    }
}

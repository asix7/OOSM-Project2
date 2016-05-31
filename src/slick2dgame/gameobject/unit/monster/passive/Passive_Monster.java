package slick2dgame.gameobject.unit.monster.passive;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import slick2dgame.core.World;
import slick2dgame.gameobject.unit.Player;
import slick2dgame.gameobject.unit.monster.Monster;

/**Monsters that wonder the map util are attacked by the player
 */
public abstract class Passive_Monster extends Monster {
	
	// Speed in pixel per second
	private static final double SPEED = 0.20;
	
	// Abstract classes to be implemented
	public abstract void setFace(boolean left);
	public abstract int[] get_new_dir();
	public abstract int getwait();
	public abstract void setwait(int time);
	public abstract void set_new_dir(int[] dir);
	public abstract void set_under_attack(boolean logic);
	public abstract void setsafe(int time);
	public abstract int getsafe();
	public abstract boolean under_attack();

	/** Generates a random direction 
	 * @param direction random number generated between (0-8)
	 * @return
	 */
	public int[] dir(int direction){
		
		int next_dir[] = new int [2];
    	if (direction == 1){
    		next_dir[0]=0;
    		next_dir[1]=1;
    		
    	} else if (direction == 2){
    		next_dir[0]=0;
    		next_dir[1]=-1;
    		
    	} else if (direction == 3){
    		next_dir[0]=1;
    		next_dir[1]=0;
    		
    	} else if (direction == 4){
    		next_dir[0]=1;
    		next_dir[1]=1;
    		
    	} else if (direction == 5){
    		next_dir[0]=1;
    		next_dir[1]=-1;
    		
    	} else if (direction == 6){
    		next_dir[0]=-1;
    		next_dir[1]=0;
    		
    	} else if (direction == 7){
    		next_dir[0]=-1;
    		next_dir[1]=1;
    		
    	} else if (direction == 8){
    		next_dir[0]=-1;
    		next_dir[1]=-1;
    		
    	} else {
    		next_dir[0]=0;
    		next_dir[1]=0;
    	}
    	return next_dir;
	}
	
	/**Updates the state of the Monster
	 * @param world The current World.
	 * @param player The player to interact.
	 * @param delta Time passed since last frame (milliseconds). 
	 */
	public void update(World world, Player player, int delta)
	{ 
	  	double new_pos[] = {0,0};
	  	// Generates a number between (0-8), 8.99 so it rounds to 8.  
		int direction = (int)Math.floor(8.99*Math.random());
		
		// Movement under attack
		if (this.under_attack() && (this.getsafe() <= 5000) && (this.getsafe() > 0) ){
	    	double distance[] = new double[2];
	    	distance = player.distanceTo(this);
	    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);    		
	    	new_pos[0] = this.getpos()[0] - (distance[0]/dis_total) * delta * SPEED;
	    	new_pos[1] = this.getpos()[1] - (distance[1]/dis_total) * delta * SPEED;
	    	setsafe(-delta) ;
	    	
		} else if (this.under_attack() && (this.getsafe() <= 0)){
			setsafe(5000);
			set_under_attack(false);   
		// Normal Movement	
		} else if (this.getwait() <= 0){
			this.set_new_dir(dir(direction));
	        // Update player facing based on X direction
	    	int dir_x = (int) Math.signum(this.get_new_dir()[0]);
	        if (dir_x > 0){
	            setFace(false);
	        }else if (dir_x < 0){
	        	setFace(true);
	        }
	        if ((this.get_new_dir()[0] != 0) &&(this.get_new_dir()[0] != 1)){
	    		new_pos[0] = this.getpos()[0] + this.get_new_dir()[0]*(1/Math.sqrt(2)) * delta * SPEED;
	    		new_pos[1] = this.getpos()[1] + this.get_new_dir()[1]*(1/Math.sqrt(2)) * delta * SPEED;
	        }else {
	        	new_pos[0] = this.getpos()[0] + this.get_new_dir()[0] * delta * SPEED;
	    		new_pos[1] = this.getpos()[1] + this.get_new_dir()[1] * delta * SPEED;
	    	}
			setwait(3000);
		} else {
			setwait(-delta);
	        if ((this.get_new_dir()[0] != 0) &&(this.get_new_dir()[1] != 0)){
	    		new_pos[0] = this.getpos()[0] + this.get_new_dir()[0]*(1/Math.sqrt(2)) * delta * SPEED;
	    		new_pos[1] = this.getpos()[1] + this.get_new_dir()[1]*(1/Math.sqrt(2)) * delta * SPEED;
	        }else {
	        	new_pos[0] = this.getpos()[0] + this.get_new_dir()[0] * delta * SPEED;
	    		new_pos[1] = this.getpos()[1] + this.get_new_dir()[1] * delta * SPEED;
	    	}
		}    	
		player.attack(this,delta);
	    moveTo(world, new_pos);  
	}
}

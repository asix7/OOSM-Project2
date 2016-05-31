package slick2dgame.gameobject.unit;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import slick2dgame.core.World;
import slick2dgame.gameobject.item.Game_Object;

public abstract class Unit extends Game_Object{
	
	//Position of the object in the map	
	private double[] position;
	
	// Abstract medthos to be implemented
	public abstract double getHealth();
	public abstract double getMaxHealth();
	
	/** Check if the Unit can move to the next position 
	 * @param world The current world
	 * @param new_pos Intended position
	 */
	public void moveTo(World world, double[] new_pos) {
        // Move in x first
		position = this.getpos();
        if(!world.terrainBlocks(new_pos[0], position[1])) {
            position[0] = new_pos[0];
        }
        
        // Then move in y
        if(!world.terrainBlocks(position[0], new_pos[1])){
        	position[1] = new_pos[1];
        }
	}	
}

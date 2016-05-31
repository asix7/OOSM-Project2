package slick2dgame.gameobject.item;
/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import slick2dgame.gameobject.unit.Player;

/** Items of the World that can be collected by the player 
 * */
public abstract class Item extends Game_Object {
	
	// Abstract classes to be implemented
	public abstract boolean getStatus();
	public abstract void setStatus(boolean status);
	public abstract String getType();
	public abstract void setposition(int x, int y);

	/** Updates the item state when interacts with the player 
	 * @param player The player that collects the items
	 */
	public void collect(Player player){
		if (this.getStatus() == false){
	    	double distance[] = new double[2];
	    	distance = player.distanceTo(this);
	    	double dis_total = Math.sqrt(distance[0]*distance[0]+distance[1]*distance[1]);
			if (dis_total <= 50){
				this.setStatus(true);
				player.collect_items(this);
			}	
		}
	}	

}

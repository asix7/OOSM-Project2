/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */
import org.newdawn.slick.Image;

/** Objects that can be renderer into the World and interact with others 
 * */
public abstract class Game_Object {
	
	public abstract Image get_Sprite();	
	public abstract double[] getpos();
	
	/** Calculates distances between two game objects 
	 * @param Object the object to make the calculation
	 */
	public double[] distanceTo(Game_Object object) {
		double[] position = this.getpos();
		double distance[];
		distance = new double[2];
		distance[0]= (position[0]-object.getpos()[0]);
		distance[1]= (position[1]-object.getpos()[1]);
		return distance;
	}
	/** Default rendering of an Object in the Game
	 *  according to its map position 
	 */
	public void render(){	
			this.get_Sprite().drawCentered((int)this.getpos()[0],(int)this.getpos()[1]);
	}
}

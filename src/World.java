/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: Andres Landeta 631427 <alandeta>
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	// Game's player
    private static final double[] PLAYER_START = {756, 684};
    private Player player;
    
    // Villagers and initial positions
    private Prince prince_aldric;
    private Garth garth;
    private Elvira elvira;
    private Villager[] villagers;
    private double[][] villagers_pos = {{738, 549},{467, 679},{756, 870}};
    
    // Monsters and initial positions
    private Draelic[] draelic;
    private double[][] draelic_pos = {{2069,510}};
    
    private Zombie[] zombies;
    private double[][] zombies_pos = {{681,3201},{691,4360},{2166,2650},{2122,2725},{2284,2962},{2072,4515},
    		{2006,4568},{2385,4629},{2446,4590},{2517,4532},{4157,6730},{4247,6620},{4137,6519},{4234,6449},
    		{5879,4994},{5954,4928},{6016,4866},{5860,4277},{5772,4277},{5715,4277},{5653,4277},{5787,797},
    		{5668,720},{5813,454},{5236,917},{5048,1062},{4845,996},{4496,575},{3457,273},{3506,779},
    		{3624,1192},{2931,1396},{2715,1326},{2442,1374},{2579,1159},{2799,1269},{2768,739},{2099,956}};
    
    private Bandit[] bandits;
    private double[][] bandits_pos = {{1889,2581},{4502,6283},{5248,6581},{5345,6678},{5940,3412},{6335,3459},
    		{6669,260},{6598,339},{6598,528},{6435,528},{6435,678},{5076,1082},{5191,1187},{4940,1175},
    		{4760,1039},{4883,889},{4427,553},{3559,162},{3779,1553},{3573,1553},{3534,2464},{3635,2464},
    		{3402,2861},{3151,2857},{3005,2997},{2763,2263},{2648,2263},{2621,1337},{2907,1270},{2331,598}
    		,{2987,394},{1979,394},{2045,693},{2069,1028}};
    
    private Skeleton[] skeletons;
    private double[][] skeletons_pos = {{1255,2924},{2545,4708},{4189,6585},{5720,622},{5649,767},{5291,312},
    		{5256,852},{4790,976},{4648,401},{3628,1181},{3771,1181},{3182,2892},{3116,3033},{2803,2901},
    		{2850,2426},{2005,1524},{2132,1427},{2242,1343},{2428,771},{2427,907},{2770,613},{2915,477},
    		{1970,553},{2143,1048}};
    
    private Giant_Bat[] giant_bats;
    private double[][] giant_bats_pos = {{1431,864},{1154,1321},{807,2315},{833,2657},{1090,3200},{676,3187},
    		{836,3966},{653,4367},{1343,2731},{1835,3017},{1657,3954},{1054,5337},{801,5921},{560,6682},
    		{1275,5696},{1671,5991},{2248,6298},{2952,6373},{3864,6695},{4554,6443},{4683,6228},{5312,6606},
    		{5484,5946},{6371,5634},{5473,3544},{5944,3339},{6301,3414},{6388,1994},{6410,1584},{5314,274}};
    
    private Monster[][] monsters;
    
    // Items and positions
    private Amulet amulet;
    private Book  book;
    private Elixir elixir;
    private Sword sword;
    private Item items[];
    private double[][] items_pos = {{965, 3563},{546, 6707},{1976, 402},{4791, 1253}};
    
    // Map, panel and camara
    private TiledMap map = null;
    private Camera camera = null;
    private Image panel = null;

    /** Map width, in pixels. */
    private int getMapWidth()
    {
        return map.getWidth() * getTileWidth();
    }

    /** Map height, in pixels. */
    private int getMapHeight()
    {
        return map.getHeight() * getTileHeight();
    }

    /** Tile width, in pixels. */
    private int getTileWidth()
    {
        return map.getTileWidth();
    }

    /** Tile height, in pixels. */
    private int getTileHeight()
    {
        return map.getTileHeight();
    }

    /** Create a new World object. */
    public World()
    throws SlickException
    {
        map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);
        player = new Player(RPG.ASSETS_PATH + "/units/player.png", PLAYER_START);
        camera = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT-RPG.PANELHEIGHT);
        panel = new Image(RPG.ASSETS_PATH+"/panel.png");
        
        prince_aldric = new Prince(RPG.ASSETS_PATH + "/units/prince.png", villagers_pos[1]);
        garth = new Garth(RPG.ASSETS_PATH + "/units/peasant.png", villagers_pos[2]);
        elvira= new Elvira(RPG.ASSETS_PATH + "/units/shaman.png", villagers_pos[0]);
        villagers = new Villager[]{elvira, prince_aldric,garth};
                
        amulet = new Amulet(RPG.ASSETS_PATH + "/items/amulet.png",items_pos[0]);
        book = new Book(RPG.ASSETS_PATH + "/items/book.png",items_pos[1]);
        elixir = new Elixir(RPG.ASSETS_PATH + "/items/elixir.png",items_pos[2]);
        sword = new Sword(RPG.ASSETS_PATH + "/items/sword.png",items_pos[3]);
        items = new Item[]{amulet,book,elixir,sword};
        
        zombies = create_zombies(zombies, zombies_pos);
        skeletons = create_skeletons(skeletons,skeletons_pos);
        bandits = create_bandits(bandits, bandits_pos);
        giant_bats = create_bats(giant_bats, giant_bats_pos);
        draelic = new Draelic[1];
        draelic[0]= new Draelic(RPG.ASSETS_PATH + "/units/necromancer.png",draelic_pos[0]);
        monsters = new Monster[][]{zombies,bandits,skeletons,giant_bats,draelic};
               
    }
    
    //Create Mosnters
    /** Create the zombies in the game     * 
     * @param zombies Array to allocate each zombie
     * @param zombies_pos Array with the initial positions
     */
	public Zombie[] create_zombies(Zombie[] zombies, double[][] zombies_pos) 
    		throws SlickException
    {	
    	zombies=new Zombie[zombies_pos.length];
    	int i=0;
    	for (double[] pos : zombies_pos){
    		zombies[i]= new Zombie(RPG.ASSETS_PATH + "/units/zombie.png", pos);
     		i++;        	       	
    	}
    	return zombies;
    }
	
    /** Create the skeletons in the game     
     * @param skeletons  Array to allocate each skeleton
     * @param skeletons _pos Array with the initial positions
     */
    public Skeleton[] create_skeletons(Skeleton[] skeletons, double[][] skeletons_pos) 
    		throws SlickException
    {	
    	skeletons = new Skeleton[skeletons_pos.length];
    	int i=0;
    	for (double[] pos : skeletons_pos){
    		skeletons[i]= new Skeleton(RPG.ASSETS_PATH + "/units/skeleton.png", pos);
     		i++;        	       	
    	}
    	return skeletons;
    }
    
    /** Creates the bandits in the game 
     * @param bandits Array to allocate each bandit
     * @param bandits_pos Array with the initial positions
     */
    public Bandit[] create_bandits(Bandit[] bandits, double[][] bandits_pos) 
    		throws SlickException
    {	
    	bandits = new Bandit[bandits_pos.length];
    	int i=0;
    	for (double[] pos : bandits_pos){
    		bandits[i]= new Bandit(RPG.ASSETS_PATH + "/units/bandit.png", pos);
     		i++;        	       	
    	}
    	return bandits;
    }
    
    /** Create the bats in the game     
     * @param giant_bats Array to allocate each bat
     * @param giant_bats_pos Array with the initial positions
     */
    public Giant_Bat[] create_bats(Giant_Bat[] giant_bats, double[][] giant_bats_pos) 
    		throws SlickException
    {	
    	giant_bats=new Giant_Bat[giant_bats_pos.length];
    	int i=0;
    	for (double[] pos : giant_bats_pos){
    		giant_bats[i]= new Giant_Bat(RPG.ASSETS_PATH + "/units/dreadbat.png", pos);
     		i++;        	       	
    	}
    	return giant_bats;
    }

    /** Render all the villagers in the world
     * @param villagers Array with all the villagers
     * @param g The Slick graphics object, used for drawing.
     */
    public void render_villagers(Villager villagers[], Graphics g) 
    {
    	for(Villager villager : villagers){
    		if (villager == null){
    			continue;
    		}
    		// Render only if is in the camera range
    		if((villager.getpos()[0] >= camera.getMaxX()) || (villager.getpos()[0] <= camera.getMinX())
        			|| (villager.getpos()[1] >= camera.getMaxY()) || (villager.getpos()[1] <= camera.getMinY()) ){
        			continue;
        	}
    		villager.render(g);
    	}
    }
    
    /** Render all the monsters in the world 
     * @param monster Array with all the monsters types
     * @param g The Slick graphics object, used for drawing.
     */
    public void render_monsters(Monster[][] monsters, Graphics g) 
    {
    	for (Monster[] monsters_type : monsters){
    		for(Monster monster : monsters_type){
    			if (monster == null){
    				continue;
    			}
    			// Checks if the current mosnter is dead
    			if (monster.is_dead()){
    				monster = null;
    				continue;
    			}
    			// Render only if is in the camera range
    			if((monster.getpos()[0] >= camera.getMaxX()) || (monster.getpos()[0] <= camera.getMinX())
    					|| (monster.getpos()[1] >= camera.getMaxY()) || (monster.getpos()[1] <= camera.getMinY()) ){
        				continue;
    			}
    			monster.render(g);
    		}
    	}
    }
    
    /** Updates the action of every monster in the world 
     * @param monsters Array with all the monsters types
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update_monsters(Monster[][] monsters,int delta){
    	for (Monster[] type: monsters){
    		for (Monster monster : type){
    			if (monster==null){
    				continue;
    			}
    			// Checks for a dead monster
        		if (monster.is_dead()){
        			monster = null;
        			continue;
        		}
    			monster.update(this, player, delta);
    		}
    	}
    }

    /** Render all the items in the world 
     * @param items Array with all the items 
     */
    public void render_items(Item items[]){
    	for (Item item: items){
    		if (item == null){
    			continue;
    		}
    		if (item.getStatus() == false){
    			item.render();
    			item = null;
    		}
    	}
    }
    

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @param attack Vulue that says when the player is attaking;
     */
    public void update(int dir_x, int dir_y, int delta, boolean attack, boolean talk)
    throws SlickException
    {
        player.update(this, dir_x, dir_y, delta, attack);        
        update_monsters(monsters,delta);
        camera.update();
      	elvira.interact(player, delta, talk);
       	garth.interact(player, delta, talk);
       	prince_aldric.interact(player,delta, talk);        
        book.collect(player);
        sword.collect(player);
        amulet.collect(player);
        elixir.collect(player);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        // Render the relevant section of the map
        int x = -(camera.getMinX() % getTileWidth());
        int y = -(camera.getMinY() % getTileHeight());
        int sx = camera.getMinX() / getTileWidth();
        int sy = camera.getMinY()/ getTileHeight();
        int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
        int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
        map.render(x, y, sx, sy, w, h);
        
        // Translate the Graphics object
        g.translate(-camera.getMinX(), -camera.getMinY());

        // Render Game Objects
        render_items(items);
        render_villagers(villagers, g);
        // Render the player
        player.render();        
        render_monsters(monsters,g);        
        renderPanel(g);
    }

    /** Determines whether a particular map coordinate blocks movement.
     * @param x Map x coordinate (in pixels).
     * @param y Map y coordinate (in pixels).
     * @return true if the coordinate blocks movement.
     */
    public boolean terrainBlocks(double x, double y)
    {
        // Check we are within the bounds of the map
        if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
            return true;
        }
        
        // Check the tile properties
        int tile_x = (int) x / getTileWidth();
        int tile_y = (int) y / getTileHeight();
        int tileid = map.getTileId(tile_x, tile_y, 0);
        String block = map.getTileProperty(tileid, "block", "0");
        return !block.equals("0");
    }
    

    /** Renders the player's status panel.
     * @param g The current Slick graphics context.
     */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item
        int panel_x, panel_y;

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel_x = (int)player.getpos()[0]-(int)(RPG.SCREEN_WIDTH/2.0);
        panel_y = (int)player.getpos()[1]+(int)((RPG.SCREEN_HEIGHT-RPG.PANELHEIGHT)/2.0);
        panel.draw(panel_x , panel_y);

        // Display the player's health
        text_x = panel_x + 15;
        text_y = panel_y + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = Integer.toString((int)player.getHealth())+ "/" + Integer.toString((int)player.getMaxHealth()); 

        bar_x = panel_x + 90;
        bar_y = panel_y + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = (float)(player.getHealth()/player.getMaxHealth());     
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = panel_x + 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = Integer.toString((int)player.getDamage());    
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = Integer.toString((int)player.getCD()); 
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", panel_x + 420, text_y);
        bar_x = panel_x  + 490;
        bar_y = panel_y + ((RPG.PANELHEIGHT - 72) / 2) + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        inv_x = panel_x  + 490;
        inv_y =  panel_y + ((RPG.PANELHEIGHT - 72) / 2);
        for (Item item : player.getInventory()) 
        {
        	if (item ==null){
        		continue;
        	}
        	item.setposition(inv_x+30,inv_y+RPG.PANELHEIGHT/2);
        	item.render();
            inv_x += 72;
        }
    }
}

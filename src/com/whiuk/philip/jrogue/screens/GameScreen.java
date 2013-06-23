package com.whiuk.philip.jrogue.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import com.whiuk.philip.jrogue.JRogueStateHandler;
import com.whiuk.philip.jrogue.Screen;
import com.whiuk.philip.jrogue.location.Direction;
import com.whiuk.philip.jrogue.location.Location;
import com.whiuk.philip.jrogue.location.World;
import com.whiuk.philip.jrogue.objects.ObjectType;
import com.whiuk.philip.jrogue.player.Player;
import com.whiuk.philip.jrogue.player.PlayerDeathHandler;

/**
 * 
 * @author Philip Whitehouse
 *
 */
public final class GameScreen extends Screen implements PlayerDeathHandler {
	/**
	 * 
	 */
    private static final Logger LOGGER =
    		Logger.getLogger(GameScreen.class.getName());
	/**
	 * 
	 */
	private static final int BORDER = 10;
	/**
	 * 
	 */
	private static final int PLAYER_INFO_WIDTH = 200;
	/**
	 * 
	 */
	private final JRogueStateHandler stateHandler;
	/**
	 * 
	 */
	private World world;
	/**
	 * 
	 */
	private Player player;
	/**
     * 
     */
	private Map map;
	/**
	 * 
	 */
	private PlayerInfo playerInfo;
	/**
	 * @param j JRogue
	 */
	public GameScreen(final JRogueStateHandler j) {
		this.stateHandler = j;
		LOGGER.info("Starting game");
		map = new Map(this);
		playerInfo = new PlayerInfo(this);
		world = new World();
		Location location = world.getStartLocation();
		System.out.println(location);
		System.out.println(location.getCreatureFactory());
		player = Player.newPlayer(location);
		player.setLocation(location);
		location.addCreature(player);
		player.moveWithoutCheck(
				location.getTile(world.getStartX(), world.getStartY()));		
	}
	/**
	 * 
	 */
	private int centerX;
	/**
	 * 
	 */
    private int centerY;
    /**
     * 
     */
    private int screenWidth;
    /**
     * 
     */
    private int screenHeight;

    /**
     * 
     * @return
     */
    public int getScrollX() {
        return Math.max(0, Math.min(centerX - screenWidth / 2,
        		player.getLocation().getWidth() - screenWidth));
    }
    /**
     * 
     * @return
     */
    public int getScrollY() {
        return Math.max(0, Math.min(centerY - screenHeight / 2,
        		player.getLocation().getHeight() - screenHeight));
    }
    

	/**
	 * Render the game.
	 * @param g 2D graphics
	 * @param w width
	 * @param h height
	 */
	@Override
	public void render(final Graphics2D g, final int w, final int h) {
		g.translate(BORDER + 5, BORDER + 5);
		map.render(g, w - PLAYER_INFO_WIDTH, h);
		g.translate(-(BORDER + 5), -(BORDER + 5));
		g.setColor(Color.red);
		g.drawRect(BORDER, BORDER, w - 2 * BORDER, h - 2 * BORDER);
		g.translate(w - PLAYER_INFO_WIDTH, 0);
		playerInfo.render(g, PLAYER_INFO_WIDTH, h);
		g.translate(-(w - PLAYER_INFO_WIDTH), 0);
	}
	/**
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	@Override
	public void handleKeyTyped(final char keyChar) {
		switch (keyChar) {
		case 'w':
			player.move(Direction.NORTH);
			break;
		case 'W':
			player.move(Direction.NORTH);
			break;
		case 'a':
			player.move(Direction.WEST);
			break;
		case 'A':
			player.move(Direction.WEST);
			break;
		case 's':
			player.move(Direction.SOUTH);
			break;
		case 'S':
			player.move(Direction.SOUTH);
			break;
		case 'd':
			player.move(Direction.EAST);
		break;
		case 'D':
			player.move(Direction.EAST);
		break;
		case '>':
			if (player.getTile().hasGameObject()
					&& player.getTile().getGameObject()
						.getType().equals(ObjectType.STAIRS_DOWN)) {
				player.descend();
			}
		break;
		case '<':
			if (player.getTile().hasGameObject()
					&& player.getTile().getGameObject()
						.getType().equals(ObjectType.STAIRS_UP)) {
				player.ascend();
			} 		
		break;
		case 'e':
			showHideEquipmentScreen();
		break;
		case 'E':
			showHideEquipmentScreen();
		break;
		default:
			break;
		}
	}
	private void showHideEquipmentScreen() {
		// TODO Auto-generated method stub
		
	}
	
	@Override public void handleDeath() {
		stateHandler.endGame(player);
	}
}
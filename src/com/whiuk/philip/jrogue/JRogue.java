package com.whiuk.philip.jrogue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.whiuk.philip.jrogue.player.Player;
import com.whiuk.philip.jrogue.screens.GameScreen;
import com.whiuk.philip.jrogue.screens.StartScreen;


/**
 * Swing implementation of application, provides a {@link JPanel}
 * that the game is rendered in.
 * 
 * @author Philip Whitehouse
 */
public class JRogue extends JPanel implements KeyListener, JRogueStateHandler {
	/**
	 * Inner class to handle menu bar actions
	 * if the application is set up with one.
	 * @author Philip Whitehouse
	 *
	 */
	public class JRogueMenuBar extends JMenuBar {
		/**
		 * Serialisation versioning identifier.
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * "File" menu for game manipulation.
		 */
		private JMenu fileMenu;
		/**
		 * "Window" related options menu.
		 */
		private JMenu windowMenu;
		/**
		 * General options menu.
		 */
		private JMenu optionsMenu;
		/**
		 * Help menu.
		 */
		private JMenu helpMenu;	
		/**
		 * Constructor.
		 */
		public JRogueMenuBar() {
			super();			
			buildFileMenu();
			buildWindowMenu();
			this.add(fileMenu);
			this.add(windowMenu);
		}
		/**
		 * Builds the file menu and it's items.
		 */
		private void buildFileMenu() {
			fileMenu = new JMenu("File");
			JMenuItem newMenuItem, openMenuItem, saveMenuItem, exitMenuItem;
			newMenuItem = new JMenuItem("New");
			newMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					newGame();
				}
				
			});
			openMenuItem = new JMenuItem("Open");
			openMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					openGame();
				}
				
			});
			saveMenuItem = new JMenuItem("Save");
			saveMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					saveGame();
				}
				
			});
			exitMenuItem = new JMenuItem("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					exit();
				}
				
			});
			fileMenu.add(newMenuItem);
			fileMenu.add(openMenuItem);
			fileMenu.add(saveMenuItem);
			fileMenu.add(exitMenuItem);
		}
		/**
		 * Builds the window menu and items.
		 */
		private void buildWindowMenu() {
			windowMenu = new JMenu("Window");
		}
	}
	/**
	 * Serialisation versioning identifier.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Game title.
	 */
	public static final String TITLE = "JRogue";
	/**
	 * Description.
	 */
	public static final String DESCRIPTION = "A Java-based Roguelike";
	/**
	 * Author.
	 */
	public static final String AUTHOR = "Philip Whitehouse";
	/**
	 * Version.
	 */
	public static final String VERSION = "0.1.0";
	/**
	 * Default width.
	 */
	private static final int WIDTH = 1024;
	/**
	 * Default height.
	 */
	private static final int HEIGHT = 768;
	/**
	 * Logger.
	 */
	private static final Logger LOGGER =
			Logger.getLogger(JRogue.class.getName());

	//TODO: Is java.util.Random good enough.
	//TODO: What about pre-calculated seed games.
	/**
	 * An instance to generate pseudo-random variables for the game.
	 */
	public static final Random RANDOM = new Random();
	
	/**
	 * Entry point.
	 * @param args Command line arguments
	 */
	public static void main(final String[] args) {
		JRogue rogue = new JRogue();
		JMenuBar menuBar = rogue.new JRogueMenuBar(); 
		JFrame frame = new JFrame(TITLE + " - v" + VERSION);
		frame.setJMenuBar(menuBar);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		frame.add(rogue);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addKeyListener(rogue);
	}

	/**
	 * The current state the application is in.
	 */
	private ApplicationState state;
	/**
	 * The width of the image used for the rendering buffer.
	 */
	private int bufferWidth;
	/**
	 * The height of the image used for the rendering buffer.
	 */
	private int bufferHeight;
	/**
	 * The image used for the rendering buffer.
	 */
	private transient Image bufferImage;
	/**
	 * The graphics context used for the rendering buffer.
	 */
	private transient Graphics bufferGraphics;
	/**
	 * The current screen to display.
	 * Note that we don't care for serialisation, hence it's declared transient.
	 */
	private transient Screen screen;

	/**
	 * Constructor.
	 */
	public JRogue() {
		state = ApplicationState.START;
		screen = new StartScreen(this);
		addKeyListener(this);
		requestFocusInWindow();
	}
	@Override
	public final void update(final Graphics g) {
        paint(g);
    }
	@Override
	public final void paint(final Graphics g) {
		if (bufferWidth != getSize().width
				|| bufferHeight != getSize().height
				|| bufferImage == null || bufferGraphics == null) {
		            resetBuffer();		
		}
		if (bufferGraphics != null) {
			//Clear the off-screen image
            bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);
            paintBuffer(bufferGraphics);
            //Paint the buffer onto the on-screen image
            g.drawImage(bufferImage, 0, 0, this);
		}
	}
	/**
	 * Paint the current buffer onto the window.
	 * @param g Graphics
	 */
	private void paintBuffer(final Graphics g) {
		LOGGER.info("Painting buffer");
		Graphics2D graphics2D = (Graphics2D) g;
        Map<?, ?> desktopHints =
                (Map<?, ?>) Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");

        Graphics2D g2d = (Graphics2D) g;
        if (desktopHints != null) {
            g2d.setRenderingHints(desktopHints);
        }
		render(graphics2D, bufferWidth, bufferHeight);
	}
	/**
	 * Set the buffer to the current size of the panel.
	 */
	private void resetBuffer() {
		bufferWidth = getWidth();
        bufferHeight = getHeight();

        // Clean up the previous image
        if (bufferGraphics != null) {
            bufferGraphics.dispose();
            bufferGraphics = null;
        }
        if (bufferImage != null) {
            bufferImage.flush();
            bufferImage = null;
        }
        // TODO: Was GCing here, not sure if necessary

        // Create the new image with the size of the panel
        bufferImage = createImage(bufferWidth, bufferHeight);
        bufferGraphics = bufferImage.getGraphics();
	}
	/**
	 * Render the screen.
	 * @param g 2D graphics
	 * @param w Width
	 * @param h Height
	 */
	private void render(final Graphics2D g, final int w, final int h) {
		if (screen != null) {
			screen.render(g, w, h);
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public final void keyTyped(final KeyEvent e) {
		if (screen != null) {
			screen.handleKeyTyped(e.getKeyChar());
		} else {
			throw new IllegalStateException();
		}
		repaint();
	}

	@Override
	public final void newGame() {
		if (state.equals(ApplicationState.GAME)) {
			//TODO: Game already in progress handling.
			return;
		}
		screen = new GameScreen(this);
		state = ApplicationState.GAME;
		repaint();
	}

	@Override
	public void openGame() {
		// TODO Opening games from game files.
		
	}

	@Override
	public void saveGame() {
		// TODO Saving games to files.
		
	}
	
	@Override
	public final void exit() {
		if (state.equals(ApplicationState.GAME)) {
			//TODO: Game currently in progress.
			return;
		}		
		// TODO Exit properly
		System.exit(0);
	}
	@Override
	public void keyPressed(final KeyEvent e) {
		// TODO Handle key press events.
		
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		// TODO Handle key release events.
		
	}
	
	/**
	 * 
	 * @param player
	 */
	public final void endGame(final Player player) {
		if (!state.equals(ApplicationState.GAME)) {
			throw new IllegalStateException();
		}
		// TODO Auto-generated method stub
		
	}
}

package main;

import naturesimulator.NatureSimulator; 
import project.Cell;
import project.Food;
import project.Snake;
import ui.ApplicationWindow;

import java.awt.*;


/**
 * The main class that can be used as a playground to test your project.
 * This class will be discarded and replaced with our own grading class.
 *
 * IMPORTANT: All the classes that you create should be put under the package named "project".
 * All the other packages will be reset when grading your project.
 */
public class Main {

	/**
	 * Main entry point for the application.
	 *
	 * IMPORTANT: You can change anything in this method to test your game,
	 * but your changes will be discarded when grading your project.
	 *
	 * @param args application arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				// Create game
				// You can change the world width and height, size of each grid square in pixels or the game speed
				NatureSimulator game = new NatureSimulator(25, 25, 20, 20);

				int x = (int) (Math.random() * game.getGridWidth());
				int y = (int) (Math.random() * game.getGridHeight());
				while(x<=5 &&x>=2&&y==1) {
					System.out.println(x+" "+y);
					x = (int) (Math.random() * game.getGridWidth());
					y = (int) (Math.random() * game.getGridHeight());
				}
				game.addCreature(new Snake(5,1,game));
				game.addCreature(new Food(x,y));
				// Create application window that contains the game panel
				ApplicationWindow window = new ApplicationWindow(game.getGamePanel());
				window.getFrame().setVisible(true);

				// Start game
				game.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}

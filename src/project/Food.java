package project;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * class that represents a blueprint for a food
 *
 */
public class Food extends Creature implements Drawable {

	/**
	 * constructs a food
	 * @param x the x coordinate of the food
	 * @param y the y coordinate of the food
	 * sets the initial health to 100.0
	 */
	public Food(int x, int y) {
		super(x, y, 100);

	}

	/**
	 * draws the pixel of the food
	 */
	public void draw(GridPanel panel) {
		panel.drawSquare(super.getX(), super.getY(), Color.GREEN);

	}

	/**
	 * 
	 * @return a point that represents the (x,y) coordinates of the food
	 */
	public Point getCoordinates() {
		return new Point(super.getX(), super.getY());
	}
	/**
	 * because the food class extends the abstract creature class, it has to implement the chooseAction method
	 */
	@Override
	public Action chooseAction(LocalInformation createLocalInformationForCreature) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * because the food class extends the abstract creature class, it has to implement the stay method
	 */
	@Override
	public void stay() {
		// TODO Auto-generated method stub

	}
	/**
	 * because the food class extends the abstract creature class, it has to implement the reproduce method
	 */
	@Override
	public Creature reproduce(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * because the food class extends the abstract creature class, it has to implement the move method
	 */
	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub

	}
	/**
	 * because the food class extends the abstract creature class, it has to implement the attack method
	 */
	@Override
	public void attack(Creature attackedCreature) {
		// TODO Auto-generated method stub

	}

}

package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;

/**
 * class that represents a blueprint for a cell
 *
 */
public class Cell extends Creature implements Drawable{

	/**
	 * constructs a cell
	 * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
	 */
	public Cell(int x, int y) {
		super(x, y);
	}
	/**
	 * constructs a cell
	 * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
	 * @param health the health of the cell
	 */
	public Cell(int x, int y, double health) {
		super(x,y, health);
	}
	/**
	 * because the cell class extends the abstract creature class, it has to implement the chooseAction method
	 */
	@Override
	public Action chooseAction(LocalInformation createLocalInformationForCreature) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * because the cell class extends the abstract creature class, it has to implement the stay method
	 */
	@Override
	public void stay() {
		// TODO Auto-generated method stub

	}
	/**
	 * because the cell class extends the abstract creature class, it has to implement the reproduce method
	 */
	@Override
	public Creature reproduce(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * because the cell class extends the abstract creature class, it has to implement the move method
	 */
	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub

	}
	/**
	 * because the cell class extends the abstract creature class, it has to implement the attack method
	 */
	@Override
	public void attack(Creature attackedCreature) {
		// TODO Auto-generated method stub

	}

}

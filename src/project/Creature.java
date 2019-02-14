package project;

import java.awt.Color;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * abstract class that is extended by snake, food, and cell classes.
 * 
 *
 */
public abstract class Creature implements Drawable{
	private int x;
	private int y;
	private double health;

	/**
	 * constructs a creature
	 * @param x the x coordinate of the creature
	 * @param y the y coordinate of the creature
	 * it sets the initial health to 100.0
	 */
	public Creature(int x, int y) {
		this.x=x;
		this.y=y;
		this.health=100;
	}
	/**
	 * constructs a creature
	 * @param x  the x coordinate of the creature
	 * @param y  the y coordinate of the creature
	 * @param health  the health of the creature
	 */
	public Creature(int x, int y, double health) {
		this.x=x;
		this.y=y;
		this.health=health;

	}

	/**
	 * draws the pixel of the creature
	 */
	public void draw(GridPanel panel) {
		panel.drawSquare(x, y, Color.BLACK);
	}

	/**
	 * getter for the health of the creature
	 * @return the health of the creature
	 */
	public double getHealth() {
		return this.health;
	}

	/**
	 * sets the health of the creature to the given value
	 * @param health the given value to be assigned to the health of the creature.
	 * guarantees that the health of the creature cannot be minus
	 */
	public void setHealth(double health) {
		this.health=health;
		if(this.health<0.0) this.health=0.0;
	}
	/**
	 * getter for the x coordinate of the creature
	 * @return the x coordinate of the creature
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * sets the x coordinate of the creature to the given value
	 * @param x the given value to be assigned to the x coordinate of the creature
	 */
	public void setX(int x) {
		this.x=x;
	}
	/**
	 * getter for the y coordinate of the creature
	 * @return the y coordinate of the creature
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * sets the y coordinate of the creature to the given value
	 * @param y the given value to be assigned to the y coordinate of the creature
	 */
	public void setY(int y) {
		this.y=y;
	}

	/**
	 * abstract method that compels child classes to implement chooseAction method
	 * @param createLocalInformationForCreature information about the current position of the creature
	 * @return the action type that is going to be executed by the creature
	 */
	public abstract Action chooseAction(LocalInformation createLocalInformationForCreature);

	/**
	 * abstract method that compels child classes to implement stay method.
	 */
	public abstract void stay();

	/**
	 * abstract method that compels child classes to implement reproduce method
	 * @param direction the direction to which the creature reproduces
	 * @return the creature that is the product of the reproduction
	 */
	public abstract Creature reproduce(Direction direction);

	/**
	 * abstract method that compels child classes to implement move method
	 * @param direction the direction to which the creature moves
	 */
	public abstract void move(Direction direction);

	/**
	 * abstract method that compels child classes to implement attack method
	 * @param attackedCreature the creature that is going to be attacked by this creature
	 */
	public abstract void attack(Creature attackedCreature);

	/**
	 *  
	 * @param attackedCreature the creature to be attacked by another creature
	 * @param createLocalInformationForCreature the information about the environment of the creature
	 */
	public void attack(Creature attackedCreature, LocalInformation createLocalInformationForCreature) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * @param direction the direction to be moved to by the creature
	 * @param creaturesMap the map that stores the existing creatures in the game
	 */
	public void move(Direction direction, Creature[][] creaturesMap) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * @param direction to be reproduced to by the creature
	 * @param creaturesMap the map that stores the existing creatures in the game
	 * @return a new creature that is the product of reproduction
	 */
	public Creature reproduce(Direction direction, Creature[][] creaturesMap) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @param creaturesMap the map that stores the existing creatures in the game
	 * @return a new creature that is the product of reproduction
	 */
	public Creature reproduce(Creature[][] creaturesMap) {
		// TODO Auto-generated method stub
		return null;
	}
}

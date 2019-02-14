package project;

import java.awt.Color; 
import java.awt.Point;
import java.util.*;
import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.NatureSimulator;
import ui.GridPanel;

/**
 *class that represents a blueprint for a snake 
 *
 */
public class Snake extends Creature {
	private LinkedList<Cell> head=new LinkedList<>();
	NatureSimulator game; 

	/**
	 * constructs a snake
	 * @param head the linkedlist that represents the body of the snake that is going to reproduce
	 * @param game the naturesimulator that represents the game
	 * it is used to construct a snake by reproduction
	 */
	public Snake(LinkedList<Cell> head, NatureSimulator game) {
		super(0,0);
		this.head.add(head.pollLast());
		this.head.add(head.pollLast());
		this.head.add(head.pollLast());
		this.head.add(head.pollLast());
		this.setX(this.head.get(0).getX()); // sets the coordinates of the head
		this.setY(this.head.get(0).getY()); // of the body of the snake to the snake's field coordinates
		this.game=game;
	}
	/**
	 * 
	 * @param x the x coordinate of the snake
	 * @param y the y coordinate of the snake 
	 * @param game the naturesimulator that represents the game
	 */
	public Snake(int x, int y, NatureSimulator game) {
		super(x, y);
		head.add(new Cell(x,y));
		head.add(new Cell(x-1,y));
		head.add(new Cell(x-2,y));
		head.add(new Cell(x-3,y));
		this.setX(this.head.get(0).getX());
		this.setY(this.head.get(0).getY());
		this.game=game;
	}
	/**
	 * draws the pixels of the body of the snake. if index is 0(head), draws a red pixel, else draws a blue pixel
	 */
	@Override
	public void draw(GridPanel panel) {
		for(int i=0; i<this.head.size();i++) {
			if(i==0) {
				panel.drawSquare(this.head.get(i).getX(), this.head.get(i).getY(), Color.red);
			}
			else panel.drawSquare(this.head.get(i).getX(), this.head.get(i).getY(), Color.blue);
		}

	}
	/**
	 * @param createLocalInformationForCreature information about the environment of the snake
	 * @return the action type that is going to be executed by the snake
	 * choosing algorithm is in accordance with action choice order for a snake
	 */
	@Override
	public Action chooseAction(LocalInformation createLocalInformationForCreature) {
		if(this.head.size()==8) {
			return new Action(Action.Type.REPRODUCE);
		}
		else if(this.getAttackedCreatureDirectionForAttack(this.getAttackedCreature(createLocalInformationForCreature)) instanceof Direction &&
				this.getAttackedCreature(createLocalInformationForCreature) instanceof Food) {
			return new Action(Action.Type.ATTACK, this.getAttackedCreatureDirectionForAttack(this.getAttackedCreature(createLocalInformationForCreature)));
		}
		else if(this.getAttackedCreatureDirectionForMove(createLocalInformationForCreature) instanceof Direction) {
			return new Action(Action.Type.MOVE, this.getAttackedCreatureDirectionForMove(createLocalInformationForCreature));
		}
		else {
			return new Action(Action.Type.STAY);
		}
	}
	/**
	 * because the snake class extends the creature class, it has to implement the stay method
	 */
	@Override
	public void stay() {

	}
	/**
	 * @param creaturesMap the map that stores the existing creatures in the game
	 * @return a new snake that is the product of the reproduction
	 */
	@Override
	public Creature reproduce(Creature[][] creaturesMap) {	
		for(int i=4; i<8; i++) {
			creaturesMap[this.head.get(i).getX()][this.head.get(i).getY()] = null;
		}
		return new Snake(this.head,this.game);
	}
	/**
	 * @param direction the direction that is going to be moved to by the snake
	 * @param creaturesMap the map that stores the existing creatures in the game
	 * according to direction adds a new cell to the beginning of the body of the snake, then removes the last cell
	 * from the creaturesMap, then removes the last cell from the body of the snake
	 */
	@Override
	public void move(Direction direction, Creature[][] creaturesMap) {
		if(direction.equals(Direction.UP)) {
			this.head.add(0, new Cell(this.head.get(0).getX(), this.head.get(0).getY()-1));
			this.game.removeCreature(this.head.getLast());
			this.head.removeLast();
		}
		else if(direction.equals(Direction.DOWN)) {
			this.head.add(0, new Cell(this.head.get(0).getX(), this.head.get(0).getY()+1));
			this.game.removeCreature(this.head.getLast());
			this.head.removeLast();
		}
		else if(direction.equals(Direction.LEFT)) {
			this.head.add(0, new Cell(this.head.get(0).getX()-1, this.head.get(0).getY()));
			this.game.removeCreature(this.head.getLast());
			this.head.removeLast();
		}
		else if(direction.equals(Direction.RIGHT)) {
			this.head.add(0, new Cell(this.head.get(0).getX()+1, this.head.get(0).getY()));
			this.game.removeCreature(this.head.getLast());
			this.head.removeLast();
		}	
		this.setX(this.head.get(0).getX()); 
		this.setY(this.head.get(0).getY());

	}
	/**
	 * @param attackedCreature the creature that is going to be attacked by the snake
	 * @param createLocalInformationForCreature the information about the environment of the snake
	 * adds a new cell with the coordinates of the attackedCreature to the beginning of the body of the snake
	 * sets a random point(control if it is empty) for the food to make the food appear another point
	 */
	public void attack(Creature attackedCreature, LocalInformation createLocalInformationForCreature) {
		int x = attackedCreature.getX();
		int y = attackedCreature.getY();
		this.head.add(0, new Cell(x, y));
		this.setX(this.head.get(0).getX());
		this.setY(this.head.get(0).getY());
		int q = (int) (Math.random() * createLocalInformationForCreature.getGridWidth());
		int z = (int) (Math.random() * createLocalInformationForCreature.getGridWidth());
		while(!this.game.isPositionFree(q, z)) {
			q = (int) (Math.random() * createLocalInformationForCreature.getGridWidth());
			z = (int) (Math.random() * createLocalInformationForCreature.getGridWidth());
		}
		attackedCreature.setX(q);
		attackedCreature.setY(z);
	}

	/**
	 *	 returns a direction for the snake to move 
	 * @param createLocalInformationForCreature the information about the environment of the snake
	 * @return a direction for the snake to move to get closed to the food
	 * checks x coordinates first, then if y coordinates can be equalized, returns a direction for the snake
	 * to get closed to the food vertically. if not possible, returns a direction for the snake to get closed to
	 * the food horizontally. if not possible to, returns a random direction
	 */
	public Direction getAttackedCreatureDirectionForMove(LocalInformation createLocalInformationForCreature) {
		int x=this.getAttackedCreature(createLocalInformationForCreature).getX();
		int y=this.getAttackedCreature(createLocalInformationForCreature).getY();
		if(this.head.get(0).getX()==x) {
			if(this.head.get(0).getY()>y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.UP)) {
				return Direction.UP;
			}
			else if(this.head.get(0).getY()<y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.DOWN)) {
				return Direction.DOWN;
			}
			else return LocalInformation.getRandomDirection(createLocalInformationForCreature.getFreeDirections());

		}
		if(this.head.get(0).getX()>x) {
			if(this.head.get(0).getY()>y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.UP)) {
				return Direction.UP;
			}
			else if(this.head.get(0).getY()<y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.DOWN)) {
				return Direction.DOWN;
			}
			else if(this.head.get(0).getY()==y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.LEFT)) {
				return Direction.LEFT;
			}
			else return LocalInformation.getRandomDirection(createLocalInformationForCreature.getFreeDirections());

		}
		if(this.head.get(0).getX()<x) {
			if(this.head.get(0).getY()>y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.UP)) {
				return Direction.UP;
			}
			else if(this.head.get(0).getY()<y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.DOWN)) {
				return Direction.DOWN;
			}
			else if(this.head.get(0).getY()==y&&createLocalInformationForCreature.getFreeDirections().contains(Direction.RIGHT)) {
				return Direction.RIGHT;
			}
			else return LocalInformation.getRandomDirection(createLocalInformationForCreature.getFreeDirections());

		}
		if(createLocalInformationForCreature.getFreeDirections().isEmpty()) { // constructs a list containing all possible directions
			ArrayList<Direction> possible=new ArrayList<>();
			possible.add(Direction.DOWN);
			possible.add(Direction.LEFT);
			possible.add(Direction.RIGHT);
			possible.add(Direction.UP);
			Direction d =LocalInformation.getRandomDirection(possible);
			while(!this.game.isDirectionFree(this.getX(), this.getY(), d)); // checks if the direction is free. if not creates another direction until it is free
			d = LocalInformation.getRandomDirection(possible);
			return d;
		}

		return LocalInformation.getRandomDirection(createLocalInformationForCreature.getFreeDirections());
	}		
	/**
	 * returns a direction for the snake to attack
	 * @param attackedCreature the creature that is going to be attacked by the snake
	 * @return the direction of the attacked creature with respect to the snake, attacker. 
	 */
	public Direction getAttackedCreatureDirectionForAttack(Creature attackedCreature) {
		int x=attackedCreature.getX();
		int y=attackedCreature.getY();
		if(this.head.get(0).getX()==x-1&& this.head.get(0).getY()==y) {
			return Direction.RIGHT;
		}
		else if(this.head.get(0).getX()==x+1&& this.head.get(0).getY()==y) {
			return Direction.LEFT;
		}
		else if(this.head.get(0).getY()==y+1&& this.head.get(0).getX()==x) {
			return Direction.UP;
		}
		else if(this.head.get(0).getY()==y-1&& this.head.get(0).getX()==x) {
			return Direction.DOWN;
		}
		else return null;
	}

	/**
	 * 
	 * @param createLocalInformationForCreature the information about the environment for the snake
	 * @return a creature to be attacked by the snake
	 * gets the food from the localinformation class
	 */
	public Creature getAttackedCreature(LocalInformation createLocalInformationForCreature) {
		return createLocalInformationForCreature.getFood();
	}
	/**
	 * 
	 * @return the linkedlist that represents the body of the snake
	 */
	public LinkedList<Cell> getHead(){
		return this.head;
	}
	/**
	 * 
	 * @param createLocalInformationForCreature the information about the environment of the snake
	 * @return the gridheight of the game
	 */
	public int getGridHeight(LocalInformation createLocalInformationForCreature){
		return createLocalInformationForCreature.getGridHeight();
	}
	/**
	 * 
	 * @param createLocalInformationForCreature the information about the environment of the snake
	 * @return the gridwidth of the game
	 */
	public int getGridWidth(LocalInformation createLocalInformationForCreature) {
		return createLocalInformationForCreature.getGridWidth();
	}
	/**
	 * because the snake class extends the creature class, it has to implement the attack method
	 */
	@Override
	public void attack(Creature attackedCreature) {

	}
	/**
	 * because the snake class extends the creature class, it has to implement the move method
	 */
	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub

	}
	/**
	 * because the snake class extends the creature class, it has to implement the reproduce method
	 */
	@Override
	public Creature reproduce(Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}
}

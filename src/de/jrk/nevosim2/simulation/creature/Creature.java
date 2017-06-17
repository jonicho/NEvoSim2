package de.jrk.nevosim2.simulation.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import de.jrk.nevosim2.util.Vector;

public class Creature implements Serializable {
	private static final long serialVersionUID = 8246894358517353395L;
	private Vector pos = new Vector();
	private double size = 0.1;
	private Color color;
	private Brain brain = new Brain();
	private double direction = 0;
	private double speed = 0;
	private double energy = 0;
	private boolean alive = true;

	public Creature(Vector pos) {
		energy = 150;
		color = Color.RED;
		this.pos = pos;
	}

	public void update() {
		updateBrain();

		pos.add(new Vector(Math.sin(Math.toRadians(direction)) * speed,
				Math.cos(Math.toRadians(direction)) * speed));
		
		if (energy < 100) {
			alive = false;
		}
	}

	private void updateBrain() {
		brain.setEnergy(energy);

		brain.calculate();
		direction += brain.getRotation();
		speed = brain.getSpeed() / 1000;
	}

	public void draw(Graphics g, int screenSize) {
		size = 0.01;
		g.setColor(color);
		int x = (int) ((pos.getX()) * screenSize);
		int y = (int) ((pos.getY()) * screenSize);
		g.fillOval(x, y, (int) (size * screenSize), (int) (size * screenSize));
	}
	
	public boolean isAlive() {
		return alive;
	}
}

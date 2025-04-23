package assignment9;

import java.awt.Color;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		Random rand = new Random();
        // Make sure food stays fully on-screen (within bounds)
        this.x = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * rand.nextDouble();
        this.y = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * rand.nextDouble();
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE / 2);
	}
	public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Reposition the food to a new random location
     */
    public void relocate() {
        Random rand = new Random();
        this.x = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * rand.nextDouble();
        this.y = FOOD_SIZE + (1.0 - 2 * FOOD_SIZE) * rand.nextDouble();
    }
	
}

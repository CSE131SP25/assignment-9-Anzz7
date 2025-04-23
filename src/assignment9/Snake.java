package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int score; //Add score field
	
	public Snake() {
		segments = new LinkedList<>();
        // Start snake in the middle of the screen
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
		score = 0; //initialize score
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		if (deltaX == 0 && deltaY == 0) return;

        // Get the current head's position
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;

        // Add new head
        BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
        segments.addFirst(newHead);

        // Remove last segment unless we just ate
        segments.removeLast();
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment segment : segments) {
            segment.draw();
        }
	}
	
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();  // Get the head of the snake
	    double x = head.getX();                  // Get the x-coordinate of the head
	    double y = head.getY();                  // Get the y-coordinate of the head

	    // Check if the head's coordinates are within the window bounds (0 to 1)
	    return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully.
	 * If the snake's head position matches the food's position, it eats the food,
	 * grows by adding a new body segment, and increments the score.
	 * 
	 * @param food The food to be eaten by the snake
	 * @return true if the snake successfully ate the food, false otherwise
	 */
	public boolean eat(Food food) {
		// Get the head of the snake (the first body segment)
	    BodySegment head = segments.getFirst();

	    // Allow for a small tolerance for collision detection
	    double tolerance = SEGMENT_SIZE / 2 + Food.FOOD_SIZE / 2;
	    if (Math.abs(head.getX() - food.getX()) < tolerance && Math.abs(head.getY() - food.getY()) < tolerance) {
	        // Snake eats the food: Increase score (for example, +10)
	        score += 10;

	        // Add a new body segment to the tail of the snake
	        BodySegment tail = segments.getLast();  // Get the last segment (the tail)
	        
	        // Add a new body segment at the same position as the tail
	        segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));

	        return true;  // Food was successfully eaten
	    }

	    return false;  // Food was not eaten (no collision)
    }
	
	/**
	 * Returns the current score of the game.
	 * The score increases when the snake eats food.
	 * 
	 * @return the current score
	 */
    // Method to get the current score
    public int getScore() {
        return score;
    }
}

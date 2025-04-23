package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
	private Snake snake;
    private Food food;
    
	public Game() {
		StdDraw.enableDoubleBuffering();
		
		// Initialize Snake and Food objects
        snake = new Snake();
        food = new Food();
	}
	
	/**
     * Displays the intro screen with a title and instructions.
     */
    private void displayIntro() {
        StdDraw.clear(); // Clear the screen before drawing the intro
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(StdDraw.getFont().deriveFont(30f)); // Set the font size for title
        StdDraw.text(0.5, 0.7, "Welcome to Snake!"); // Display title
        StdDraw.setFont(StdDraw.getFont().deriveFont(20f)); // Set smaller font size for instructions
        StdDraw.text(0.5, 0.5, "Use WASD to control the snake");
        StdDraw.text(0.5, 0.4, "Press Enter to Start");
        StdDraw.show(); // Show the drawing
        while (!StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
            // Wait for the user to press Enter to start the game
        }
        StdDraw.clear(); // Clear the intro screen before starting the game
    }
	
	public void play() {
		displayIntro(); // Display intro screen
		
		while (snake.isInbounds()) { // Game continues until the snake is out of bounds
			int dir = getKeypress();
			if (dir != -1) {
                snake.changeDirection(dir); // Update direction based on key press
            }

            // Move the snake
            snake.move();

            // Check if the snake ate the food
            if (snake.eat(food)) {
                food = new Food(); // Create new food at random location
            }

            // Update drawing
            updateDrawing();
            
            // Add a small delay for smooth gameplay
            try {
                Thread.sleep(100); // 50 ms delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		// Game over, snake is out of bounds
        System.out.println("Game Over! Snake went out of bounds.");		
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;//up
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;//down
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;//left
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;//right
		} else {
			return -1;//no key press
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear(); // Clear the screen
        snake.draw();    // Draw the snake
        food.draw();     // Draw the food
     // Draw the score at the top left corner
        StdDraw.setPenColor(StdDraw.BLACK);  // Set color for score text
        StdDraw.text(0.1, 0.95, "Score: " + snake.getScore());  // Display score
        
        StdDraw.show();  // Show the updated drawing
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}

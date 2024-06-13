//Nadav Menirav 330845678

import biuoop.GUI;

import biuoop.DrawSurface;

import biuoop.Sleeper;

import java.awt.Color;

/**
 * Game class.
 */
public class Game {
    //Fields of the Game class
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;

    /**
     * Empty constructor of the Game class.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new GUI("Nice", 800, 600);
    }

    /**
     * This method adds a Collidable to the GameEnvironment.
     * @param c The Collidable we add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * This method adds a Sprite to the SpriteCollection.
     * @param s the Sprite we add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This method initializes the game: creates new blocks and a ball, and adds them to this game.
     */
    public void initialize() {
        Ball ball = new Ball(new Point(500, 100), 30, Color.BLACK);
        ball.setVelocity(1, 3);
        ball.addToGame(this);
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 50);
        Rectangle rectangle2 = new Rectangle(new Point(0, 550), 800, 50);
        Rectangle rectangle3 = new Rectangle(new Point(0, 0), 50, 600);
        Rectangle rectangle4 = new Rectangle(new Point(750, 0), 50, 600);
        Rectangle rectangle5 = new Rectangle(new Point(100, 100), 200, 150);
        Rectangle rectangle6 = new Rectangle(new Point(350, 200), 200, 150);
        Rectangle rectangle7 = new Rectangle(new Point(600, 300), 150, 200);
        Block block = new Block(rectangle);
        Block block2 = new Block(rectangle2);
        Block block3 = new Block(rectangle3);
        Block block4 = new Block(rectangle4);
        Block block5 = new Block(rectangle5);
        Block block6 = new Block(rectangle6);
        Block block7 = new Block(rectangle7);
        block.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
        block5.addToGame(this);
        block6.addToGame(this);
        block7.addToGame(this);
        ball.addToGame(this);
    }

    /**
     * This method runs the game -- starts the animation loop.
     */
    public void run() {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (true) {
            long startTime = System.currentTimeMillis(); //Timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            //Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


}
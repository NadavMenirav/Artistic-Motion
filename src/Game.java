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
     * Getter of the environment field.
     * @return The GameEnvironment of this Game
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Getter of the gui field.
     * @return The GUI of this Game
     */
    public GUI getGui() {
        return gui;
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
     * This method initializes the game: creates new blocks, a ball and a paddle, and adds them to this game.
     */
    public void initialize() {
        Ball firstBall = new Ball(new Point(500, 100), 8, Color.WHITE);
        firstBall.setVelocity(2, 3);
        Ball secondBall = new Ball(new Point(600, 100), 8, Color.WHITE);
        secondBall.setVelocity(2, 3);
        Paddle paddle = new Paddle();
        paddle.addToGame(this);

        //The blocks on the edges of the screen
        this.addEdgesBlocks();



    }

    /**
     * Adds The blocks on the edges of the screen to this game.
     */
    public void addEdgesBlocks() {
        Rectangle rectangle = new Rectangle(new Point(0, -50), 800, 50);
        Rectangle rectangle2 = new Rectangle(new Point(0, 600), 800, 50);
        Rectangle rectangle3 = new Rectangle(new Point(-50, 0), 50, 600);
        Rectangle rectangle4 = new Rectangle(new Point(800, 0), 50, 600);

        Block block = new Block(rectangle);
        Block block2 = new Block(rectangle2);
        Block block3 = new Block(rectangle3);
        Block block4 = new Block(rectangle4);

        block.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);

        addRowBlocks(12, 80);
        addRowBlocks(11, 105);
        addRowBlocks(10, 130);
        addRowBlocks(9, 155);
        addRowBlocks(8, 180);
        addRowBlocks(7, 205);
    }

    /**
     * This method adds to the game a row of blocks to this Game.
     * @param numberOfBlocks The number of blocks we want to add
     * @param yValueOfBlocks The y value of all the blocks we want to add
     */
    public void addRowBlocks(int numberOfBlocks, double yValueOfBlocks) {
        for (int i = 0; i < numberOfBlocks; i++) {
            Rectangle rectangle = new Rectangle(new Point(750 - 50 * i, yValueOfBlocks), 50, 25);
            Block block = new Block(rectangle);
            block.addToGame(this);
        }
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
//Nadav Menirav 330845678

package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.hitlisteners.BallRemover;
import game.hitlisteners.BlockRemover;
import game.hitlisteners.ScoreTrackingListener;
import game.miscellaneous.Counter;
import game.miscellaneous.GameEnvironment;
import game.miscellaneous.ScoreIndicator;
import game.miscellaneous.SpriteCollection;
import game.objects.Ball;
import game.objects.Block;
import game.objects.Collidable;
import game.objects.Paddle;
import game.objects.Sprite;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Game class.
 */
public class Game {
    //Fields of the Game class
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private Paddle paddle;
    private final Counter blockCounter;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final Counter ballCounter;
    private final Counter score;
    private final ScoreTrackingListener scoreTrackingListener;

    /**
     * Empty constructor of the Game class.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        gui = new GUI("Arkanoid", 800, 600);
        this.paddle = null;
        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);

        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, this.ballCounter);

        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
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
     * Getter of the paddle field.
     * @return The Paddle of this game
     */
    public Paddle getPaddle() {
        return paddle;
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
     * The method removes a collidable.
     * @param c The collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method removes a Sprite.
     * @param s The sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method initializes the game: creates new blocks, a ball and a paddle, and adds them to this game.
     */
    public void initialize() {
        //Create the two balls
        Ball firstBall = new Ball(new Point(700, 500), 6, Color.WHITE);
        firstBall.setVelocity(3, 2);
        Ball secondBall = new Ball(new Point(600, 400), 6, Color.WHITE);
        secondBall.setVelocity(2, -3);
        Ball thirdBall = new Ball(new Point(500, 500), 6, Color.WHITE);
        thirdBall.setVelocity(3, 2);
        this.ballCounter.increase(3);

        //Create the Paddle
        Paddle pad = new Paddle(new Color(255, 200, 0, 255));
        this.paddle = pad;

        //Add the background block
        this.addBackgroundBlock();
        //Add paddle and balls
        pad.addToGame(this);
        firstBall.addToGame(this);
        secondBall.addToGame(this);
        thirdBall.addToGame(this);
        //Add rest of blocks
        this.addAllBlocks();
    }

    /**
     * Adds the background Block to this game.
     */
    public void addBackgroundBlock() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 600);
        Block block = new Block(rectangle, new Color(48, 132, 20));
        block.addHitListener(this.ballRemover);
        block.addToGame(this);
    }

    /**
     * Adds all the blocks to this game.
     */
    public void addAllBlocks() {
        //Add edge blocks and the score rectangle
        Rectangle rectangle = new Rectangle(new Point(0, 20), 800, 25);
        Rectangle rectangle3 = new Rectangle(new Point(0, 0), 25, 600);
        Rectangle rectangle4 = new Rectangle(new Point(775, 0), 25, 800);

        Block block = new Block(rectangle, Color.GRAY);
        Block block3 = new Block(rectangle3, Color.GRAY);
        Block block4 = new Block(rectangle4, Color.GRAY);

        block.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);

        //Add score block
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 800, 20);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, rectangle2);
        this.addSprite(scoreIndicator);

        //We want to add the color blocks on the rows
        addRowBlocks(11, 130, Color.GRAY);
        addRowBlocks(10, 160, Color.RED);
        addRowBlocks(9, 190, Color.YELLOW);
        addRowBlocks(8, 220, Color.BLUE);
        addRowBlocks(7, 250, Color.WHITE);
    }

    /**
     * This method adds to the game a row of blocks to this Game.
     * @param numberOfBlocks The number of blocks we want to add
     * @param yValueOfBlocks The y value of all the blocks we want to add
     * @param color The Color of the row blocks
     */
    public void addRowBlocks(int numberOfBlocks, double yValueOfBlocks, Color color) {
        /*The loop create a certain amount of the blocks, it does so by moving the x value to the left the value of the
         * width in each iteration
         */
        for (int i = 0; i < numberOfBlocks; i++) {
            Rectangle rectangle = new Rectangle(new Point(725 - 50 * i, yValueOfBlocks), 50, 30);
            Block block = new Block(rectangle, color);
            block.addHitListener(this.scoreTrackingListener);
            block.addHitListener(this.blockRemover);
            block.addToGame(this);
            blockCounter.increase(1);
        }
    }

    /**
     * This method runs the game -- starts the animation loop.
     */
    public void run() {
        final int framesPerSecond = 60;
        final int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        //This loop runs the game
        while (true) {
            long startTime = System.currentTimeMillis(); //Timing

            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100);
                gui.close();
                return;
            }

            if (this.ballCounter.getValue() == 0) {
                gui.close();
                return;
            }

            DrawSurface d = gui.getDrawSurface();
            //Now we draw the Sprites
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
//Nadav Menirav 330845678

/**
 * Game class.
 */
public class Game {
    //Fields of the Game class
    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    /**
     * Empty constructor of the Game class.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
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

    public void initialize() {

    }


}
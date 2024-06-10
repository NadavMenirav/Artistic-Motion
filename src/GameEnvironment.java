//Nadav Menirav 330845678
import java.util.List;
import java.util.ArrayList;
/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    //Fields of the GameEnvironment class
    private ArrayList<Collidable> collidables;

    /**
     * Constructor of the GameEnvironment class.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     * @param c The collidable we add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c); //Encapsulation?
    }
}

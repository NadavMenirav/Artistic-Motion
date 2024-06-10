//Nadav Menirav 330845678
import java.util.List;
import java.util.ArrayList;
/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    //Fields of the GameEnvironment class
    private final ArrayList<Collidable> collidables;

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

    /**
     * This function finds the closest collision between the object moving on the trajectory and the Collidables
     * in this environment.
     * @param trajectory The trajectory of the moving object
     * @return CollisionInfo of the closest collision if there is any, null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionInfos = new ArrayList<>();
        Point collisionPoint;
        double minLength = Double.MAX_VALUE;
        CollisionInfo collisionInfo, finalCollisionInfo = null;

        for (Collidable collidable : this.collidables) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                collisionInfo = new CollisionInfo(collisionPoint, collidable);
                collisionInfos.add(collisionInfo);
            }
        }

        for (CollisionInfo collisionInfo2 : collisionInfos) {
            collisionPoint = collisionInfo2.collisionPoint();
            if (collisionPoint.distance(trajectory.start()) < minLength) {
                finalCollisionInfo = collisionInfo2;
            }
        }
        return finalCollisionInfo;
    }
}

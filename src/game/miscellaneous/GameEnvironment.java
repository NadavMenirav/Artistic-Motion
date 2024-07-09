//Nadav Menirav 330845678

package game.miscellaneous;

import java.util.List;
import java.util.ArrayList;

import game.objects.Collidable;
import geometry.Line;
import geometry.Point;


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
        this.collidables.add(c);
    }

    /**
     * The method removes a collidable from this Environment.
     * @param c The collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This function finds the closest collision between the object moving on the trajectory and the Collidables
     * in this environment.
     * @param trajectory The trajectory of the moving object
     * @return CollisionInfo of the closest collision if there is any, null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minLength = Double.MAX_VALUE;
        List<CollisionInfo> collisionInfos = new ArrayList<>();
        Point collisionPoint;
        CollisionInfo collisionInfo, finalCollisionInfo = null;

        //This loop adds all the collisionInfos to the array.
        for (Collidable collidable : this.collidables) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                collisionInfo = new CollisionInfo(collisionPoint, collidable);
                collisionInfos.add(collisionInfo);
            }
        }

        //Finding the closest collision
        for (CollisionInfo collisionInfo2 : collisionInfos) {
            collisionPoint = collisionInfo2.collisionPoint();
            if (collisionPoint.distance(trajectory.start()) < minLength) {
                finalCollisionInfo = collisionInfo2;
                minLength = collisionPoint.distance(trajectory.start());
            }
        }
        return finalCollisionInfo;
    }
}

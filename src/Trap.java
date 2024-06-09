/**
 * Trap class.
 * (we will spawn balls which are called TrappedBalls, they are going to be trapped inside the Trap)
 */
public class Trap {
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    /**
     * constructor.
     * @param topLeft topLeft point of trap
     * @param width width of trap
     * @param height height of trap
     */
    public Trap(Point topLeft, int width, int height) {
        this.topLeft = new Point(topLeft);
        this.topRight = new Point(topLeft.getX() + width, topLeft.getY());
        this.bottomLeft = new Point(topLeft.getX(), topLeft.getY() + height);
        this.bottomRight = new Point(topLeft.getX() + width, topLeft.getY() + height);
    }
    /**
     * constructor.
     * @param topLeft topLeft point of trap
     * @param bottomRight bottomRight point of trap
     */
    public Trap(Point topLeft, Point bottomRight) {
        this(
            topLeft,
            (int) (bottomRight.getX() - topLeft.getX()),
            (int) (bottomRight.getY() - topLeft.getY())
        );
    }
    /**
     * constructor.
     * @param trap
     */
    public Trap(Trap trap) {
        this(trap.getTopLeft(), trap.getWidth(), trap.getHeight());
    }
    /**
     * finds the width trap.
     * @return width of trap
     */
    public int getWidth() {
        return (int) (this.topRight.getX() - this.topLeft.getX());
    }
    /**
     * finds height of trap.
     * @return height of trap
     */
    public int getHeight() {
        return (int) (this.bottomLeft.getY() - this.topLeft.getY());
    }
    /**
     * returns topLeft point.
     * @return topLeft point
     */
    public Point getTopLeft() {
        return new Point(this.topLeft);
    }
    /**
     * returns the bottomRight point.
     * @return the bottomRight point
     */
    public Point getBottomRight() {
        return new Point(this.bottomRight);
    }
}
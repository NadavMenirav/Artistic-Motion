import java.util.Random;
import java.awt.Color;
import biuoop.DrawSurface;
/**
 * TrappedBall class.
 */
public class TrappedBall {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Trap trap;
    /**
     * sets velocity.
     * @param velocity new velocity
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = new Velocity(velocity);
    }
    /**
     * random constructor.
     * @param r radius of ball
     * @param color color of ball
     * @param trap trap of ball
     */
    public TrappedBall(int r, Color color, Trap trap) {
        Random random = new Random();
        this.radius = r;
        this.color = new Color(color.getRGB());
        this.velocity = new Velocity();
        this.trap = new Trap(trap);
        this.center = new Point(random.nextInt(this.trap.getWidth() + 1) + this.trap.getTopLeft().getX(),
                random.nextInt(this.trap.getHeight() + 1) + this.trap.getTopLeft().getY());
    }
    /**
     * Constructor.
     * @param center center of ball
     * @param r radius of ball
     * @param color color of ball
     * @param trap trap of ball
     */
    public TrappedBall(Point center, int r, Color color, Trap trap) {
        this.center = new Point(center);
        this.radius = r;
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.velocity = new Velocity();
        this.trap = new Trap(trap);
    }
    /**
     * draws the ball on DrawSurface.
     * @param surface surface where we draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * returns radius of non trapped ball.
     * @return radius
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * move the trapped ball.
     */
    public void moveOneStep() {
        if (this.isTouchingTrapVertically()) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        if (this.isTouchingTrapHorizontally()) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        this.center = this.velocity.applyToPoint(this.center);
    }
    /**
     * checks if trapped ball touches the trap vertically.
     * @return true if it touches, false otherwise
     */
    public boolean isTouchingTrapVertically() {
        return (
            Threshold.isDoubleGreaterEqual(
                this.radius,
                this.center.getY() - this.trap.getTopLeft().getY()
            )
            || Threshold.isDoubleGreaterEqual(
                this.radius,
                this.trap.getBottomRight().getY() - this.center.getY()
            )
        );
    }
    /**
     * checks if trapped ball touches the trap horizontally.
     * @return true if it touches, false otherwise
     */
    public boolean isTouchingTrapHorizontally() {
        return (
            Threshold.isDoubleGreaterEqual(
                this.radius,
                this.center.getX() - this.trap.getTopLeft().getX()
            )
            || Threshold.isDoubleGreaterEqual(
                this.radius,
                this.trap.getBottomRight().getX() - this.center.getX()
            )
        );
    }
}
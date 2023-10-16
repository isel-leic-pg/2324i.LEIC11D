
// The ball properties
const val BALL_RADIUS = 100
const val BALL_DELTA = 15
const val MIN_X = BALL_RADIUS
const val MAX_X = WIDTH - BALL_RADIUS
const val MIN_Y = BALL_RADIUS
const val MAX_Y = HEIGHT - BALL_RADIUS
const val MAX_VELOCITY = 5

/**
 * Represents a point in the canvas.
 * @property x the horizontal coordinate. (0 is the leftmost position)
 * @property y the vertical coordinate. (0 is the topmost position)
 */
data class Point(val x:Int, val y:Int)

/**
 * Represents a ball in the canvas.
 * @property center the center of the ball.
 * @property dx the horizontal velocity of the ball.
 * @property dy the vertical velocity of the ball.
 * @property drop indicates if the ball is in drop mode.
 */
data class Ball(val center: Point, val dx: Int, val dy: Int, val drop:Boolean= false)

// Values used in the move function
private const val ACCELERATION_Y = 1
private const val LOSS_VELOCITY = 0.85

/**
 * Moves the ball according to its velocity.
 * The ball bounces when it reaches the canvas limits.
 * In drop mode the ball has a uniform accelerated movement.
 * In drop mode the ball loses velocity when it bounces at bottom.
 * @receiver the ball to move.
 * @return the ball in the new position.
 */
fun Ball.move(): Ball {
    var newDy = if (drop) dy+ACCELERATION_Y else dy
    if (center.y+newDy < MIN_Y || center.y+newDy > MAX_Y) newDy=-dy
    if (drop && dy>0 && newDy<0)
        newDy = (newDy * LOSS_VELOCITY).toInt()
    val newDx = if (center.x+dx < MIN_X || center.x+dx > MAX_X) -dx else dx
    return Ball(
        Point(center.x + newDx, center.y + newDy),
        newDx, newDy, drop
    )
}

/**
 * Coerces the center of the ball to be inside the canvas.
 * @receiver the center of the ball.
 * @return the center of the ball inside the canvas.
 */
fun Point.coerce() =
    Point(x.coerceIn(MIN_X..MAX_X), y.coerceIn(MIN_Y..MAX_Y))

/**
 * Starts a drop movement.
 * In drop mode the ball has a uniform accelerated movement.
 * @receiver the ball to start the drop movement.
 * @return the ball with the drop movement started.
 */
fun Ball.startDrop(): Ball {
    return Ball(center,dx= 0, dy= 0, drop=true)
}
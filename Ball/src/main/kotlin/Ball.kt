
// The ball properties
const val BALL_RADIUS = 100
const val BALL_DELTA = 15
const val MIN_X = BALL_RADIUS
const val MAX_X = WIDTH - BALL_RADIUS
const val MIN_Y = BALL_RADIUS
const val MAX_Y = HEIGHT - BALL_RADIUS
const val MAX_VELOCITY = 5


/**
 * Represents a ball in the canvas.
 * @property center the center of the ball.
 * @property dx the horizontal velocity of the ball.
 * @property dy the vertical velocity of the ball.
 * @property drop indicates if the ball is in drop mode.
 */
data class Ball(val center: Point, val speed: Speed= Speed(0,0), val drop:Boolean= false)

// Values used in the move function
private const val ACCELERATION_Y = 1
private const val LOSS_VELOCITY = 0.85

/**
 * Moves the ball according to its properties.
 * The ball bounces when it reaches the canvas limits.
 * In drop mode the ball has a uniform accelerated movement.
 * In drop mode the ball loses velocity when it bounces at bottom.
 * @receiver the ball to move.
 * @return the ball in the new position.
 */
fun Ball.move() = when {
    speed.isZero() && !drop -> this
    drop -> moveFree() //moveDrop()
    else -> moveFree()
}

/**
 * TODO: Function must be decomposed.
 * This function must be decomposed into two simpler functions:
 * - moveDrop: One that only moves when drop.
 * - moveFree: Another that moves freely (without drop)
 */
fun Ball.moveFree(): Ball {
    var newDy = if (drop) speed.dy+ACCELERATION_Y else speed.dy
    if (center.y+newDy < MIN_Y || center.y+newDy > MAX_Y) newDy=-speed.dy
    if (drop && speed.dy>0 && newDy<0)
        newDy = (newDy * LOSS_VELOCITY).toInt()
    val newDx = if (center.x+speed.dx < MIN_X || center.x+speed.dx > MAX_X) -speed.dx else speed.dx
    return copy(
        center = Point(center.x + newDx, center.y + newDy),
        speed = Speed(newDx,newDy)
    )
}

/**
 * Starts a drop movement.
 * In drop mode the ball has a uniform accelerated movement.
 * @receiver the ball to start the drop movement.
 * @return the ball with the drop movement started.
 */
fun Ball.startDrop(): Ball = copy(drop= true)
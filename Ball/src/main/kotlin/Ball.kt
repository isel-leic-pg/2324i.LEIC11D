
// The ball properties
const val BALL_RADIUS = 20
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
 * In "drop" mode the ball falls vertically, bouncing until it stabilizes on the ground.
 * In "free" mode the ball reflects when it collides with vertical or horizontal limits.
 * @receiver the ball to move.
 * @return the ball in the new position.
 */
fun Ball.move() = when {
    speed.isZero() && !drop -> this
    drop -> moveDrop()
    else -> moveFree()
}

/**
 * Moves the ball freely (without drop).
 * Reflects without losing speed when it collides with vertical or horizontal limits.
 */
fun Ball.moveFree(): Ball {
    val newSpeed = Speed(
        if (verticalCollision()) -speed.dx else speed.dx,
        if (horizontalCollision()) -speed.dy else speed.dy
    )
    return copy(center = center + newSpeed, speed = newSpeed)
}

/**
 * Returns true if the ball collides with a horizontal limit.
 */
private fun Ball.horizontalCollision() =
    center.y + speed.dy !in MIN_Y..MAX_Y

/**
 * Returns true if the ball collides with a vertical limit.
 */
private fun Ball.verticalCollision() =
    center.x + speed.dx !in MIN_X..MAX_X

/**
 * Moves the ball in drop mode.
 * Moves in a uniform accelerated vertical movement.
 * The reflection in the ground loss some velocity.
 */
fun Ball.moveDrop(): Ball {
    val newDy = speed.dy + ACCELERATION_Y
    val speed = Speed(
        speed.dx,
        if (center.y+newDy > MAX_Y)
            (-speed.dy * LOSS_VELOCITY).toInt()
        else newDy
    )
    return copy(center = center + speed, speed = speed)
}

/**
 * Starts a drop movement.
 * In drop mode the ball has a uniform accelerated movement.
 * @receiver the ball to start the drop movement.
 * @return the ball with the drop movement started.
 */
fun Ball.startDrop(): Ball = copy(drop= true)

/**
 * Stops the ball if the mouse click is inside the ball.
 * @receiver the current ball maybe stopped.
 * @param click The mouse click
 * @return The ball stopped or the current ball.
 */
fun Ball.stopIfTouched(click: Point): Ball =
    if (touched(click)) copy(speed=Speed(0,0))
    else this

/**
 * Returns true if the mouse click is inside the ball.
 */
fun Ball.touched(click: Point) =
    center.distanceTo(click) <= BALL_RADIUS
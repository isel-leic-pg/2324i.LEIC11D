/**
 * Represents a point in the canvas.
 * @property x the horizontal coordinate. (0 is the leftmost position)
 * @property y the vertical coordinate. (0 is the topmost position)
 */
data class Point(val x:Int, val y:Int)

/**
 * Represents a speed vector.
 * @property dx the horizontal velocity.
 * @property dy the vertical velocity.
 */
data class Speed(val dx: Int, val dy: Int)

/**
 * Returns a random velocity, vertical or horizontal.
 */
private fun randomDelta() = (-MAX_VELOCITY..MAX_VELOCITY).random()

/**
 * Returns a random speed vector.
 */
fun randomSpeed() = Speed( randomDelta(), randomDelta() )

/**
 * Returns true if the speed vector is zero.
 */
fun Speed.isZero() = dx==0 && dy==0

/**
 * Coerces the center of the ball to be inside the canvas.
 * @receiver the center of the ball.
 * @return the center of the ball inside the canvas.
 */
fun Point.coerce() =
    Point(x.coerceIn(MIN_X..MAX_X), y.coerceIn(MIN_Y..MAX_Y))

/**
 * Adds a Speed vector to a Point.
 * @receiver the current point.
 * @param speed the speed vector to add.
 * @return the new point after adding the speed vector.
 */
fun Point.plus(speed: Speed) = Point(x+speed.dx, y+speed.dy)
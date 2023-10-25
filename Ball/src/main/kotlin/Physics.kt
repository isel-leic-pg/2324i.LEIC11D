import kotlin.math.*

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
operator fun Point.plus(speed: Speed) = Point(x+speed.dx, y+speed.dy)

/**
 * Computes de the power of 2 of an integer.
 * @receiver the integer to compute the power of 2.
 * @return the power of 2 of the integer.
 */
fun Int.pow2() = this * this

/**
 * Computes the distance between two points.
 * @receiver the first point.
 * @param p the second point.
 * @return the distance between the two points.
 */
fun Point.distanceTo(p: Point): Double {
    val a = (y - p.y)
    val b = (x - p.x)
    return sqrt((a.pow2() + b.pow2()).toDouble())
}

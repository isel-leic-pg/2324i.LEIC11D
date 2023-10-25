import pt.isel.canvas.*

private const val VECTOR_FACTOR = 8
private const val VECTOR_THICKNESS = 4

/**
 * Draws the ball in the canvas and the velocity vector.
 * @param ball the ball to draw.
 * @receiver the canvas where to draw the ball.
 */
fun Canvas.drawBall(ball: Ball, erased: Boolean = true) {
    if (erased) erase()
    val x = ball.center.x
    val y = ball.center.y
    drawCircle(x, y, BALL_RADIUS, GREEN)
    drawLine(
        xFrom= x, yFrom = y,
        xTo = x+ball.speed.dx* VECTOR_FACTOR,
        yTo = y+ball.speed.dy* VECTOR_FACTOR,
        RED, VECTOR_THICKNESS
    )
}

fun Canvas.drawBalls(b: List<Ball>) {
    erase()
    b.forEach { drawBall(it,false) }
}
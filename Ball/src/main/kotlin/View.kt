import pt.isel.canvas.*

private const val VECTOR_FACTOR = 8
private const val VECTOR_THICKNESS = 4

/**
 * Draws the ball in the canvas and the velocity vector.
 * @param ball the ball to draw.
 * @receiver the canvas where to draw the ball.
 */
fun Canvas.drawBall(ball: Ball) {
    erase()
    val x = ball.center.x
    val y = ball.center.y
    drawCircle(x, y, BALL_RADIUS, GREEN)
    drawLine(x,y, x+ball.dx* VECTOR_FACTOR, y+ball.dy* VECTOR_FACTOR, RED, VECTOR_THICKNESS)
}
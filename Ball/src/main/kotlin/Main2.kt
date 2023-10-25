import pt.isel.canvas.*

fun main() {
    onStart {
        var ball = Ball(Point(WIDTH/2,HEIGHT/2),randomSpeed())
        val cv = Canvas(WIDTH,HEIGHT, WHITE)
        cv.onMouseDown { me ->
            ball = ball.stopIfTouched(Point(me.x,me.y))
            cv.drawBall(ball)
        }
        cv.onTimeProgress(FRAME_TIME) {
            ball = ball.move()
            cv.drawBall(ball)
        }
    }
    onFinish {  }
}

/**
 * Processes a key event.
 * @param ke the key event to process.
 * @param ball the ball to move.
 * @return the ball in the new position and velocity.
 */
private fun processKey(ke: KeyEvent, ball: Ball) =
    when(ke.code) {
        'G'.code -> ball.copy(speed = randomSpeed())
        'D'.code -> ball.startDrop()
        else -> Ball(moveByKey(ke.code, ball.center).coerce())
    }


/**
 * Moves the ball center according to the cursor key pressed
 * @param key the code of the key pressed.
 * @param b the center of the ball.
 * @return the new center of the ball.
 */
private fun moveByKey(key: Int, b: Point): Point = when (key) {
    LEFT_CODE -> Point(b.x - BALL_DELTA, b.y)
    RIGHT_CODE -> Point(b.x + BALL_DELTA, b.y)
    UP_CODE -> Point(b.x, b.y - BALL_DELTA)
    DOWN_CODE -> Point(b.x, b.y + BALL_DELTA)
    else -> b
}

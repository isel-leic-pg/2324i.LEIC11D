import pt.isel.canvas.*

// The canvas area
const val WIDTH = 800
const val HEIGHT = 600

// Time to draw each frame
const val FRAME_TIME = 20 // milliseconds

/**
 * The main function of the application.
 * Show a window with a ball that moves according to the user input.
 * Cursor keys move the ball in the corresponding direction.
 * The 'G' key generates a random velocity for the ball.
 * The 'D' key starts a drop movement.
 * The mouse click sets the ball center at the mouse position.
 */
fun main() {
    onStart {
        var ball = Ball(Point(WIDTH/2,HEIGHT/2),0,0)
        val cv = Canvas(WIDTH,HEIGHT, WHITE)
        cv.drawBall(ball)
        cv.onKeyPressed { ke ->
            ball = processKey(ke, ball)
            cv.drawBall(ball)
        }
        cv.onMouseDown { me ->
            ball = Ball(Point(me.x,me.y).coerce(),0,0)
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
        'G'.code -> Ball(ball.center,
            dx = (-MAX_VELOCITY..MAX_VELOCITY).random(),
            dy = (-MAX_VELOCITY..MAX_VELOCITY).random()
        )
        'D'.code -> ball.startDrop()
        else -> Ball(moveByKey(ke.code, ball.center).coerce(), 0, 0)
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

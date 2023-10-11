import pt.isel.canvas.*

const val WIDTH = 400
const val HEIGHT = 400
const val BALL_RADIUS = 50
const val BALL_DELTA = 15
const val MIN_X = BALL_RADIUS
const val MAX_X = WIDTH - BALL_RADIUS
const val MIN_Y = BALL_RADIUS
const val MAX_Y = HEIGHT - BALL_RADIUS
const val FRAME_TIME = 20 // milliseconds
const val MAX_VELOCITY = 5

data class Point(val x:Int, val y:Int)

data class Ball(val center: Point, val dx: Int, val dy: Int)

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
            ball = moveBall(ball)
            cv.drawBall(ball)
        }
    }
    onFinish {  }
}

private fun moveBall(ball: Ball) = Ball(
    Point(ball.center.x + ball.dx, ball.center.y + ball.dy),
    ball.dx, ball.dy
)

private fun processKey(ke: KeyEvent, ball: Ball) =
    if (ke.code == 'G'.code)
        Ball(ball.center,
            dx = (-MAX_VELOCITY..MAX_VELOCITY).random(),
            dy = (-MAX_VELOCITY..MAX_VELOCITY).random()
        )
    else
        Ball(moveByKey(ke.code, ball.center).coerce(), 0, 0)

private fun Point.coerce() =
    Point( x.coerceIn(MIN_X..MAX_X), y.coerceIn(MIN_Y..MAX_Y))

private fun moveByKey(key: Int, b: Point): Point = when (key) {
    LEFT_CODE -> Point(b.x - BALL_DELTA, b.y)
    RIGHT_CODE -> Point(b.x + BALL_DELTA, b.y)
    UP_CODE -> Point(b.x, b.y - BALL_DELTA)
    DOWN_CODE -> Point(b.x, b.y + BALL_DELTA)
    else -> b
}

private const val VECTOR_FACTOR = 8

private fun Canvas.drawBall(ball: Ball) {
    erase()
    val x = ball.center.x
    val y = ball.center.y
    drawCircle(x, y, BALL_RADIUS, GREEN)
    drawLine(x,y, x+ball.dx* VECTOR_FACTOR, y+ball.dy* VECTOR_FACTOR, RED,2)
}
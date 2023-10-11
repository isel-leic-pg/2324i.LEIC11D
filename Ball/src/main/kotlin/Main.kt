import pt.isel.canvas.*

const val WIDTH = 400
const val HEIGHT = 400
const val BALL_RADIUS = 100
const val BALL_DELTA = 15
const val MIN_X = BALL_RADIUS
const val MAX_X = WIDTH - BALL_RADIUS
const val MIN_Y = BALL_RADIUS
const val MAX_Y = HEIGHT - BALL_RADIUS

data class Point(val x:Int, val y:Int)

fun main() {
    onStart {
        var center = Point(WIDTH/2,HEIGHT/2)
        val cv = Canvas(WIDTH,HEIGHT, WHITE)
        cv.drawBall(center)
        cv.onKeyPressed { key ->
            center = moveBall(key.code, center)
            cv.drawBall(center)
        }
    }
    onFinish {  }
}

private fun moveBall(key: Int, b: Point): Point = inLimits( moveByKey(key, b) )

private fun inLimits(p: Point): Point = when {
    p.x < MIN_X -> Point(MIN_X, p.y)
    p.x > MAX_X -> Point(MAX_X, p.y)
    p.y < MIN_Y -> Point(p.x, MIN_Y)
    p.y > MAX_Y -> Point(p.x, MAX_Y)
    else -> p
}

private fun moveByKey(key: Int, b: Point): Point = when (key) {
    LEFT_CODE -> Point(b.x - BALL_DELTA, b.y)
    RIGHT_CODE -> Point(b.x + BALL_DELTA, b.y)
    UP_CODE -> Point(b.x, b.y - BALL_DELTA)
    DOWN_CODE -> Point(b.x, b.y + BALL_DELTA)
    else -> b
}
/*
    when (key) {
    LEFT_CODE ->
        Point(if (b.x - BALL_DELTA >= MIN_X) b.x - BALL_DELTA else MIN_X, b.y)
    RIGHT_CODE ->
        Point(if (b.x + BALL_DELTA <= MAX_X) b.x + BALL_DELTA else MAX_X, b.y)
    UP_CODE ->
        Point(b.x, if (b.y - BALL_DELTA >= MIN_Y) b.y - BALL_DELTA else MIN_Y)
    DOWN_CODE ->
        Point(b.x, if (b.y + BALL_DELTA <= MAX_Y) b.y + BALL_DELTA else MAX_Y)
    else -> b
}
*/

private fun Canvas.drawBall(ball: Point) {
    erase()
    drawCircle(ball.x, ball.y, BALL_RADIUS, GREEN)
}
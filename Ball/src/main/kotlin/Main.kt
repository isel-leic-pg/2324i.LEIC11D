import pt.isel.canvas.*

const val BALL_RADIUS = 100
const val BALL_DX = 10

fun main() {
    onStart {
        var x = 200
        val cv = Canvas(400,600, WHITE)
        cv.drawBall(x)
        cv.onKeyPressed {
            x = when (it.code) {
                LEFT_CODE -> x - BALL_DX
                RIGHT_CODE -> x + BALL_DX
                else -> x
            }
            cv.drawBall(x)
        }
    }
    onFinish {  }
}

private fun Canvas.drawBall(x: Int) {
    erase()
    drawCircle(x, 300, BALL_RADIUS, GREEN)
}
import pt.isel.canvas.*

/**
 * Main function of the application.
 * Shows several balls moving with random initial speed.
 * Each ball disappears when clicked with the mouse.
 * The application ends when there are no balls left.
 */
fun main() {
    onStart {
        var allBalls = List(10) {
            Ball(Point(WIDTH/2,HEIGHT/2),randomSpeed())
        }
        val cv = Canvas(WIDTH,HEIGHT, WHITE)
        cv.drawBalls(allBalls)
        cv.onMouseDown { me ->
            //allBalls = allBalls.map { it.stopIfTouched(Point(me.x,me.y)) }
            allBalls = allBalls.filter {
                ! it.touched(Point(me.x,me.y))
            }
            if (allBalls.isEmpty()) cv.close()
            cv.drawBalls(allBalls)
        }
        cv.onTimeProgress(FRAME_TIME) {
            allBalls = allBalls.map { it.move() }
            cv.drawBalls(allBalls)
        }
    }
    onFinish {  }
}


import pt.isel.canvas.*

const val FLAG_WIDTH = 800
const val FLAG_HEIGHT = 600
const val GREEN_WIDTH = FLAG_WIDTH / 3
const val RADIUS_CENTER = FLAG_HEIGHT / 3 / 2
const val DIAMETER_CENTER = RADIUS_CENTER * 2

fun main() {
    println("Begin")
    onStart {
        println("Hello")
        val cv = Canvas(FLAG_WIDTH,FLAG_HEIGHT,0xFF0000)
        cv.drawFlag()
        cv.onMouseMove { mouse ->
            if (mouse.down)
                cv.drawCircle(mouse.x,mouse.y,2, BLACK)
        }
        val cv2 = Canvas(100,100, BLACK)
        cv2.drawLine(0,0,cv2.width-1,cv2.height-1, YELLOW,4)
        cv2.onMouseDown {
            cv.erase()
            cv.drawFlag()
        }
    }
    onFinish {
        println("Bye.")
    }
    println("End.")
}

private fun Canvas.drawFlag() {
    drawRect(0, 0, GREEN_WIDTH, FLAG_HEIGHT, GREEN)
    drawImage(
        "esfera_armilar",
        GREEN_WIDTH - RADIUS_CENTER,
        FLAG_HEIGHT / 2 - RADIUS_CENTER,
        DIAMETER_CENTER,
        DIAMETER_CENTER
    )
    drawImage(
        "brasão",
        GREEN_WIDTH - RADIUS_CENTER / 2,
        FLAG_HEIGHT / 2 - RADIUS_CENTER / 2,
        RADIUS_CENTER,
        RADIUS_CENTER
    )
}
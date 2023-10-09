import pt.isel.canvas.*

private const val FRAME_TIME = 1000 // [milliseconds]

fun main() {
    onStart {
        val arena: Canvas = createArena()
        arena.drawBackground()
        arena.onKeyPressed { key ->
            // Code to be executed when a key is pressed
            // println(key)
            if (key.code == 'Q'.code) arena.close()
        }
        arena.onTimeProgress(FRAME_TIME) {
            // Code to be executed in each frame
            print('.')
        }
    }
    onFinish { println("Bye.") }
}



import pt.isel.canvas.*

private const val FRAME_TIME = 30 // in milliseconds

/**
 * The main function of the program.
 * Creates the arena and the game.
 * Change the gama for each key pressed and for each frame.
 */
fun main() {
    onStart {
        val arena = createCanvas()
        //arena.drawSprite(Cell(3,4).toPoint(),Sprite(2,3,2))
        //arena.drawMan(Man(Cell(3,4).toPoint(),Direction.LEFT))
        //arena.drawSprite(Cell(3,5).toPoint(),Sprite(0,3,2))
        var game = loadGame("level1.txt")
        arena.onKeyPressed { key ->
            if (key.code == ESCAPE_CODE) arena.close()
            game = game.doAction(key.code.toAction())
            arena.drawGame(game)
        }
        arena.onTimeProgress(FRAME_TIME) {
            game = game.stepFrame()
            arena.drawGame(game)
        }
    }
    onFinish { }
}

private const val SPACE_CODE = 32

/**
 * Converts a key code to an action.
 * @receiver the key code to convert.
 * @return the action corresponding to the key code.
 */
fun Int.toAction(): Action? =
    when(this) {
        LEFT_CODE ->    Action.WALK_LEFT
        RIGHT_CODE ->   Action.WALK_RIGHT
        UP_CODE ->      Action.UP_STAIRS
        DOWN_CODE ->    Action.DOWN_STAIRS
        SPACE_CODE ->   Action.JUMP
        else -> null
    }
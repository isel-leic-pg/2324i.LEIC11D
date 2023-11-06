
/**
 * Represents the game action.
 */
enum class Action { WALK_LEFT, WALK_RIGHT, UP_STAIRS, DOWN_STAIRS, JUMP }

/**
 * Represents all game information.
 * @property man information about man
 * @property floor positions of floor cells
 * @property stairs positions of stairs cells
 */
data class Game(
    val man: Man,
    val floor: List<Cell>,
    val stairs: List<Cell>,
)

/**
 * Loads a game from a file.
 * @param fileName the name of the file with the game information.
 * @return the game loaded.
 */
fun loadGame(fileName: String) :Game {
    val cells: List<CellContent> = loadLevel(fileName)
    return Game(
        man = createMan( cells.first { it.type==CellType.MAN }.cell ),
        floor = cells.ofType(CellType.FLOOR),
        stairs = cells.ofType(CellType.STAIR),
    )
}

/**
 * Performs an action to the game.
 * If the action is null, returns current game.
 * @param action the action to perform.
 * @receiver the current game.
 * @return the game after the action performed.
 */
fun Game.doAction(action: Action?): Game {
    if (action==null) return this
    val newMan = when(action) {
        Action.WALK_LEFT -> man.walkIn(Direction.LEFT, this)
        Action.WALK_RIGHT -> man.walkIn(Direction.RIGHT, this)
        Action.UP_STAIRS -> man.climbIn(Direction.UP, this)
        Action.DOWN_STAIRS -> man.climbIn(Direction.DOWN, this)
        // TODO: implement the jump action
        else -> man
    }
    return if (newMan!=man) copy(man = newMan) else this
}

/**
 * Moves the man in the indicated direction if the new cell meets the condition
 * @param dir the direction to move.
 * @param condition the condition to check the new cell.
 * @return The man after move.
 */
fun Man.moveIn(dir: Direction, condition: (Cell)->Boolean): Man {
    val cell = pos.toCell() + dir
    return if (condition(cell))
        Man( cell.toPoint(), dir)
    else this
}

/**
 * Moves the man in the indicated horizontal direction if there is a floor or stairs below.
 * @param dir the horizontal direction to move.
 * @param game the current game to check the floor and stairs.
 * @return The man after walk.
 */
fun Man.walkIn(dir: Direction, game: Game) =
    moveIn(dir) { it+Direction.DOWN in game.floor+game.stairs }

/**
 * Moves the man in the indicated vertical direction if there is a stair there.
 * @param dir the vertical direction to move.
 * @param game the current game to check the stairs.
 * @return The man after climbing.
 */
fun Man.climbIn(dir: Direction, game: Game) =
    moveIn(dir) { it in game.stairs }

/**
 * Computes the next game state.
 * If the man is stopped, returns current game.
 * @receiver the current game.
 * @return the game after the next frame.
 */
fun Game.stepFrame(): Game = this
// TODO: implement this function

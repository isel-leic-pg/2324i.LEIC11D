// Speed of man in pixels per frame, in horizontal and vertical directions
const val MOVE_SPEED = CELL_WIDTH / 6
const val CLIMBING_SPEED = CELL_HEIGHT / 4

// Limits of the board in pixels
// Point(MAX_X, MAX_Y) == Cell(GRID_HEIGHT-1, GRID_WIDTH-1).toPoint()
const val MAX_X = (GRID_WIDTH - 1) * CELL_WIDTH
const val MAX_Y = (GRID_HEIGHT - 1) * CELL_HEIGHT

/**
 * Represents the Man in the game.
 * @property pos is the position in the board.
 * @property faced the direction the man is facing
 */
data class Man(
    val pos: Point,
    val faced: Direction,
    // TODO: Add other properties
)

/**
 * Creates the Man in the cell
 */
fun createMan(cell: Cell) = Man(
    pos = cell.toPoint(),
    faced = Direction.LEFT,
    // TODO: Initialize other properties
)

// TODO: Add other functions for man actions

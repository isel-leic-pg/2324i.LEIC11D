import pt.isel.canvas.*

// Dimensions of the sprites in the images files
// Floor, Egg, Food, Stair are 1x1 ; Man is 1x2 ; Hen 1x3 or 2x3
const val SPRITE_WIDTH = 24  // [pixels]
const val SPRITE_HEIGHT = 16 // [pixels]

// Dimensions of each cell of the Arena grid
const val VIEW_FACTOR = 2 // each cell is VIEW_FACTOR x sprite
const val CELL_WIDTH = VIEW_FACTOR * SPRITE_WIDTH   // [pixels]
const val CELL_HEIGHT = VIEW_FACTOR * SPRITE_HEIGHT  // [pixels]

// Dimensions of the Arena grid
const val GRID_WIDTH = 10   // [cells]  final = 20
const val GRID_HEIGHT = 8  // [cells]  final = 24

fun createArena(): Canvas =
    Canvas(GRID_WIDTH * CELL_WIDTH, GRID_HEIGHT * CELL_HEIGHT, BLACK)

/**
 * Draw the base floor in the bottom of the arena.
 * Each cell of the floor is a sprite of the image file "floor.png".
 */
fun Canvas.drawBaseFloor() {
    (0 ..< width step CELL_WIDTH).forEach { x ->
        drawImage("floor.png", x, height- CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT)
    }
}

/**
 * Draw horizontal and vertical lines of the grid in arena.
 */
fun Canvas.drawGridLines() {
    (0 ..< width step CELL_WIDTH).forEach { x -> drawLine(x, 0, x, height, WHITE, 1) }
    (0 ..< height step CELL_HEIGHT).forEach { y -> drawLine(0, y, width, y, WHITE, 1) }
}

/**
 * Draw the background of the arena.
 */
fun Canvas.drawBackground() {
    erase()            // clear the canvas
    drawBaseFloor()    // draw the base floor
    //drawGridLines()    // draw the grid (for debugging)
}


// The code of this module depends only on the constants: CELL_WIDTH and CELL_HEIGHT
// This module defines the types: Point, Cell, Direction and Speed

/**
 * Represents a point in the arena.
 * The origin (0,0) is the top-left corner.
 * @property x the horizontal coordinate (in pixels)
 * @property y the vertical coordinate (in pixels)
 */
data class Point(val x: Int, val y: Int)

/**
 * Returns a new point with the coordinates limited to the area.
 * @param xMax the maximum horizontal coordinate
 * @param yMax the maximum vertical coordinate
 * @receiver the current point that will be limited
 * @return a new point with the coordinates limited to the area.
 */
fun Point.limitToArea(xMax: Int, yMax: Int): Point =
    if (x in 0..xMax && y in 0..yMax) this
    else Point( x.coerceIn(0, xMax), y.coerceIn(0, yMax))

/**
 * Represents a cell in the arena grid.
 * @property row the vertical coordinate (in cells)
 * @property col the horizontal coordinate (in cells)
 */
data class Cell(val row: Int, val col: Int)

/**
 * Converts a point to a cell.
 * Any point inside a cell is converted to that cell.
 * @receiver the point to convert.
 * @return the cell corresponding to the point.
 */
fun Point.toCell() = Cell(y / CELL_HEIGHT, x / CELL_WIDTH)

/**
 * Converts a cell to a point.
 * The point is the top-left corner of the cell.
 * @receiver the cell to convert.
 * @return the point corresponding to the cell.
 */
fun Cell.toPoint() = Point(col * CELL_WIDTH, row * CELL_HEIGHT)

/**
 * Represents a direction.
 * @property dRow the vertical displacement
 * @property dCol the horizontal displacement
 */
enum class Direction(val dRow: Int, val dCol: Int) {
    LEFT(0,-1), RIGHT(0,+1), UP(-1,0), DOWN(+1,0)
}

/**
 * Returns true if the direction is horizontal (LEFT or RIGHT).
 * @receiver the direction to test.
 * @return true if the direction is horizontal.
 */
fun Direction.isHorizontal() = this.dRow == 0

/**
 * Adds a direction to a cell.
 * As an operator function, it can be used: cell+dir
 * @receiver the current cell.
 * @param dir the direction to add.
 * @return the new cell after adding the direction.
 */
operator fun Cell.plus(dir: Direction) = Cell(row + dir.dRow, col + dir.dCol)

/**
 * Represents a speed vector.
 * @property dx the horizontal velocity.
 * @property dy the vertical velocity.
 */
data class Speed(val dx: Int, val dy: Int)

/**
 * Returns true if the speed vector is zero.
 */
fun Speed.isZero() = dx == 0 && dy == 0

/**
 * Adds a Speed vector to a Point.
 * As an operator function, it can be used: pos+speed
 * @receiver the current point.
 * @param speed the speed vector to add.
 * @return the new point after adding the speed vector.
 */
operator fun Point.plus(speed: Speed) = Point(x + speed.dx, y + speed.dy)

/**
 * Verify if the position is synchronized with one cell.
 * The speed components are zeroed if the position is synchronized with one cell.
 * @param pos the position to verify.
 * @receiver the current speed.
 * @return the new speed with zero components if the position is synchronized with one cell.
 */
fun Speed.stopIfInCell(pos: Point): Speed {
    val newDx = if (pos.x % CELL_WIDTH == 0) 0 else dx
    val newDy = if (pos.y % CELL_HEIGHT == 0) 0 else dy
    return if (newDx!=dx || newDy!=dy) Speed(newDx, newDy) else this
}

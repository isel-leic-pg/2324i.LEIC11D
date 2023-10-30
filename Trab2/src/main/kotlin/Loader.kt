import kotlin.io.path.*

/**
 * Represents the type of cell in the game.
 * @property symbol the symbol used to represent the cell in the level file.
 */
enum class CellType(val symbol: Char) {
    MAN('M'), FLOOR('#'), STAIR('H') , EGG('e'), FOOD('f'), HEN('G')
}

/**
 * Aggregates the type of cell and its position.
 * @property type the type of cell.
 * @property cell the position of the cell.
 */
data class CellContent(val type: CellType, val cell: Cell)

/**
 * Loads a level from a file.
 * @param fileName the name of the file in resources folder.
 * @return the list of cells in the level.
 */
fun loadLevel(fileName: String) :List<CellContent> {
    var path = Path("build/resources/main/$fileName")
    if (!path.exists()) path = Path(fileName)
    val lines = path.readLines()
    val cells = lines.flatMapIndexed { row, line ->
        line.mapIndexedNotNull { col, symbol ->
            val type = CellType.entries.find { it.symbol == symbol }
            if (type != null) CellContent(type, Cell(row, col)) else null
        }
    }
    return cells
}

/**
 * Returns the list of cells of the given type.
 */
fun List<CellContent>.ofType(type: CellType) = filter { it.type == type }.map { it.cell }



const val WIDTH = 3
const val DIM = WIDTH * WIDTH
const val USER = 'O'
const val COMPUTER = 'X'
const val EMPTY = ' '

var board = List(DIM) { EMPTY }

fun readInt(quest: String): Int? {
    print("$quest? ")
    return readln().toIntOrNull()
}

fun printBoard(board: List<Char>) {
    val sepLine = "---+".repeat(WIDTH-1)+"---"
    board.chunked(WIDTH)
        .map { it.joinToString(separator= " | ") }
        .forEachIndexed { i, line ->
            println(" $line ")
            if (i < WIDTH-1) println(sepLine)
        }
}

fun userMove(quest: String, error: String): Int {
    while(true) {
        val move = readInt(quest)
        if (move != null && move in board.indices && board[move] == EMPTY)
            return move
        println(error)
    }
}

fun computerMove(): Int =
    board.indices.filter{ board[it]==EMPTY }.random()

fun List<Char>.play(move: Int, symbol: Char): List<Char> =
    mapIndexed { idx, c -> if (idx==move) symbol else c }

fun List<Char>.lineCompleted(symbol: Char): Boolean =
    if ( count{ it==symbol } < WIDTH ) false
    else (0 ..< DIM step WIDTH).any{ line -> (0..<WIDTH).all{ board[line+it]==symbol} }
        || (0 ..< WIDTH).any{ col -> (0..<DIM step WIDTH).all{ board[col+it]==symbol} }
        || (0 ..< DIM step WIDTH+1).all { board[it]==symbol }
        || (WIDTH-1 ..< DIM step WIDTH-1).all { board[it]==symbol }

fun main() {
    board = listOf('X','O','O','O','O','X',' ',' ','X')
    println(computerMove())
    printBoard(board)
    val move = userMove("Jogada do utilizador: ","Jogada inválida")
    board = board.play(move,USER)
    printBoard(board)
    println(board.lineCompleted(USER))
}


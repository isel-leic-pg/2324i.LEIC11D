
const val WIDTH = 3
const val DIM = WIDTH * WIDTH
const val USER = 'O'
const val COMPUTER = 'X'
const val EMPTY = ' '

//var board = List(DIM) { EMPTY }

data class Move(val pos: Int, val player: Char)
var board = emptyList<Move>()

fun readInt(quest: String): Int? {
    print("$quest? ")
    return readln().toIntOrNull()
}

fun printBoard(board: List<Move>) {
    val sepLine = "---+".repeat(WIDTH-1)+"---"
    repeat(WIDTH) { l ->
        repeat(WIDTH) { c ->
            val pos = l * WIDTH + c
            val move = board.firstOrNull { it.pos==pos }
            print(" ${move?.player ?: EMPTY} ")
            if (c < WIDTH-1) print("|")
        }
        if (l < WIDTH-1) println("\n$sepLine")
    }
    println()
}

fun List<Move>.isEmpty(move: Int) = none{ it.pos==move }

fun userMove(quest: String, error: String): Int {
    while(true) {
        val move = readInt(quest)
        if (move != null && move in (0..<DIM) && board.isEmpty(move))
            return move
        println(error)
    }
}

fun computerMove(): Int =
    (0..<DIM).filter{ board.isEmpty(it) }.random()

fun List<Move>.play(move: Int, symbol: Char): List<Move> =
    this + Move(move,symbol)

fun List<Move>.lineCompleted(symbol: Char): Boolean {
    val moves = filter { it.player == symbol }.map { it.pos }
    if (moves.size < WIDTH) return false
    return  (0..<WIDTH).any { l -> moves.count { it / WIDTH == l } == WIDTH }
            || (0..<WIDTH).any { c -> moves.count { it % WIDTH == c } == WIDTH }
            || moves.count { it % WIDTH == it / WIDTH } == WIDTH
            || moves.count { WIDTH - (it % WIDTH) == it / WIDTH } == WIDTH
}

fun List<Move>.boardCompleted(): Boolean = size==DIM

/*
fun printBoard(board: List<Char>) {
    val sepLine = "---+".repeat(WIDTH-1)+"---"
    board.chunked(WIDTH)
        .map { it.joinToString(separator= " | ") }
        .forEachIndexed { i, line ->
            println(" $line ")
            if (i < WIDTH-1) println(sepLine)
        }
}

fun List<Char>.isEmpty(move: Int) = this[move] == EMPTY

fun computerMove(): Int =
    board.indices.filter{ board[it]==EMPTY }.random()

fun List<Char>.play(move: Int, symbol: Char): List<Char> =
    mapIndexed { idx, c -> if (idx==move) symbol else c }

fun List<Char>.lineCompleted(symbol: Char): Boolean =
    if ( count{ it==symbol } < WIDTH ) false
    else (0 ..< DIM step WIDTH).any{ line -> (0..<WIDTH).all{ board[line+it]==symbol} }
        || (0 ..< WIDTH).any{ col -> (0..<DIM step WIDTH).all{ board[col+it]==symbol} }
        || (0 ..< DIM step WIDTH+1).all { board[it]==symbol }
        || (WIDTH-1 ..< DIM-1 step WIDTH-1).all { board[it]==symbol }

fun List<Char>.boardCompleted(): Boolean = EMPTY !in this
*/

fun main() {
    printBoard(board)
    while( ! board.boardCompleted() ) {
        val move = userMove("Jogada do utilizador: ","Jogada inválida")
        board = board.play(move,USER)
        printBoard(board)
        if (board.lineCompleted(USER)) {
            println("Ganhou o utilizador")
            return
        }
        if (board.boardCompleted()) break
        val cpu = computerMove()
        board = board.play(cpu,COMPUTER)
        println("Jogada o computador: $cpu")
        printBoard(board)
        if (board.lineCompleted(COMPUTER)) {
            println("Ganhou o computador")
            return
        }
    }
    println("Empate.")
}


fun main() {
    val action: Action = Action.WALK_LEFT
    val dir = Direction.LEFT
    println(action.toString())
    println(dir)
    println("${dir.dCol}:${dir.dRow}")
    if (dir==Direction.DOWN) {
        println("Desce")
    }
    println(dir.isHor())

    println((Cell(0,0)+Direction.DOWN).toPoint())
    println(dir.ordinal)
    println(dir.name)
    println(Direction.entries.map { it.ordinal })
    val dirs = Direction.entries.map{ it.name }.sorted().reversed()
    println(dirs)
}

fun Direction.isHor(): Boolean =
    this == Direction.LEFT || this == Direction.RIGHT
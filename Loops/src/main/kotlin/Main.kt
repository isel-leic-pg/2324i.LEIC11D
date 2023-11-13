fun main() {
    (1..10).forEach{ print("$it ") }
    println()

    repeat(10) { print("${it+1} ") }
    println()

    useDoWhile()
    useWhile()
    useFor()
}

fun useFor() {
    for (a in 1..10) print("$a ")
    println()
}

fun useWhile() {
    var a = 1
    while (a <= 10) print("${a++} ")
    println()
}

fun useDoWhile() {
    var a = 1
    do print("${a++} ") while (a <= 10)
    println()
}
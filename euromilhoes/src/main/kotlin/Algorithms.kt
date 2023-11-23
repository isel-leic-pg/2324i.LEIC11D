/**
 * Pesquisa sequencial em lista não ordenada
 */
fun List<Char>.indexOfChar(c: Char): Int? {
    for(idx in indices)
        if (c == this[idx])
            return idx
    return null
}

/**
 * Pequisa dicotómica em lista ordenada
 */
fun List<Char>.indexOfCharSorted(c: Char): Int? {
    var s = 0
    var e = size-1
    while (s <= e) {
        val m = (s + e) / 2
        when {
            c == this[m] -> return m
            c < this[m] -> e = m - 1
            else -> s = m + 1
        }
    }
    return null
}

fun List<Char>.indexOfCharSorted(c: Char, from:Int, to:Int): Int? {
    if (from>to) return null
    val m = (from+to) / 2
    if (this[m]==c) return m
    return if (c<this[m]) indexOfCharSorted(c,from,m-1)
    else indexOfCharSorted(c,m+1,to)
}

fun main() {
    val chars = listOf('I','S','E','L')
    println( chars.indexOfChar('S') )
    println( chars.indexOfChar('X') )
    val chars2 = List(25){ ('A'..'Z').random() }.sorted()
    println(chars2)
    println( chars2.indexOfCharSorted('I',5,15))
    "ACDMZ".toList().indexOfCharSorted('F')
}
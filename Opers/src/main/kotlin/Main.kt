
private fun add(a: Int, b: Int): Int = a + b

private fun max(a: Int, b: Int) = if (a>=b) a else b

fun main() {
    fun sub(a: Int, b: Int) = a - b

    val op: Char = readChar("Operação")
    val v1: Int = readInt("Valor 1")
    val v2: Int = readInt("Valor 2")
    val r: Int = when(op) {
        '+' -> add(v1,v2)
        '-' -> sub(v1,v2)
        else -> max(v1,v2)
    }
    println("$op = $r")
}
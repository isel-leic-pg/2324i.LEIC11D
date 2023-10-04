
private fun add(a: Int, b: Int): Int = a + b
private fun max(a: Int, b: Int) = if (a>=b) a else b
private fun sub(a: Int, b: Int) = a - b

fun main() {
    val op = readChar("Operação")
    val fx = getFunction(op)
    val r = fx(readInt("Valor 1") ,readInt("Valor 2"))
    println("$op = $r")
}

private fun getFunction(oper: Char): (Int,Int)->Int = when(oper) {
    '+' -> ::add
    '-' -> ::sub
    else -> ::max
}
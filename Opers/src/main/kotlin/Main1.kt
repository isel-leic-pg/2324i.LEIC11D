
private fun add(a: Int, b: Int): Int = a + b

private fun max(a: Int, b: Int) = if (a>=b) a else b

private fun sub(a: Int, b: Int) = a - b

// type of main is: ()->Unit
fun main() {
    val op: Char = readChar("Operação")
    val fx: (Int,Int)->Int = when(op) {
        '+' -> ::add
        '-' -> ::sub
        else -> ::max
    }
    val r: Int = fx(readInt("Valor 1") ,readInt("Valor 2"))
    println("$op = $r")
}
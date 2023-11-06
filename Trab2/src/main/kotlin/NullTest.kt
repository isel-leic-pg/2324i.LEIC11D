fun main() {
/*
    val value: Float? = readln().toFloatOrNull()
    println(value)
    val x = value ?: 0
    println(x)
    val y: Int = value?.toInt() ?: 0
    println(y)
    if (value!=null)
        println("Dobro = ${value*2}")
    else
        println("Valor inválido")
*/

    val values: List<Int> =
        readln().split(' ').mapNotNull { it.toIntOrNull() }
    println(values.sum())
}
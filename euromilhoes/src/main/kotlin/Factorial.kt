
fun factI(n: Int): Int {
    var res = 1
    for(nn in 1..n)
        res *= nn
    return res
}

fun fact2I(n: Int) = (1..n).reduce{ acc, i -> acc*i }

fun fact(n: Int): Long =
    if (n<=1) 1 else fact(n-1)*n

fun main() {
    println(fact(10))
    println(factI(10))
    repeat(40){
        println("$it! = ${fact(it)}")
    }
}
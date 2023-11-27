/**
 * Computes the factorial of a number.
 * Iterative version.
 */
fun factI(n: Int): Int {
    var res = 1
    for(nn in 1..n)
        res *= nn
    return res
}

/**
 * Computes the factorial of a number.
 * Iterative version using reduce.
 */
fun fact2I(n: Int) = (1..n).reduce{ acc, i -> acc*i }

/**
 * Computes the factorial of a number.
 * Recursive version.
 */
fun fact(n: Int): Long {
    if (n <= 1)
        return 1
    return n * fact(n - 1)
}

fun main() {
    println(fact(10))
    println(factI(10))
    repeat(40){
        println("$it! = ${fact(it)}")
    }
}
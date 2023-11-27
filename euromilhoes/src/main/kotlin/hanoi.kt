
/**
 * Show the steps to solve the Hanoi Tower problem.
 * @param n Number of disks
 * @param from Initial tower
 * @param to Final tower
 * @param aux Remain tower
 */
fun hanoi(n: Int, from: Char, to: Char, aux: Char) {
    if (n == 1)
        println("$from --> $to")
    else {
        hanoi(n - 1, from, aux, to)
        hanoi(  1  , from, to , aux)
        hanoi(n - 1, aux , to , from)
    }
}

/**
 * Show the steps to solve the Hanoi Tower problem.
 * For 3 disks, the output is:
 * A --> C
 * A --> B
 * C --> B
 * A --> C
 * B --> A
 * B --> C
 * A --> C
 */
fun main() {
    hanoi(3,'A','C','B')
}
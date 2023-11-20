
const val MAX_NUMBER = 50   // Numbers in 1..50
const val MAX_STAR = 12     // Stars in 1..12
const val MIN_NUMBERS = 5   // Minimum numbers in a simple bet
const val MAX_NUMBERS = 11  // Maximum numbers in a multiple bet
const val MIN_STARS = 2     // Minimum stars in a simple bet
const val MAX_STARS = 12    // Maximum stars in a multiple bet

val allNumbers = 1..MAX_NUMBER
val allStars = 1..MAX_STAR

/**
 * Represents a bet in the Euromilhões game.
 * @property numbers the numbers of the bet.
 * @property stars the stars of the bet.
 */
data class Bet(
    val numbers: List<Int>, // size in 5..11
    val stars: List<Int>,   // size in 2..12
)

/**
 * Creates a random simple bet (5 numbers + 2 stars).
 * numbers in 1..50 and stars in 1..12 without repetitions.
 * @return the random bet.
 */
fun createRandomSimpleBet() = Bet(
    numbers = allNumbers.shuffled().take(MIN_NUMBERS).sorted(),
    stars = allStars.shuffled().take(MIN_STARS).sorted()
)

/**
 * Reads a list of numbers, separated by space, from the console.
 * Ignore words that are not numbers.
 * The list returned is sorted.
 */
fun readValues(quest: String): List<Int> {
    print("$quest ? ")
    return readln()
        .split(' ')
        .mapNotNull { word -> word.toIntOrNull() }
        .sorted()
}

/**
 * Reads a bet from the console.
 * The bet may be invalid.
 */
fun readBet() = Bet(
    readValues("Números"),
    readValues("Estrelas")
)

/**
 * Checks if all the values are in the range [1..max].
 */
fun List<Int>.valuesIn(max: Int) = all { it in 1..max }

/**
 * Checks if the bet is valid:
 * - all the numbers are in  1..MAX_NUMBER
 * - all the stars are in 1..MAX_STAR
 * - size of numbers is in MIN_NUMBERS..MAX_NUMBERS
 * - size of stars is in MIN_STARS..MAX_STARS
 * - there are no repetitions in numbers and stars
 */
fun Bet.isValid(): Boolean =
    numbers.valuesIn(MAX_NUMBER) && stars.valuesIn(MAX_STAR) &&
    numbers.size in MIN_NUMBERS..MAX_NUMBERS && stars.size in MIN_STARS..MAX_STARS &&
    numbers.noRepeats() && stars.noRepeats()

/**
 * Checks if the list has no repetitions.
 */
fun List<Int>.noRepeats(): Boolean = distinct().size == size

/**
 * Reads a valid bet from the console.
 * Repeat the read until the bet is valid.
 */
fun readValidBet(): Bet {
    while(true) {
        val bet = readBet()
        if (bet.isValid()) return bet
        println("Invalid bet")
    }
}

/**
 * Gets the prize for the bet with the given key.
 * The key is randomly generated.
 * The bet is read from the console.
 */
fun main() {
    val key = /*Bet(listOf(4, 15, 33, 46, 49), listOf(2, 8) )*/ createRandomSimpleBet()
    println("Chave = $key")
    val bet = /*Bet(listOf(4, 15, 30, 46, 48), listOf(1, 8) )*/ readValidBet()
    println("Aposta = $bet")
    val prize = bet.getPrizeFor(key)
    println(prize)
    val prizeNumber = numberOf(prize)
    if (prizeNumber!=null)
        println("Ganhou o ${prizeNumber}º prémio")
}

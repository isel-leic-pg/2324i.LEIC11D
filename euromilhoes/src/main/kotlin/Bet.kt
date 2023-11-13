
const val MAX_NUMBER = 50   // Numbers in [1..50]
const val MAX_STAR = 12     // Stars in [1..12]
const val MIN_NUMBERS = 5   // Minimum numbers in a bet
const val MIN_STARS = 2     // Minimum stars in a bet

val allNumbers = (1..MAX_NUMBER)
val allStars = (1..MAX_STAR)

/**
 * Represents a bet in the Euromilhões game.
 * @property numbers the numbers of the bet.
 * @property stars the stars of the bet.
 */
data class Bet(
    val numbers: List<Int>, // size in [5..50]
    val stars: List<Int>,   // size in [2..12]
)

/**
 * Creates a random simple bet (5 numbers + 2 stars).
 * numbers in [1..50] and stars in [1..12] without repetitions.
 * @return the random bet.
 */
fun createRandomBet() = Bet(
    numbers = allNumbers.shuffled().take(MIN_NUMBERS),
    stars = allStars.shuffled().take(MIN_STARS)
)

/**
 * Show two randoms bets (only for debug).
 */
fun main() {
    println( createRandomBet() )
    println( createRandomBet() )
}

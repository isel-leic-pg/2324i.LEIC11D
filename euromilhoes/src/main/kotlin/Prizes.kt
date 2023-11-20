/**
 * Represents a prize in the EuroMillions game.
 */
data class Prize(val equalNumbers: Int, val equalStars: Int)

/**
 * Gets the prize for the bet with the given key.
 */
fun Bet.getPrizeFor(key: Bet) = Prize(
    equalNumbers = countEquals(key.numbers, numbers),
    equalStars = countEquals(key.stars, stars)
)

/**
 * Counts the number of elements of [a] that are in [b].
 */
fun countEquals(a: List<Int>, b: List<Int>): Int = a.count { it in b }
    // = a.filter { it in b }.size
/* {   var count = 0
    //for(e in a) {
    a.forEach { e ->
        if (e in b) count++
        //for(i in b)
        //    if (e==i) count++
    }
    return count
} */

/**
 * List of all the prizes in the EuroMillions game.
 */
val prizes = listOf(
    Prize(5,2), //1.º Prémio 5 Números + 2 Estrelas
    Prize(5,1), //2.º Prémio 5 Números + 1 Estrela
    Prize(5,0), //3.º Prémio 5 Números + 0 Estrelas
    Prize(4,2), //4.º Prémio 4 Números + 2 Estrelas
    Prize(4,1), //5.º Prémio 4 Números + 1 Estrela
    Prize(3,2), //6.º Prémio 3 Números + 2 Estrelas
    Prize(4,0), //7.º Prémio 4 Números + 0 Estrelas
    Prize(2,2), //8.º Prémio 2 Números + 2 Estrelas
    Prize(3,1), //9.º Prémio 3 Números + 1 Estrela
    Prize(3,0), //10.º Prémio 3 Números + 0 Estrelas
    Prize(1,2), //11.º Prémio 1 Número + 2 Estrelas
    Prize(2,1), //12.º Prémio 2 Números + 1 Estrela
    Prize(2,0), //13.º Prémio 2 Números + 0 Estrelas
)

/**
 * Gets the number of the prize or null if the prize is not in the list.
 */
fun numberOf(p: Prize): Int? =
    prizes.indexOf(p).let{ if (it==-1) null else it+1 }
//{
//    val idx = prizes.indexOf(p)
//    return if (idx==-1) null else idx+1
/*
    for(idx in 0..<prizes.size )
        if (p == prizes[idx]) return idx+1
    return null
*/
//}


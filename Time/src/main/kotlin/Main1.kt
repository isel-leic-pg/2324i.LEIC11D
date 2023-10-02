
const val SECONDS_HOUR = 3600
const val SECONDS_MINUTE = 60
val HOUR_RANGE = 0..23
val MINUTES_RANGE = 0..59
val SECONDS_RANGE = 0..59

fun main() {
    println("Tempo em horas, minutos e segundos")
    val hours = readInt("Horas")
    val minutes = readInt("Minutos")
    val seconds = readInt("Segundos")
    // println("($hours,$minutes,$seconds)")
    if ( hours in HOUR_RANGE &&
         minutes in MINUTES_RANGE &&
         seconds in SECONDS_RANGE
    ) {
        val totalSeconds =
            hours * SECONDS_HOUR +
            minutes * SECONDS_MINUTE +
            seconds
        println("Total em segundos = $totalSeconds")
    } else
        println("Tempo inválido ($hours,$minutes,$seconds)")
}
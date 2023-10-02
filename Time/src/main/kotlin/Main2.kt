
fun main() {
    println("Tempo em horas, minutos e segundos")
    val hours = readInt("Horas")
    val minutes = readInt("Minutos")
    val seconds = readInt("Segundos")
    // println("($hours,$minutes,$seconds)")
    if ( isValidTime(hours,minutes,seconds)  ) {
        val totalSeconds = timeToSeconds(hours,minutes,seconds)
        println("Total em segundos = $totalSeconds")
    } else
        println("Tempo inválido ($hours,$minutes,$seconds)")
}

private fun isValidTime(h :Int, m :Int, s: Int): Boolean =
    h in HOUR_RANGE && m in MINUTES_RANGE && s in SECONDS_RANGE

private fun timeToSeconds(h :Int, m :Int, s: Int): Int =
    h * SECONDS_HOUR + m * SECONDS_MINUTE + s
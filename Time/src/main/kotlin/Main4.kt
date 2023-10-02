
fun main() {
    val t = readTime("Tempo")
    if ( t.isValid() ) {
        val totalSeconds = t.toSeconds()
        println("Total em segundos = $totalSeconds")
    } else
        println("Tempo inválido $t")
}

private fun Time.isValid(): Boolean =
    h in HOUR_RANGE && m in MINUTES_RANGE && s in SECONDS_RANGE

private fun Time.toSeconds(): Int =
    h * SECONDS_HOUR + m * SECONDS_MINUTE + s
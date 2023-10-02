
data class Time(val h: Int, val m: Int, val s: Int = 0)

fun main() {
    val t = readTime("Tempo")
    if ( isValidTime(t) ) {
        val totalSeconds = timeToSeconds(t)
        println("Total em segundos = $totalSeconds")
    } else
//        println("Tempo inválido $t")
        println("Tempo inválido (${t.h},${t.m},${t.s})")
}

fun readTime(quest: String): Time {
    println("$quest em horas, minutos e segundos")
    val hours = readInt("Horas")
    val minutes = readInt("Minutos")
    val seconds = readInt("Segundos")
    return Time(hours,minutes,seconds)
}

private fun isValidTime(time: Time): Boolean =
    time.h in HOUR_RANGE && time.m in MINUTES_RANGE && time.s in SECONDS_RANGE

private fun timeToSeconds(time: Time): Int =
    time.h * SECONDS_HOUR + time.m * SECONDS_MINUTE + time.s

fun readString(quest: String): String {
    print("$quest ? ")
	return readln()
}

fun readInt(quest: String) = readString(quest).toInt()

fun readChar(quest: String): Char {
    val txt = readString(quest).trim()
    return if(txt.length >0) txt[0] else '?'
}

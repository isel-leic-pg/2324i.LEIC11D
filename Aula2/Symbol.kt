fun main() {
    val symb: Char = readln()[0]
	val code: Int = symb.code
	println("Letra $symb = $code")
	val upperLetter = symb in 'A'..'Z'
	  // symb >= 'A' && symb <= 'Z'
	println("maiuscula = $upperLetter")
}
fun main() {
	print("Numero ? ")
	val n = readln().toInt()
	val signal = 
		if (n>=0) "positivo" else "negativo"
	val type = 
	    if (n==0) signal+" e é zero" else signal
	println("$n é $type")
}
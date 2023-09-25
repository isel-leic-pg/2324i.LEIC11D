fun main() {
	print("Numero ? ")
	val n = readln().toInt()
	if (n>=0) {
	  println("$n é positivo")
	  if (n==0)
	    println("e também é zero")
    }		
	else 
	  println("$n é negativo")
    println("Bye.")
}
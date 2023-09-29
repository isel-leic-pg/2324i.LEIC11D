fun main() {
    val a = readInt("Valor A")
	val b = readInt("Valor B")
	val c = readInt("Valor C")
	val type = typeOfTriangle(a,b,c)
	println("O triangulo($a,$b,$c) é $type")
}

fun typeOfTriangle(x: Int, y: Int, z: Int): String =
    when {
	    x+y<=z || x+z<=y || y+z<=x || 
		          x<=0 || y<=0 || z<=0 -> "Inválido"
		x==y && y==z -> "Equilatero"
		x==y || y==z || x==z -> "Isósceles"
		else -> "Escaleno"
	}
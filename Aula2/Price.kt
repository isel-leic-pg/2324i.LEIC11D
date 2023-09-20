fun main() {
	print("Preço ? ")
	val price = (readln().toFloat() * 100).toInt()
	print("Pagamento ? ")
	val pay = (readln().toFloat() * 100).toInt()
	val remain = (pay - price) / 100.0F
	println("Demasia = $remain")
}
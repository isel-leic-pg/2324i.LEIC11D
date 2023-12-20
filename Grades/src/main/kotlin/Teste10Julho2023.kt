
data class Product(
    val name: String,
    val price: Int //Cêntimos
)

val forSale = listOf(
    Product("Caneta", 150),
    Product("Borracha", 75),
    Product("Caderno", 230),
    Product("Mochila", 3520),
// etc...
)

data class Item(
    val quantity: Int,
    val product: Product
)

data class Cmd(
    val type: Char, val quantity: Int = 1, val name: String = ""
)

fun parseCmd(line: String): Cmd {
    val words = line.split(' ').filter{ it.isNotEmpty() }
    val type = if (words.isEmpty()) '?' else words[0][0]
    return when(words.size) {
        2 -> Cmd(type, name= words[1])
        3 -> Cmd(type, words[1].toInt(), words[2])
        else -> Cmd(type)
    }
}

fun getProduct(name:String): Product? =
    forSale.firstOrNull { it.name==name }

fun Item.priceInEuros(): Float =
    (quantity * product.price) / 100f


fun removeItemIn(cart: List<Item>, name: String) =
    cart.filter { it.product.name != name }

/* Output:
3 x Borracha = 2.25€
1 x Mochila = 35.2€
3 x Caneta = 4.5€
Quantidade = 7
*/
fun listItemsOf(cart: List<Item>) {
    cart.forEach {
        println("${it.quantity} x ${it.product.name} = ${it.priceInEuros()}€")
    }
    println("Quantidade = ${cart.sumOf { it.quantity }}")
}

fun List<Item>.addItem(qt: Int, name: String): List<Item> {
    val p = getProduct(name) ?: return this
    val item = firstOrNull { it.product==p }
    return if (item==null) this + Item(qt,p)
    else this - item + Item(qt+item.quantity, p)
}

fun main() {
    var cart: List<Item> = emptyList()
    while (true) {
        print("> ")
        val cmd = parseCmd( readln() )
        when(cmd.type) {
            '+' -> cart = cart.addItem(cmd.quantity, cmd.name)
            '-' -> cart = removeItemIn(cart, cmd.name)
            '=' -> listItemsOf(cart)
            '.' -> break
            else -> println("Comando inválido")
        }
    }
    println("Total = ${cart.map{ it.priceInEuros() }.sum()}€")
}

fun mainTest() {
    val p = getProduct("Caderno")
    println(p)
    println( Item(2, forSale[2]).priceInEuros() )
    listOf(parseCmd("- Mochila"), parseCmd("=;x"), parseCmd(""), parseCmd("32 4 Xpto 123"))
        .forEach { println(it) }
}
import kotlin.math.roundToInt

fun List<Int>.averageOfBest(n:Int): Float =
    if (n<=0 || isEmpty()) 0F
    else sorted().takeLast(n).average().toFloat()

fun List<Int>.toText() :String = joinToString(",")

data class Student(val number:Int, val name:String, val evalForms:List<Int>)

fun Student.toText() :String =
    "$number - $name, fichas=(${evalForms.toText()})->${evalForms.averageOfBest(3).roundToInt()}"



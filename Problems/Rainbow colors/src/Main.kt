import java.lang.Exception
import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val colorName = input.next().toUpperCase()
    println(Rainbow.isColorInRainbow(colorName))
}

enum class Rainbow {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    companion object {
        fun isColorInRainbow(colorName: String): Boolean {
            return try {
                valueOf(colorName)
                true
            } catch (exp: Exception) {
                false
            }
        }
    }
}
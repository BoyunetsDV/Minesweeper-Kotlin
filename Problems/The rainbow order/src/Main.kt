import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val colorName = input.next().toUpperCase()
    println(Rainbow.valueOf(colorName).ordinal + 1)

}

enum class Rainbow {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val s1 = scanner.nextLine()
    val s2 = scanner.nextLine()
    val s3 = scanner.nextLine()
    val separator = scanner.nextLine()
    printConcatValues(s1, s2, s3, separator)
}

fun printConcatValues(s1: String, s2: String, s3: String, separator: String = "NO SEPARATOR") {
    val textSeparator = if (separator == "NO SEPARATOR") {
        " "
    } else {
        separator
    }
    println("$s1$textSeparator$s2$textSeparator$s3")
}
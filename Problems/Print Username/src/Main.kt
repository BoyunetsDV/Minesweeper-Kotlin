import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    greatUser(scanner.nextLine())
}

fun greatUser(name: String = "HIDDEN") {
    val userName = if (name == "HIDDEN") {
        "secret user"
    } else {
        name
    }
    println("Hello, $userName!")
}
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val parameterName = scanner.nextLine()
    val value = scanner.nextLine()
    when (parameterName) {
        "amount" -> calculateAndPrintFinalAmount(startingAmount = value.toInt())
        "percent" -> calculateAndPrintFinalAmount(yearlyPercent = value.toInt())
        "years" -> calculateAndPrintFinalAmount(years = value.toInt())
    }
}

fun calculateAndPrintFinalAmount(startingAmount: Int = 1000, yearlyPercent: Int = 5, years: Int = 10) {
    val finalAmount = (startingAmount * Math.pow(1 + yearlyPercent.toDouble() / 100, years.toDouble())).toInt()
    println(finalAmount)
}
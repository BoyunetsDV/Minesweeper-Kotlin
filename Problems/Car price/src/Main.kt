import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val parameterType = scanner.nextLine()!!
    val value = scanner.nextLine()!!.toInt()

    when (parameterType) {
        "old" -> calculateAndPrintPrice(yearsOld = value)
        "passed" -> calculateAndPrintPrice(mileage = value)
        "speed" -> calculateAndPrintPrice(maxSpeed = value)
        "auto" -> calculateAndPrintPrice(hasAutoTransmission = value)
    }
}

fun calculateAndPrintPrice(yearsOld: Int = 5,
                           mileage: Int = 100000,
                           maxSpeed: Int = 120,
                           hasAutoTransmission: Int = 0,
                           initialPrice: Int = 20000) {
    println(initialPrice - yearsOld * 2000 - mileage / 10000 * 200 + hasAutoTransmission * 1500 - (120 - maxSpeed) * 100)
}
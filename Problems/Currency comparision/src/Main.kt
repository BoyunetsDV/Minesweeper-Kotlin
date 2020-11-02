import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val firstCountryName = input.next().toUpperCase()
    val secondCountryName = input.next().toUpperCase()
    println(Countries.isCountriesCurrencyEqual(firstCountryName, secondCountryName))
}

enum class Countries(val currency: String) {
    GERMANY("Euro"),
    MALI("CFA franc"),
    DOMINICA("Eastern Caribbean dollar"),
    CANADA("Canadian dollar"),
    SPAIN("Euro"),
    AUSTRALIA("Australian dollar"),
    BRAZIL("Brazilian real"),
    SENEGAL("CFA franc"),
    FRANCE("Euro"),
    GRENADA("Eastern Caribbean dollar"),
    KIRIBATI("Australian dollar");

    companion object {
        fun isCountriesCurrencyEqual(countryName1: String, countryName2: String): Boolean {
            return try {
                val a = valueOf(countryName1)
                val b = valueOf(countryName2)
                a.currency == b.currency
            } catch (exp: Exception) {
                false
            }
        }
    }
}
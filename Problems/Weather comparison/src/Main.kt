class City(val name: String, private val defaultDegrees: Int) {
    var degrees: Int = 0
        set(value) {
            field = if (value in -92..57) {
                value
            } else {
                defaultDegrees
            }
        }

}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai", 30)
    firstCity.degrees = first
    val secondCity = City("Moscow", 5)
    secondCity.degrees = second
    val thirdCity = City("Hanoi", 20)
    thirdCity.degrees = third

    print(when {
        firstCity.degrees == secondCity.degrees || firstCity.degrees == thirdCity.degrees
                || secondCity.degrees == thirdCity.degrees -> "neither"
        firstCity.degrees < secondCity.degrees && firstCity.degrees < thirdCity.degrees -> firstCity.name
        secondCity.degrees < thirdCity.degrees -> secondCity.name
        else -> thirdCity.name
    })
}
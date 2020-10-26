import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val speed = scanner.nextLine()
    val limit = scanner.nextLine()
    checkSpeed(
            speed = speed,
            speedLimit = limit
    )
}

fun checkSpeed(speed: String, speedLimit: String = "no limit") {
    val currentSpeed = speed.toInt()
    val maxSpeed = if (speedLimit == "no limit") {
        60
    } else {
        speedLimit.toInt()
    }

    if (currentSpeed > maxSpeed) {
        println("Exceeds the limit by ${currentSpeed - maxSpeed} kilometers per hour")
    } else {
        println("Within the limit")
    }
}
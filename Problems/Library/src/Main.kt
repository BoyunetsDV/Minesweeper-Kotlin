object Math {
    fun abs(value: Int): Int {
        return if (value < 0) {
            value * -1
        } else {
            value
        }
    }

    fun abs(value: Double): Double {
        return if (value < 0) {
            value * -1
        } else {
            value
        }
    }
}
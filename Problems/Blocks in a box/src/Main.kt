class Block(val color: String) {
    object DimProperties {
        var length = 0
        var width = 0

        fun blocksInBox(boxLength: Int, boxWidth: Int): Int {
            return (boxLength / length) * (boxWidth / width)
        }
    }
}

class C {
    val b = Block.DimProperties.length
}
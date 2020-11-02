class Player(val id: Int = 0, val name: String, val hp: Int) {
    companion object {
        var defaultId = 0
        val defaultHp = 100

        fun create(name: String): Player {
            defaultId++
            return Player(id = defaultId, name = name, hp = defaultHp)
        }
    }
}
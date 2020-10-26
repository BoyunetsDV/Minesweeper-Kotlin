class Site(val address: String, val foundationYear: Int)

fun makeReddit(): Site {
    return Site(address = "reddit.com", foundationYear = 2005)
}
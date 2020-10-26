class BankAccount(_deposited: Long, _withdrawn: Long) {
    val deposited = _deposited
    val withdrawn = _withdrawn
    val balance = deposited - withdrawn
}
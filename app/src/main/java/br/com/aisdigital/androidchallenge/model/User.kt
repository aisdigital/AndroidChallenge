data class User(
    var email: String = "",
    override var token: String = "",
    override var name: String = "",
    override var age: String = "",
    override var gender: String = ""
) : UserResponse()
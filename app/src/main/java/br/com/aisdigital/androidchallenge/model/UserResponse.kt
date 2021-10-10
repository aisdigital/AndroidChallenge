abstract class UserResponse(
    open var name: String = "",
    open var age: String = "",
    open var gender: String = "",
    open var token: String = ""
) {
    val parseUser: User
        get() = User(this.name, this.age, this.gender, this.token)
}

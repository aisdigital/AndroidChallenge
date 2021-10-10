abstract class TeamResponse (
    open var name: String = "",
    open var city: String = "",
    open var conference: String = "",
    open var teamImageUrl: String = "",
    open var description: String = ""
) {
    val parseTeam: Team
        get() = Team(this.name, this.city, this.teamImageUrl, this.description)
}

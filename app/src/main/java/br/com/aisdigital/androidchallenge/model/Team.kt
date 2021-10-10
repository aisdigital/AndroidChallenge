data class Team(
    override var name: String = "",
    override var city: String = "",
    override var conference: String = "",
    override var teamImageUrl: String = "",
    override var description: String = ""
) : TeamResponse()
package br.com.aisdigital.androidchallenge.networking

import br.com.aisdigital.androidchallenge.domain.error.NoTeamsFound
import br.com.aisdigital.androidchallenge.domain.teams.Team
import br.com.aisdigital.androidchallenge.domain.user.Gender
import br.com.aisdigital.androidchallenge.domain.user.User
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

@Serializable
data class RawUser(
    val name: String,
    val age: String,
    val gender: String
)

@Serializable
data class RawAuth(
    val token: String
)

@Serializable
data class RawTeam(
    val name: String,
    val city: String,
    val conference: String,
    val teamImageUrl: String,
    val description: String
)

@Serializable(with = RawTeamsSerializer::class)
data class RawTeams(
    val teams: List<RawTeam>
)

internal fun RawUser.asUserEntity() = User(
    name = name,
    age = Integer.parseInt(age),
    gender = if(gender == Gender.FEMALE.name) Gender.FEMALE else Gender.MALE
)

internal fun RawTeams.asTeamEntityList(): List<Team> {
    return if(teams.isNotEmpty()) teams.map {
        Team(
            it.name,
            it.city,
            it.conference,
            it.teamImageUrl,
            it.description
        )
    }
    else
        throw NoTeamsFound
}

internal object RawTeamsSerializer : KSerializer<RawTeams> {

    private val serializer =  ListSerializer(RawTeam.serializer())

    override val descriptor = SerialDescriptor("br.com.aisdigital.androidchallenge.networking.RawTeams", StructureKind.CLASS)

    override fun deserialize(decoder: Decoder) = RawTeams(
        decoder.decodeSerializableValue(serializer)
    )

    override fun serialize(encoder: Encoder, value: RawTeams) {
        encoder.encodeSerializableValue(serializer, value.teams)
    }
}
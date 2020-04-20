package br.com.aisdigital.androidchallenge

import br.com.aisdigital.androidchallenge.core.domain.model.Team
import br.com.aisdigital.androidchallenge.framework.TeamEntity
import br.com.aisdigital.androidchallenge.framework.toDomainModel
import br.com.aisdigital.androidchallenge.framework.toEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TeamEntityMapperTest {

    @Test
    fun `maps Team to TeamEntity using extensions function`() {
        val team = buildTeam()
        val teamEntity = team.toEntity()

        assertAll(
            { assertEquals(team.id, teamEntity.id) },
            { assertEquals(team.name, teamEntity.name) },
            { assertEquals(team.city, teamEntity.city) },
            { assertEquals(team.conference, teamEntity.conference) },
            { assertEquals(team.teamImageUrl, teamEntity.teamImageUrl) },
            { assertEquals(team.description, teamEntity.description) }
        )
    }

    @Test
    fun `maps TeamEntity to Team using extensions function`() {
        val teamEntity = buildTeamEntity()
        val team = teamEntity.toDomainModel()

        assertAll(
            { assertEquals(teamEntity.id, team.id) },
            { assertEquals(teamEntity.name, team.name) },
            { assertEquals(teamEntity.city, team.city) },
            { assertEquals(teamEntity.conference, team.conference) },
            { assertEquals(teamEntity.teamImageUrl, team.teamImageUrl) },
            { assertEquals(teamEntity.description, team.description) }
        )
    }

    private fun buildTeam() =
        Team(
            0, // Autogenerated.
            "name",
            "city",
            "conference",
            "teamImageUrl",
            "description"
        )

    private fun buildTeamEntity() = TeamEntity(
        1,
        "name",
        "city",
        "conference",
        "teamImageUrl",
        "description"
    )
}
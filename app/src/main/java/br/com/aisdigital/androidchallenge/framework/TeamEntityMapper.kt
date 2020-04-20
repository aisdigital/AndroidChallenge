package br.com.aisdigital.androidchallenge.framework

import br.com.aisdigital.androidchallenge.core.domain.model.Team

/**
 *
 */
fun Team.toEntity() = TeamEntity(
    id = 0, //  Auto generated.
    name = name,
    city = city,
    conference = conference,
    teamImageUrl = teamImageUrl,
    description = description
)

/**
 *
 */
fun TeamEntity.toDomainModel() =
    Team(
        id = id,
        name = name,
        city = city,
        conference = conference,
        teamImageUrl = teamImageUrl,
        description = description
    )
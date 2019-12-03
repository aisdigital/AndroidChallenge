package br.com.aisdigital.androidchallenge.repository.mapper

import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.repository.dto.network.AuthDTO
import br.com.aisdigital.androidchallenge.repository.dto.network.SessionDTO
import org.mapstruct.Mapper

@Mapper
interface AuthMapper {

//    domain to DTO
    fun authToAuthDTO(user: Auth): AuthDTO

//    DTO to domain
    fun sessionDTOToSession(sessionDTO: SessionDTO): Session

}
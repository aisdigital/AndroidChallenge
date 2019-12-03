package br.com.aisdigital.androidchallenge.repository.mapper

import br.com.aisdigital.androidchallenge.domain.User
import br.com.aisdigital.androidchallenge.repository.dto.network.UserDTO
import org.mapstruct.Mapper

@Mapper
interface LoginMapper {

//    DTO to Domain
    fun userDTOToUser(userDTO: UserDTO): User
}
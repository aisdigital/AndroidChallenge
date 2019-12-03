package br.com.aisdigital.androidchallenge.repository

import android.content.Context
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.domain.User
import br.com.aisdigital.androidchallenge.repository.dto.network.AuthDTO
import br.com.aisdigital.androidchallenge.repository.dto.network.SessionDTO
import br.com.aisdigital.androidchallenge.repository.dto.network.UserDTO
import br.com.aisdigital.androidchallenge.repository.mapper.AuthMapper
import br.com.aisdigital.androidchallenge.repository.mapper.LoginMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mapstruct.factory.Mappers
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @GET("/login")
    @Headers("Content-Type: application/json")
    fun login(): Single<UserDTO>

}

class LoginRepository(context: Context, baseUrl: String) : Retrofit(context, baseUrl) {
    private val userService = retrofit.create(LoginService::class.java)

    fun login(): Single<User> {

        val domainMapper = Mappers.getMapper(LoginMapper::class.java)

        return userService.login()
            .map { dto ->
                if(dto == null) {
                    throw Exception("Falha ao efetuar login.")
                }

                domainMapper.userDTOToUser(dto)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
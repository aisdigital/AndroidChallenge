package br.com.aisdigital.androidchallenge.repository

import android.content.Context
import br.com.aisdigital.androidchallenge.domain.Auth
import br.com.aisdigital.androidchallenge.domain.Session
import br.com.aisdigital.androidchallenge.repository.dto.network.AuthDTO
import br.com.aisdigital.androidchallenge.repository.dto.network.SessionDTO
import br.com.aisdigital.androidchallenge.repository.mapper.AuthMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mapstruct.factory.Mappers
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @POST("/auth")
    @Headers("Content-Type: application/json")
    fun auth(@Body user: AuthDTO): Single<SessionDTO>

}

class AuthRepository(context: Context, baseUrl: String) : Retrofit(context, baseUrl) {
    private val authService = retrofit.create(AuthService::class.java)

    fun auth(auth: Auth): Single<Session> {

        val dto = Mappers.getMapper(AuthMapper::class.java).authToAuthDTO(auth)
        val domainMapper = Mappers.getMapper(AuthMapper::class.java)

        return authService.auth(dto)
            .map { dto ->
                dto.let {
                    if (it?.token == null) {
                        throw Exception("Falha ao efetuar login.")
                    }
                }

                domainMapper.sessionDTOToSession(dto)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
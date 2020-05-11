package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.*

class TeamRepository(private val service: TeamService) : TeamDataContract, SafeRequest() {

    override suspend fun getTeams(): DataResult<List<Team>> =
        apiRequest {
            service.getTeams()
        }

    override suspend fun postAuth(email: String, password: String): DataResult<Auth> =
        apiRequest {
            service.postAuth(email, password)
        }

    override suspend fun getLogin(token: String): DataResult<UserInfo> =
        apiRequest {
            service.getLogin(token)
        }

    suspend fun doLogin(login: Login): DataResult<UserInfo> {
        val auth = postAuth(login.email, login.password)

        if (auth.status == RequestStatus.SUCCESS) {
            getLogin(auth.data?.token.orEmpty()).run {
                if (status == RequestStatus.SUCCESS) {
                    return DataResult(
                        status = RequestStatus.SUCCESS,
                        data = data
                    )
                }
            }
        }

        return DataResult(RequestStatus.ERROR, errorMessage = "Login error")
    }
}

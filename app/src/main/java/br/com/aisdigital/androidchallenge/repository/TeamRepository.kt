package br.com.aisdigital.androidchallenge.repository

import br.com.aisdigital.androidchallenge.internal.RequestStatus
import br.com.aisdigital.androidchallenge.model.Auth
import br.com.aisdigital.androidchallenge.model.DataResult
import br.com.aisdigital.androidchallenge.model.Team
import br.com.aisdigital.androidchallenge.model.UserInfo

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

    suspend fun doLogin(email: String, password: String): DataResult<UserInfo> {
        val auth = postAuth(email, password)

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

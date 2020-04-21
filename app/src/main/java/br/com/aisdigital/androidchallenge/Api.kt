package br.com.aisdigital.androidchallenge

import br.com.aisdigital.androidchallenge.login.data.model.LoggedInUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    /* @GET("search/repositories?q=language:Java&sort=stars")
     fun getRepositories(@Query("page") page : String) : Observable<RepositorieResponse>


  var url  = 'https://dn84g8ux7c.execute-api.us-east-2.amazonaws.com/prod/agilFinishRent?'
                             url += 'data=' + encodeURIComponent(JSON.stringify(item))

     @GET("repos/{creator}/{repositorie}/pulls")
     fun getPullRequests(@Path("creator") creator : String, @Path("repositorie") repositorie : String) : Observable<List<PullRequestResponse>>

    @GET("getAgilRents")
    fun loadContracts(): Observable<List<Contract>>

    @GET("agilFinishRent")
    fun finish(@Query("data", encoded = true) contract: String): Observable<String>

    @GET("getAgilTools")
    fun loadTools(): Observable<List<Tool>>

    @GET("changeToolsStatus")
    fun changeStatus(@Query("data", encoded = true) encode: String, @Query("status") status: String): Observable<String>

    @GET("getAgilContainerClients")
    fun loadClients(): Observable<List<Client>>
*/

    @POST("auth")
    fun auth(@Query("user_email ") email: String, @Query("user_password ") password : String): Observable<LoggedInUser>
}
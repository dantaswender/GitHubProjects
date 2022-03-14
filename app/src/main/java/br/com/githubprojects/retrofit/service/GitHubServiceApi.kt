package br.com.githubprojects.retrofit.service

import br.com.githubprojects.model.ListaRepositorios
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubServiceApi {

    @GET("search/repositories?q=language:kotlin&sort=stars&page")
    fun getBuscarListas(
        @Query("page") page: Int
    ): Call<ListaRepositorios>

}
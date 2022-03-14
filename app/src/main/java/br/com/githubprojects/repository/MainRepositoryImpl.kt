package br.com.githubprojects.repository

import br.com.githubprojects.retrofit.service.GitHubServiceApi
import br.com.githubprojects.model.ListaRepositorios
import br.com.githubprojects.retrofit.webclient.doRequest
import retrofit2.awaitResponse

class MainRepositoryImpl(private val serviceApi: GitHubServiceApi): Repository{
    override suspend fun buscarLista(page: Int): ResultApi<ListaRepositorios> {
        return doRequest(serviceApi.getBuscarListas(page).awaitResponse())
    }

}
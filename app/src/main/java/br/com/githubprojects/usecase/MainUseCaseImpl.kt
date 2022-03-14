package br.com.githubprojects.usecase

import br.com.githubprojects.repository.Repository
import br.com.githubprojects.model.ListaRepositorios
import br.com.githubprojects.repository.ResultApi

class MainUseCaseImpl(private val repository: Repository): MainUseCase {

    override suspend fun buscarLista(page: Int): ResultApi<ListaRepositorios> =
        repository.buscarLista(page)

}
package br.com.githubprojects.usecase

import br.com.githubprojects.model.ListaRepositorios
import br.com.githubprojects.repository.ResultApi

interface MainUseCase {
    suspend fun buscarLista(page: Int): ResultApi<ListaRepositorios>
}
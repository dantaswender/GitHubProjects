package br.com.githubprojects.repository

import br.com.githubprojects.model.ListaRepositorios

interface Repository {
    suspend fun buscarLista(page: Int): ResultApi<ListaRepositorios>
}
package br.com.githubprojects.ui.viewmodel.state

import br.com.githubprojects.model.Item

sealed class MainState {
    data class GetListaRepositorios(val lista: List<Item>): MainState()
    data class IsErro(val error: String): MainState()
}

sealed class MainInteracao{
    data class ItemClicado(val item: Item): MainInteracao()
}
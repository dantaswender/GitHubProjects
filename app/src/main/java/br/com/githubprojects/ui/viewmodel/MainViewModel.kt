package br.com.githubprojects.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.githubprojects.usecase.MainUseCase
import br.com.githubprojects.model.ListaRepositorios
import br.com.githubprojects.repository.ResultApi
import br.com.githubprojects.ui.viewmodel.state.MainState
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: MainUseCase) : ViewModel() {


    private val state: MutableLiveData<MainState> = MutableLiveData()
    val viewState: LiveData<MainState> = state

    private var page = 1

    fun buscarListaRepositorios() {
        viewModelScope.launch {
            val resultApi = useCase.buscarLista(page)
            onResponse(resultApi)
        }
    }

    private fun onResponse(resultApi: ResultApi<ListaRepositorios>) {
        when {
            resultApi.isSucesso() -> {
                resultApi.value?.let {
                    page++
                    val item = it.items
                    state.value = MainState.GetListaRepositorios(item)
                    state.value = MainState.GetListaRepositorios(item)
                    //Incluir um load
                }
            }
            resultApi.isErro() -> {
                resultApi.erro?.let {
                    state.value = MainState.IsErro(it)
                }
            }
        }
    }
}
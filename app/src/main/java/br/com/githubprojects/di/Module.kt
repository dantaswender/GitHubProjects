package br.com.githubprojects.di

import br.com.githubprojects.repository.MainRepositoryImpl
import br.com.githubprojects.repository.Repository
import br.com.githubprojects.retrofit.AppRetrofit
import br.com.githubprojects.retrofit.service.GitHubServiceApi
import br.com.githubprojects.ui.viewmodel.MainViewModel
import br.com.githubprojects.usecase.MainUseCase
import br.com.githubprojects.usecase.MainUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceApi: GitHubServiceApi = AppRetrofit.service

val repositoryModule = module {
    single<Repository> { MainRepositoryImpl(serviceApi) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }

    factory<MainUseCase> { MainUseCaseImpl(get()) }
}
package biz.ei6.eluvplages.di

import biz.ei6.eluvplages.data.FilePlageRepository

import biz.ei6.eluvplages.presentation.PlageRepository

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

import biz.ei6.eluvplages.presentation.PlageVM

val appModule = module {

        single<PlageRepository> { FilePlageRepository(get()) }
        viewModel { PlageVM(get()) }

}



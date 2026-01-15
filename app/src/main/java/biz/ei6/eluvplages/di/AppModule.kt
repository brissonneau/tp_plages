package biz.ei6.eluvplages.di


import biz.ei6.eluvplages.data.RoomPlageRepository
import biz.ei6.eluvplages.datasource.AppDatabase
import biz.ei6.eluvplages.datasource.DatabaseProvider
import biz.ei6.eluvplages.presentation.PlageRepository

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

import biz.ei6.eluvplages.presentation.PlageVM

val appModule = module {

    // DB (singleton)
    single { DatabaseProvider.get(get()) }

    // DAO (optionnel mais propre)
    single { get<AppDatabase>().plageDao() }

    // Repository
    single<PlageRepository> { RoomPlageRepository(get()) }
    // get() ici = JudoEventDao

    viewModel { PlageVM(get()) }

}



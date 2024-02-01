package com.example.ecosystemalpha.app

import com.example.ecosystemalpha.model.NewsRepository
import com.example.ecosystemalpha.model.NewsRequest
import com.example.ecosystemalpha.model.NewsSharedPreferences
import com.example.ecosystemalpha.viewmodel.NewsViewModel
import org.koin.dsl.module
import com.example.ecosystemalpha.model.NewsApi
import com.example.ecosystemalpha.model.NewsDao


val viewModelModule = module {
    single { NewsViewModel(get()) }
}

val repositoryModule = module {
    single<NewsRepository> { NewsRepository(get(), get()) }
}

val localBase = module {
    single<NewsDao> { NewsSharedPreferences(get()) }
}

val netBase = module {
    single<NewsApi> { NewsRequest() }
}

package br.com.aisdigital.androidchallenge.di

import androidx.room.Room
import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamCache
import br.com.aisdigital.androidchallenge.core.data.interfaces.TeamRemote
import br.com.aisdigital.androidchallenge.core.data.interfaces.UserCache
import br.com.aisdigital.androidchallenge.core.data.interfaces.UserRemote
import br.com.aisdigital.androidchallenge.core.data.repository.TeamDefaultRepository
import br.com.aisdigital.androidchallenge.core.data.repository.UserDefaultRepository
import br.com.aisdigital.androidchallenge.core.domain.interfaces.TeamRepository
import br.com.aisdigital.androidchallenge.core.domain.interfaces.UserRepository
import br.com.aisdigital.androidchallenge.core.domain.usecase.*
import br.com.aisdigital.androidchallenge.framework.*
import br.com.aisdigital.androidchallenge.ui.LoginViewModel
import br.com.aisdigital.androidchallenge.ui.TeamDetailsViewModel
import br.com.aisdigital.androidchallenge.ui.TeamListViewModel
import br.com.aisdigital.androidchallenge.ui.UserProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val allModules = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AndroidChallengeDatabase::class.java,
            "challenge-db"
        )
            .build()
    }
    single { get<AndroidChallengeDatabase>().teamDao() }
    single { provideRetrofit().create(AndroidChallengeApi::class.java) }
    // We can replace with other implementations without change data flow logic.
    single<TeamCache> { TeamDatabaseCache(get()) }
    single<TeamRemote> { TeamRetrofitRemote(get()) }
    single<TeamRepository> {
        TeamDefaultRepository(
            get(),
            get()
        )
    }
    single<UserCache> { UserInMemoryCache() }
    single<UserRemote> { UserRetrofitRemote(get()) }
    single<UserRepository> {
        UserDefaultRepository(
            get(),
            get()
        )
    }
    single { GetAllTeamsUseCase(get()) }
    single { GetTeamUseCase(get()) }
    single { GetUserProfileUseCase(get()) }
    single { LoginUseCase(get()) }
    single { CheckLoginStatusUseCase(get()) }
    viewModel { TeamListViewModel(get()) }
    viewModel { TeamDetailsViewModel(get()) }
    viewModel { UserProfileViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl("https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
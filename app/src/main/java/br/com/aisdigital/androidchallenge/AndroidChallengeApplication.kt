package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.data.*
import br.com.aisdigital.androidchallenge.domain.AuthenticateUsecase
import br.com.aisdigital.androidchallenge.domain.ClearLoginLocalDataUsecase
import br.com.aisdigital.androidchallenge.domain.LoginUsecase
import br.com.aisdigital.androidchallenge.domain.ValidateEmailUsecase
import br.com.aisdigital.androidchallenge.presentation.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class AndroidChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val module = module {
            viewModel { LoginViewModel(loginUsecase = get(), clearLoginLocalDataUsecase =  get(), authenticateUsecase =  get(), validateEmailUsecase =  get()) }
            factory { ClearLoginLocalDataUsecase(loginRepository = get()) }
            factory { ValidateEmailUsecase() }
            factory { LoginUsecase(loginRepository = get()) }
            factory { AuthenticateUsecase(loginRepository = get()) }
            factory { LoginRepository(loginRemoteDatasource = get(), sharedPreferencesLocalDatasource = get()) }
            single<ILoginRemoteDatasource> { LoginRemoteDatasource(retrofitClient = get()) }
            single { RetrofitClient() }
            single<ISharedPreferencesLocalDatasource> { SharedPreferencesLocalDatasource(androidContext()) }
        }
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AndroidChallengeApplication)
            // Load modules
            modules(module)
        }
    }
}

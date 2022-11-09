package br.com.aisdigital.androidchallenge

import android.app.Application
import br.com.aisdigital.androidchallenge.data.BaseRemoteDatasource
import br.com.aisdigital.androidchallenge.data.ILoginRemoteDatasource
import br.com.aisdigital.androidchallenge.data.LoginRemoteDatasource
import br.com.aisdigital.androidchallenge.data.LoginRepository
import br.com.aisdigital.androidchallenge.domain.AuthenticateUsecase
import br.com.aisdigital.androidchallenge.domain.LoginUsecase
import br.com.aisdigital.androidchallenge.domain.ValidateEmailUsecase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class AndroidChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val module = module {
            factory { ValidateEmailUsecase() }
            factory { LoginUsecase(loginRepository = get()) }
            factory { AuthenticateUsecase(loginRepository = get()) }
            factory { LoginRepository(loginRemoteDatasource = get()) }
            single<ILoginRemoteDatasource> { LoginRemoteDatasource(baseRemoteDatasource = get()) }
            single { BaseRemoteDatasource() }
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

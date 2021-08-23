package br.com.aisdigital.androidchallenge.modules

import android.content.Context
import androidx.room.Room
import br.com.aisdigital.androidchallenge.AndroidChallenge
import br.com.aisdigital.androidchallenge.networking.TeamsApi
import br.com.aisdigital.androidchallenge.persistence.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AndroidChallenge {
        return app as AndroidChallenge
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://c526ee5a-a2fb-446c-b242-d5fa13592a1a.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTeamsApi(retrofit: Retrofit): TeamsApi {
        return retrofit.create(TeamsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : MainDatabase {
        return Room.databaseBuilder(
            appContext,
            MainDatabase::class.java,
            "main_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: MainDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideTeamDao(db: MainDatabase) = db.getTeamDao()

    @Singleton
    @Provides
    fun provideSchedulerIO() = SchedulerProvider()

    open class SchedulerProvider {
        fun computation() = Schedulers.computation()
        fun ui() = AndroidSchedulers.mainThread()
        fun io() = Schedulers.io()
    }
}
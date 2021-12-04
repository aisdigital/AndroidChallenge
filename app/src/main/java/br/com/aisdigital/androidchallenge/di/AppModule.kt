package br.com.aisdigital.androidchallenge.di

import android.content.Context
import androidx.room.Room
import br.com.aisdigital.androidchallenge.data.local.TeamDao
import br.com.aisdigital.androidchallenge.data.local.AndroidChallengeDatabase
import br.com.aisdigital.androidchallenge.data.local.UserDao
import br.com.aisdigital.androidchallenge.data.remote.AndroidChallengeApi
import br.com.aisdigital.androidchallenge.repositories.LoginRepository
import br.com.aisdigital.androidchallenge.repositories.TeamRepository
import br.com.aisdigital.androidchallenge.utilities.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideLoginRepository(dao: UserDao, api: AndroidChallengeApi): LoginRepository {
        return LoginRepository(dao, api)
    }

    @Singleton
    @Provides
    fun provideTeamRepository(dao: TeamDao, api: AndroidChallengeApi): TeamRepository {
        return TeamRepository(dao, api)
    }

    @Singleton
    @Provides
    fun provideTeamDatabase(@ApplicationContext context: Context): AndroidChallengeDatabase {
        return Room.databaseBuilder(
            context,
            AndroidChallengeDatabase::class.java,
            "team_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTeamDao(database: AndroidChallengeDatabase): TeamDao {
        return database.teamDao()
    }

    @Provides
    fun provideUserDao(database: AndroidChallengeDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideAndroidChallengeApi(): AndroidChallengeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(AndroidChallengeApi::class.java)
    }
}
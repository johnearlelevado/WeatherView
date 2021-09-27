package com.example.challenge.dagger.hilt

import android.content.Context
import androidx.room.Room
import com.example.challenge.BuildConfig
import com.example.challenge.repository.Repository
import com.example.challenge.repository.RepositoryImpl
import com.example.challenge.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(db: AppDatabase):Repository
            = RepositoryImpl(db)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app:Context): AppDatabase = Room
        .databaseBuilder(app,
            AppDatabase::class.java,
            "weatherapp.db")
        .allowMainThreadQueries()
        .build()
}
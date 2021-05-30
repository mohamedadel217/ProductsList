package com.example.data.di

import dagger.Provides
import android.content.Context
import com.example.data.source.local.PhonesDataBase
import dagger.Module
import dagger.hilt.InstallIn
 import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context) =
        PhonesDataBase.getDatabase(application)

    @Provides
    @Singleton
    fun providePhonesDao(phonesDataBase: PhonesDataBase) = phonesDataBase.phonesDao()
}
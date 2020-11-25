package com.onesimo.nyathi.hogwarts.di

import com.onesimo.nyathi.hogwarts.repo.*
import com.onesimo.nyathi.hogwarts.repo.implementation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun provideHouseRepository(impl: HouseRepositoryImpI): HouseRepository

    @Binds
    abstract fun provideSpellRepository(impl: SpellRepositoryImpl): SpellRepository
}
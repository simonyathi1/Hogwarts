package com.onesimo.nyathi.hogwarts.di

import com.onesimo.nyathi.hogwarts.repo.CharacterRepository
import com.onesimo.nyathi.hogwarts.repo.HouseRepository
import com.onesimo.nyathi.hogwarts.repo.SortingHatRepository
import com.onesimo.nyathi.hogwarts.repo.SpellRepository
import com.onesimo.nyathi.hogwarts.repo.implementation.CharacterRepositoryImpl
import com.onesimo.nyathi.hogwarts.repo.implementation.HouseRepositoryImpI
import com.onesimo.nyathi.hogwarts.repo.implementation.SortingHatRepositoryImpl
import com.onesimo.nyathi.hogwarts.repo.implementation.SpellRepositoryImpl
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

    @Binds
    abstract fun provideSortingRepository(impl: SortingHatRepositoryImpl): SortingHatRepository
}
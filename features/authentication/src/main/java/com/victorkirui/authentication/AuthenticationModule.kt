package com.victorkirui.authentication

import com.victorkirui.authentication.domain.EmailAuthentication
import com.victorkirui.authentication.domain.usecases.ResetPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AuthenticationModule {

    @ViewModelScoped
    @Provides
    fun provideEmailAuthentication(): EmailAuthentication{
        return EmailAuthentication()
    }

    @ViewModelScoped
    @Provides
    fun providePasswordResetUseCase(): ResetPasswordUseCase{
        return ResetPasswordUseCase()
    }
}
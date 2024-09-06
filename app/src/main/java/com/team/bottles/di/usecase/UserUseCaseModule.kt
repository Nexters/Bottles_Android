package com.team.bottles.di.usecase

import com.team.bottles.core.domain.user.usecase.GetContactsUseCase
import com.team.bottles.core.domain.user.usecase.GetContactsUseCaseImpl
import com.team.bottles.core.domain.user.usecase.ReportUserUseCase
import com.team.bottles.core.domain.user.usecase.ReportUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class UserUseCaseModule {

    @Binds
    abstract fun bindsReportUserUseCase(
        useCaseImpl: ReportUserUseCaseImpl
    ): ReportUserUseCase

    @Binds
    abstract fun bindsGetContactsUseCase(
        useCaseImpl: GetContactsUseCaseImpl
    ): GetContactsUseCase

}
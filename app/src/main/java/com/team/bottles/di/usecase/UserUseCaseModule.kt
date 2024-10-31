package com.team.bottles.di.usecase

import com.team.bottles.core.domain.user.usecase.GetContactsUseCase
import com.team.bottles.core.domain.user.usecase.GetContactsUseCaseImpl
import com.team.bottles.core.domain.user.usecase.GetNotificationPermissionStatusUseCase
import com.team.bottles.core.domain.user.usecase.GetNotificationPermissionStatusUseCaseImpl
import com.team.bottles.core.domain.user.usecase.GetSettingNotificationsUseCase
import com.team.bottles.core.domain.user.usecase.GetSettingNotificationsUseCaseImpl
import com.team.bottles.core.domain.user.usecase.ReportUserUseCase
import com.team.bottles.core.domain.user.usecase.ReportUserUseCaseImpl
import com.team.bottles.core.domain.user.usecase.UpdateActivateMatchingUseCase
import com.team.bottles.core.domain.user.usecase.UpdateActivateMatchingUseCaseImpl
import com.team.bottles.core.domain.user.usecase.UpdateBlockingContactsUseCase
import com.team.bottles.core.domain.user.usecase.UpdateBlockingContactsUseCaseImpl
import com.team.bottles.core.domain.user.usecase.UpdateCurrentSystemNotificationStateUseCase
import com.team.bottles.core.domain.user.usecase.UpdateCurrentSystemNotificationStateUseCaseImpl
import com.team.bottles.core.domain.user.usecase.UpdateSettingNotificationUseCase
import com.team.bottles.core.domain.user.usecase.UpdateSettingNotificationUseCaseImpl
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

    @Binds
    abstract fun bindsUpdateBlockingContactsUseCase(
        useCaseImpl: UpdateBlockingContactsUseCaseImpl
    ): UpdateBlockingContactsUseCase

    @Binds
    abstract fun bindsGetSettingNotificationsUseCase(
        useCaseImpl: GetSettingNotificationsUseCaseImpl
    ): GetSettingNotificationsUseCase

    @Binds
    abstract fun bindsUpdateSettingNotificationUseCase(
        useCaseImpl: UpdateSettingNotificationUseCaseImpl
    ): UpdateSettingNotificationUseCase

    @Binds
    abstract fun bindsUpdateActivateMatchingUseCase(
        useCaseImpl: UpdateActivateMatchingUseCaseImpl
    ): UpdateActivateMatchingUseCase

    @Binds
    abstract fun bindsGetNotificationPermissionStatusUseCase(
        useCaseImpl: GetNotificationPermissionStatusUseCaseImpl
    ): GetNotificationPermissionStatusUseCase

    @Binds
    abstract fun bindsUpdateCurrentSystemNotificationStateUseCase(
        useCaseImpl: UpdateCurrentSystemNotificationStateUseCaseImpl
    ): UpdateCurrentSystemNotificationStateUseCase

}
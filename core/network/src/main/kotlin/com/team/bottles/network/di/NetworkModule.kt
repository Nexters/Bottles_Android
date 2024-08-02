package com.team.bottles.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.team.bottles.core.datastore.datasource.TokenDataSource
import com.team.bottles.network.BuildConfig
import com.team.bottles.network.datasource.AuthDataSource
import com.team.bottles.network.interceptor.AuthAuthenticator
import com.team.bottles.network.interceptor.AuthHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        coerceInputValues = true
        prettyPrint = true
    }

    @Singleton
    @Provides
    fun provideJsonConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    /* Provides Interceptor*/

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    )

    @Singleton
    @Provides
    fun provideAuthHeaderInterceptor(tokenDataSource: TokenDataSource): Interceptor =
        AuthHeaderInterceptor(tokenDataSource)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenDataSource: TokenDataSource, authDataSource: AuthDataSource): Authenticator =
        AuthAuthenticator(tokenDataSource, authDataSource)

    /* Provides OkHttpClient*/

    @Singleton
    @Provides
    @Named("DefaultClient")
    fun provideDefaultOkHttpClient(
        logInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(logInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    @Named("AuthenticatedClient")
    fun provideAuthOkHttpClient(
        logInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor,
        authenticator: Authenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .authenticator(authenticator)
            .addNetworkInterceptor(logInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    /* Provides Retrofit */

    @Singleton
    @Provides
    @Named("Default")
    fun provideDefaultRetrofit(
        @Named("DefaultClient") client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BOTTLES_DEBUG_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

    @Singleton
    @Provides
    @Named("Secure")
    fun provideRetrofit(
        @Named("AuthenticatedClient") client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BOTTLES_DEBUG_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

}

package com.soethan.melodystream.di

import android.content.Context
import com.soethan.melodystream.ContentResolverHelper
import com.soethan.melodystream.data.repository.AudioRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    fun provideContentResolverHelper(@ApplicationContext context: Context): ContentResolverHelper {
        return ContentResolverHelper(context = context)
    }


    fun provideAudioRepository(contentResolverHelper: ContentResolverHelper): AudioRepository {
        return AudioRepository(contentResolverHelper = contentResolverHelper)
    }
}
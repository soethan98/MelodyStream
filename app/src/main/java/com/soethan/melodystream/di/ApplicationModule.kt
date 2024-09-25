package com.soethan.melodystream.di

import android.content.Context
import com.soethan.melodystream.media.ContentResolverHelper
import com.soethan.melodystream.data.mapper.AudioMapper
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.data.repository.AlbumRepository
import com.soethan.melodystream.data.repository.ArtistRepository
import com.soethan.melodystream.data.repository.IArtistRepository
import com.soethan.melodystream.media.IAlbum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    fun provideContentResolverHelper(@ApplicationContext context: Context): ContentResolverHelper {
        return ContentResolverHelper(context = context)
    }


    @Provides
    @Singleton
    fun provideAlbumRepository(@ApplicationContext context: Context): IAlbum {
        return AlbumRepository(context = context)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(@ApplicationContext context: Context): IArtistRepository {
        return ArtistRepository(context = context)
    }


    fun provideAudioRepository(
        contentResolverHelper: ContentResolverHelper,
        audioMapper: AudioMapper,
        albumRepository: IAlbum,
        artistRepository: IArtistRepository
    ): AudioRepository {
        return AudioRepository(
            contentResolverHelper = contentResolverHelper,
            albumRepository,
            audioMapper,
            artistRepository = artistRepository
        )
    }
}
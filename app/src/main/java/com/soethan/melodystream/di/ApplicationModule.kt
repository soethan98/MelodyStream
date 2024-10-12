package com.soethan.melodystream.di

import android.content.Context
import com.soethan.melodystream.media.ContentResolverHelper
import com.soethan.melodystream.data.mapper.AudioMapper
import com.soethan.melodystream.data.repository.AudioRepository
import com.soethan.melodystream.data.repository.AlbumRepository
import com.soethan.melodystream.data.repository.ArtistRepository
import com.soethan.melodystream.data.repository.GenreRepository
import com.soethan.melodystream.data.repository.IAlbumRepository
import com.soethan.melodystream.data.repository.IArtistRepository
import com.soethan.melodystream.data.repository.IGenreRepository
import com.soethan.melodystream.data.repository.ITrackRepository
import com.soethan.melodystream.data.repository.TrackRepository
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
    fun provideTrackRepository(@ApplicationContext context: Context): ITrackRepository {
        return TrackRepository(context = context)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(
        trackRepository: ITrackRepository,
        @ApplicationContext context: Context
    ): IAlbumRepository {
        return AlbumRepository(trackRepository = trackRepository, context = context)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(
        @ApplicationContext context: Context,
        albmRepo: IAlbumRepository,
        trackRepository: ITrackRepository
    ): IArtistRepository {
        return ArtistRepository(context = context, albumRepository = albmRepo,trackRepository)
    }


    @Provides
    @Singleton
    fun provideGenreRepository(@ApplicationContext context: Context): IGenreRepository {
        return GenreRepository(context = context)
    }


    fun provideAudioRepository(
        contentResolverHelper: ContentResolverHelper,
        audioMapper: AudioMapper,
        artistRepository: IArtistRepository,
        genreRepository: IGenreRepository
    ): AudioRepository {
        return AudioRepository(
            contentResolverHelper = contentResolverHelper,
            genreRepository,
            audioMapper,

            artistRepository = artistRepository
        )
    }
}
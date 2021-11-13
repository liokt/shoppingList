package com.example.lio.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.lio.shoppinglist.data.local.ShoppingDao
import com.example.lio.shoppinglist.data.local.ShoppingItemDatabase
import com.example.lio.shoppinglist.data.remote.responses.PixebayAPI
import com.example.lio.shoppinglist.other.Constants.BASE_URL
import com.example.lio.shoppinglist.other.Constants.DATABASE_NAME
import com.example.lio.shoppinglist.repositories.DefaultShoppingRepository
import com.example.lio.shoppinglist.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixebayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixebayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl( "https://pixabay.com")
            .build()
            .create(PixebayAPI::class.java)
    }

}
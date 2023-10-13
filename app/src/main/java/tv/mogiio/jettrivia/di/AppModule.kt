package tv.mogiio.jettrivia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tv.mogiio.jettrivia.network.QuestionApi
import tv.mogiio.jettrivia.respository.QuestionRepository
import tv.mogiio.jettrivia.util.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Singleton
  @Provides
  fun provideQuestionRepository(api: QuestionApi) = QuestionRepository(api)

  @Singleton
  @Provides
  fun provideQuestionApi(): QuestionApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(QuestionApi::class.java)
  }

}
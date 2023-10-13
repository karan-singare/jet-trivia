package tv.mogiio.jettrivia.network

import retrofit2.http.GET
import tv.mogiio.jettrivia.model.Question
import javax.inject.Singleton

@Singleton
interface QuestionApi {
  @GET("world.json")
  suspend fun getAllQuestions(): ArrayList<Question>
}
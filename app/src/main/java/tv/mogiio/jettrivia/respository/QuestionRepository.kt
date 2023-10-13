package tv.mogiio.jettrivia.respository

import android.util.Log
import tv.mogiio.jettrivia.data.DataOrException
import tv.mogiio.jettrivia.model.Question
import tv.mogiio.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
  private val dataOrException = DataOrException<ArrayList<Question>, Boolean, Exception>()

  suspend fun getAllQuestions(): DataOrException<ArrayList<Question>, Boolean, Exception> {
    try {
      dataOrException.loading = true
      val questions = api.getAllQuestions()
      dataOrException.data = questions
      if (dataOrException.data.toString().isNotEmpty()) {
        dataOrException.loading = false
      }
    } catch (exception: Exception) {
      dataOrException.e = exception
      Log.d("Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
    }
    return dataOrException
  }


}
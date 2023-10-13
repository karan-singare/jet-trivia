package tv.mogiio.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tv.mogiio.jettrivia.data.DataOrException
import tv.mogiio.jettrivia.model.Question
import tv.mogiio.jettrivia.respository.QuestionRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository) :
  ViewModel() {

  val data: MutableState<DataOrException<ArrayList<Question>, Boolean, Exception>> =
    mutableStateOf(
      DataOrException(null, true, Exception(""))
    )

  init {
    getAllQuestions()
  }

  private fun getAllQuestions() {
    viewModelScope.launch {
      data.value.loading = true
      data.value = repository.getAllQuestions()
      if (data.value.data.toString().isNotEmpty()) {
        data.value.loading = false
      }
    }
  }

  public fun getTotalQuestionsCount(): Int? {
    return data.value?.data?.size
  }

}
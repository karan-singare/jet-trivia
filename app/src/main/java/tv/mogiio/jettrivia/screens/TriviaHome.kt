package tv.mogiio.jettrivia.screens

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import tv.mogiio.jettrivia.screens.components.QuestionComponent

@Composable
fun TriviaHome(viewModel: QuestionsViewModel = hiltViewModel()) {
  Questions(viewModel)
}

@Composable
fun Questions(viewModel: QuestionsViewModel) {
  val questions = viewModel.data.value.data?.toMutableList()

  val questionIndex = remember {
    mutableStateOf(0)
  }

  if (viewModel.data.value?.loading == true) {
    CircularProgressIndicator(modifier = Modifier.size(110.dp), color = Color.Blue)
  } else {

    val question = try {
      questions?.get(questionIndex.value)
    } catch (e: Exception) {
      null
    }

    if (questions != null) {
      QuestionComponent(
        question = question!!,
        questionIndex = questionIndex,
        viewModel = viewModel) {
        questionIndex.value = questionIndex.value + 1
      }
    }

  }
}

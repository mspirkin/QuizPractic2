package com.avtocontrol.quizpractic2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.navOptions
import com.avtocontrol.quizpractic2.MyViewModel
import com.avtocontrol.quizpractic2.R
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionFinishBinding
import kotlin.system.exitProcess


class QuestionFinish : Fragment(R.layout.fragment_question_finish) {
    private var questionFinish: FragmentQuestionFinishBinding? = null
    private val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQuestionFinishBinding.bind(view)
        questionFinish = binding
        var i = 1 //Счетчик вопросов
        var count = 0 //Результат теста
        var str =""
        //Массив с правильными ответами
        viewModel.correctAnswer[viewModel.questionNameFirst] = viewModel.correctResponseQuestionFirst
        viewModel.correctAnswer[viewModel.questionNameTwo] = viewModel.correctResponseQuestionTwo
        viewModel.correctAnswer[viewModel.questionNameThree] = viewModel.correctResponseQuestionThree
        viewModel.correctAnswer[viewModel.questionNameFour] = viewModel.correctResponseQuestionFour
        viewModel.correctAnswer[viewModel.questionNameFife] = viewModel.correctResponseQuestionFife

        // Заполнение массива c ответами
        viewModel.result[viewModel.questionNameFirst] = viewModel.answerQuestionFirstText
        viewModel.result[viewModel.questionNameTwo] = viewModel.answeQuestionTwoText
        viewModel.result[viewModel.questionNameThree] = viewModel.answerQuestionThreeText
        viewModel.result[viewModel.questionNameFour] = viewModel.answerQuestionFourText
        viewModel.result[viewModel.questionNameFife] = viewModel.answerQuestionFifeText

        //Проверка результата
        for ((keyCorrect, valueCorrect) in viewModel.correctAnswer) {
            for ((keyResult, valueResult) in viewModel.result) {
                if (keyCorrect == keyResult && valueCorrect == valueResult) {
                    count++
                }
            }
        }

        //вывод результата на экран
        binding.tvFinish.text = "Ваш результат: $count из ${viewModel.result.size}"

        //Подготовка строки для вывода через share
        for ((keyResult, valueResult) in viewModel.result) {
                str += "$i) $keyResult \n Ваш ответ: $valueResult \n \n"
            i++

        }

        //Слушатель кнопки Share
        binding.btnShare.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Quiz Result")
                putExtra(Intent.EXTRA_TEXT, "${binding.tvFinish.text} \n \n $str \n ")
            }
            startActivity(Intent.createChooser(sendIntent, null))

        }
        //Слушатель кнопки Exit
        binding.btnFinish.setOnClickListener {
            exitProcess(1)
        }
        //Слушатель кнопки Назад
        binding.btnBack.setOnClickListener {
            viewModel.clearRadioButton()
            findNavController().navigate(R.id.action_questionFinish_to_questionFirst, null, navOptions { popUpTo(R.id.questionFirst, popUpToBuilder = {inclusive = true}) })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        questionFinish = null
    }

}
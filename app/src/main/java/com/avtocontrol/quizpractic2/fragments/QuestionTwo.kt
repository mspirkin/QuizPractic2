package com.avtocontrol.quizpractic2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.avtocontrol.quizpractic2.MyViewModel
import com.avtocontrol.quizpractic2.R
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionTwoBinding


class QuestionTwo : Fragment(R.layout.fragment_question_two) {
    private var questionTwo: FragmentQuestionTwoBinding? = null
    private  val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQuestionTwoBinding.bind(view)
        questionTwo = binding

        //Начальное состояние кнопки Next
        binding.btnQuestionTwoNext.isEnabled = false
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbarQuestionTwo.setupWithNavController(findNavController(),appBarConfiguration)
        if (viewModel.answerQuestionTwo != 0) {
            if (binding.rgQuestionTwo.checkedRadioButtonId == -1) {
                binding.rgQuestionTwo.check(viewModel.answerQuestionTwo)
                binding.btnQuestionTwoNext.isEnabled = true
            }
        }
        //Правильный ответ
        viewModel.correctResponseQuestionTwo = binding.rbThreeQuestionTwo.text.toString()
        //Название вопроса
        viewModel.questionNameTwo = binding.tvQuestionTwo.text.toString()

        //Слушатель выбора вариантов ответа
        binding.rgQuestionTwo.setOnCheckedChangeListener { group, checkedId ->
            when(group.checkedRadioButtonId > -1) {
                true -> {
                    binding.btnQuestionTwoNext.isEnabled = true
                     when(checkedId) {
                        R.id.rb_first_answer_question_two -> {
                            viewModel.answerQuestionTwo = binding.rbFirstAnswerQuestionTwo.id
                            viewModel.answeQuestionTwoText = binding.rbFirstAnswerQuestionTwo.text.toString()
                        }
                        R.id.rb_two_question_two -> {
                            viewModel.answerQuestionTwo = binding.rbTwoQuestionTwo.id
                            viewModel.answeQuestionTwoText = binding.rbTwoQuestionTwo.text.toString()
                        }
                        R.id.rb_three_question_two -> {
                            viewModel.answerQuestionTwo = binding.rbThreeQuestionTwo.id
                            viewModel.answeQuestionTwoText = binding.rbThreeQuestionTwo.text.toString()
                        }
                        R.id.rb_four_question_two -> {
                            viewModel.answerQuestionTwo = binding.rbFourQuestionTwo.id
                            viewModel.answeQuestionTwoText = binding.rbFourQuestionTwo.text.toString()
                        }
                        else -> {
                            viewModel.answerQuestionTwo = binding.rbFifeQuestionTwo.id
                            viewModel.answeQuestionTwoText = binding.rbFifeQuestionTwo.text.toString()
                        }
                    }
                }
            }
        }

        //Слушатель кнопки Next
        binding.btnQuestionTwoNext.setOnClickListener {
          findNavController().navigate(R.id.action_questionTwo_to_questionThree)
        }
        //Слушатель кнопки Previous
        binding.btnQuestionTwoPrevious.setOnClickListener {
          findNavController().navigate(R.id.action_questionTwo_to_questionFirst, null, navOptions { popUpTo(R.id.questionFirst, popUpToBuilder = {inclusive = true}) })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        questionTwo = null
    }
}
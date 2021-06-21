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
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionFourBinding


class QuestionFour : Fragment(R.layout.fragment_question_four) {
    private var questionFour: FragmentQuestionFourBinding? = null
    private val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQuestionFourBinding.bind(view)
        questionFour = binding
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbarQuestionFour.setupWithNavController(findNavController(), appBarConfiguration)

        //Начальное состояние кнопки Next
        binding.btnQuestionFourNext.isEnabled = false

        if (viewModel.answerQuestionFour != 0) {
            if (binding.rgQuestionFour.checkedRadioButtonId == -1) {
                binding.rgQuestionFour.check(viewModel.answerQuestionFour)
                binding.btnQuestionFourNext.isEnabled = true
            }
        }
        //Правильный ответ
        viewModel.correctResponseQuestionFour = binding.rbFirstAnswerQuestionFour.text.toString()
        //Название вопроса
        viewModel.questionNameFour = binding.tvQuestionFour.text.toString()
        //Слушатель выбора вариантов ответа
        binding.rgQuestionFour.setOnCheckedChangeListener { group, checkedId ->
            when(group.checkedRadioButtonId > -1) {
                true -> {
                    binding.btnQuestionFourNext.isEnabled = true
                     when(checkedId) {
                        R.id.rb_first_answer_question_four -> {
                            viewModel.answerQuestionFour = binding.rbFirstAnswerQuestionFour.id
                            viewModel.answerQuestionFourText = binding.rbFirstAnswerQuestionFour.text.toString()
                        }
                        R.id.rb_two_question_four -> {
                            viewModel.answerQuestionFour = binding.rbTwoQuestionFour.id
                            viewModel.answerQuestionFourText = binding.rbTwoQuestionFour.text.toString()
                        }
                        R.id.rb_three_question_four -> {
                            viewModel.answerQuestionFour = binding.rbThreeQuestionFour.id
                            viewModel.answerQuestionFourText = binding.rbThreeQuestionFour.text.toString()
                        }
                        R.id.rb_four_question_four -> {
                            viewModel.answerQuestionFour = binding.rbFourQuestionFour.id
                            viewModel.answerQuestionFourText = binding.rbFourQuestionFour.text.toString()
                        }
                        else -> {
                            viewModel.answerQuestionFour = binding.rbFifeQuestionFour.id
                            viewModel.answerQuestionFourText = binding.rbFifeQuestionFour.text.toString()
                        }
                    }
                }
            }
        }

        //Слушатель кнопки Next
        binding.btnQuestionFourNext.setOnClickListener {
            findNavController().navigate(R.id.action_questionFour_to_questionFive)
        }
        //Слушатель кнопки Previous
        binding.btnQuestionFourPrevious.setOnClickListener {
            findNavController().navigate(R.id.action_questionFour_to_questionThree, null, navOptions { popUpTo(R.id.questionThree, popUpToBuilder = {inclusive = true}) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        questionFour = null
    }

}
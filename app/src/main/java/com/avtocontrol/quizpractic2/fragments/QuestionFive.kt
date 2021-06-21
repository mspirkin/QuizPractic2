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
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionFiveBinding


class QuestionFive : Fragment(R.layout.fragment_question_five) {
    private var questionFive: FragmentQuestionFiveBinding? = null
    private val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQuestionFiveBinding.bind(view)
        questionFive = binding
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbarQuestionFife.setupWithNavController(findNavController(), appBarConfiguration)
        //Начальное состояние кнопки Submit
        binding.btnQuestionFiveSubmit.isEnabled = false

        if (viewModel.answerQuestionFife != 0) {
            if (binding.rgQuestionFife.checkedRadioButtonId == -1) {
                binding.rgQuestionFife.check(viewModel.answerQuestionFife)
                binding.btnQuestionFiveSubmit.isEnabled = true
            }
        }
        //Правильный ответ
        viewModel.correctResponseQuestionFife = binding.rbFifeQuestionFife.text.toString()
        //Название вопроса
        viewModel.questionNameFife = binding.tvQuestionFife.text.toString()
        //Слушатель выбора вариантов ответа
        binding.rgQuestionFife.setOnCheckedChangeListener { group, checkedId ->
            when(group.checkedRadioButtonId > -1) {
                true -> {
                    binding.btnQuestionFiveSubmit.isEnabled = true
                     when(checkedId) {
                        R.id.rb_first_answer_question_fife -> {
                            viewModel.answerQuestionFife = binding.rbFirstAnswerQuestionFife.id
                            viewModel.answerQuestionFifeText = binding.rbFirstAnswerQuestionFife.text.toString()
                        }
                        R.id.rb_two_question_fife -> {
                            viewModel.answerQuestionFife = binding.rbTwoQuestionFife.id
                            viewModel.answerQuestionFifeText = binding.rbTwoQuestionFife.text.toString()
                        }
                        R.id.rb_three_question_fife -> {
                            viewModel.answerQuestionFife = binding.rbThreeQuestionFife.id
                            viewModel.answerQuestionFifeText = binding.rbThreeQuestionFife.text.toString()
                        }
                        R.id.rb_four_question_fife -> {
                            viewModel.answerQuestionFife = binding.rbFourQuestionFife.id
                            viewModel.answerQuestionFifeText = binding.rbFourQuestionFife.text.toString()
                        }
                        else -> {
                            viewModel.answerQuestionFife = binding.rbFifeQuestionFife.id
                            viewModel.answerQuestionFifeText = binding.rbFifeQuestionFife.text.toString()
                        }
                    }
                }
            }
        }

        //Слушатель кнопки Submit
        binding.btnQuestionFiveSubmit.setOnClickListener {
           findNavController().navigate(R.id.action_questionFive_to_questionFinish)
        }

        //Слушатель кнопки Previous
        binding.btnQuestionFivePrevious.setOnClickListener {
            findNavController().navigate(R.id.action_questionFive_to_questionFour, null, navOptions { popUpTo(R.id.questionFour, popUpToBuilder = {inclusive = true}) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        questionFive = null
    }

}
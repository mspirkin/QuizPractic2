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
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionThreeBinding


class QuestionThree : Fragment(R.layout.fragment_question_three) {
    private var questionThree: FragmentQuestionThreeBinding? = null
    private val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentQuestionThreeBinding.bind(view)
        questionThree = binding
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbarQuestionThree.setupWithNavController(findNavController(),appBarConfiguration)
        //Начальное состояние кнопки Next
        binding.btnQuestionThreeNext.isEnabled = false

        if (viewModel.answerQuestionThree != 0) {
            if (binding.rgQuestionThree.checkedRadioButtonId == -1) {
                binding.rgQuestionThree.check(viewModel.answerQuestionThree)
                binding.btnQuestionThreeNext.isEnabled = true
            }
        }
         //Правильный ответ
        viewModel.correctResponseQuestionThree = binding.rbFourQuestionThree.text.toString()
        //Название вопроса
        viewModel.questionNameThree = binding.tvQuestionThree.text.toString()
        //Слушатель выбора вариантов ответа
        binding.rgQuestionThree.setOnCheckedChangeListener { group, checkedId ->
            when(group.checkedRadioButtonId > -1) {
                true -> {
                    binding.btnQuestionThreeNext.isEnabled = true
                     when(checkedId) {
                        R.id.rb_first_answer_question_three -> {
                            viewModel.answerQuestionThree = binding.rbFirstAnswerQuestionThree.id
                            viewModel.answerQuestionThreeText = binding.rbFirstAnswerQuestionThree.text.toString()
                        }
                        R.id.rb_two_question_three -> {
                            viewModel.answerQuestionThree = binding.rbTwoQuestionThree.id
                            viewModel.answerQuestionThreeText = binding.rbTwoQuestionThree.text.toString()
                        }
                        R.id.rb_three_question_three -> {
                            viewModel.answerQuestionThree = binding.rbThreeQuestionThree.id
                            viewModel.answerQuestionThreeText = binding.rbThreeQuestionThree.text.toString()
                        }
                        R.id.rb_four_question_three -> {
                            viewModel.answerQuestionThree =  binding.rbFourQuestionThree.id
                            viewModel.answerQuestionThreeText = binding.rbFourQuestionThree.text.toString()
                        }
                        else -> {
                            viewModel.answerQuestionThree = binding.rbFifeQuestionThree.id
                            viewModel.answerQuestionThreeText = binding.rbFifeQuestionThree.text.toString()
                        }
                    }
                }
            }
        }

        //Слушатель кнопки Next
        binding.btnQuestionThreeNext.setOnClickListener {
            findNavController().navigate(R.id.action_questionThree_to_questionFour)
        }
        //Слушатель кнопки Previous
        binding.btnQuestionThreePrevious.setOnClickListener {
            findNavController().navigate(R.id.action_questionThree_to_questionTwo, null, navOptions { popUpTo(R.id.questionTwo, popUpToBuilder = {inclusive =true}) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        questionThree = null

    }

}
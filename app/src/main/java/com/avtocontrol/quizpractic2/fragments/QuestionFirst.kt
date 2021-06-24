package com.avtocontrol.quizpractic2.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.avtocontrol.quizpractic2.MyViewModel
import com.avtocontrol.quizpractic2.R
import com.avtocontrol.quizpractic2.databinding.FragmentQuestionFirstBinding


class QuestionFirst : Fragment(R.layout.fragment_question_first) {
    private var questionFirst: FragmentQuestionFirstBinding? = null
    private val viewModel: MyViewModel by navGraphViewModels(R.id.nav_graph)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binging = FragmentQuestionFirstBinding.bind(view)
        questionFirst = binging

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binging.toolbarQuestionFirst.setupWithNavController(findNavController(), appBarConfiguration)



        //Начальное состояние кнопок Next и Previous
        binging.btnQuestionFirstNext.isEnabled = false
        binging.btnQuestionFirstPrevious.isEnabled = false

        if (viewModel.answerQuestionFirst != 0) {
            if (binging.rgQuestionFirst.checkedRadioButtonId == -1) {
                binging.rgQuestionFirst.check(viewModel.answerQuestionFirst)
                binging.btnQuestionFirstNext.isEnabled = true
            }
        }
        viewModel.correctResponseQuestionFirst = binging.rbFirstAnswerQuestionFirst.text.toString()
        viewModel.questionNameFirst = binging.tvQuestionFirst.text.toString()

        //Слушатель выбора вариантов ответа
        binging.rgQuestionFirst.setOnCheckedChangeListener { group, checkedId ->
            when (group.checkedRadioButtonId > -1) {
                true -> {
                    binging.btnQuestionFirstNext.isEnabled = true
                      when (checkedId) {
                        R.id.rb_first_answer_question_first -> {
                            viewModel.answerQuestionFirst = binging.rbFirstAnswerQuestionFirst.id
                            viewModel.answerQuestionFirstText = binging.rbFirstAnswerQuestionFirst.text.toString()
                        }
                        R.id.rb_two_question_first -> {
                            viewModel.answerQuestionFirst = binging.rbTwoQuestionFirst.id
                            viewModel.answerQuestionFirstText = binging.rbTwoQuestionFirst.text.toString()
                        }
                        R.id.rb_three_question_first -> {
                            viewModel.answerQuestionFirst = binging.rbThreeQuestionFirst.id
                            viewModel.answerQuestionFirstText = binging.rbThreeQuestionFirst.text.toString()
                        }
                        R.id.rb_four_question_first -> {
                            viewModel.answerQuestionFirst = binging.rbFourQuestionFirst.id
                            viewModel.answerQuestionFirstText = binging.rbFourQuestionFirst.text.toString()
                        }
                        else -> {
                            viewModel.answerQuestionFirst = binging.rbFifeQuestionFirst.id
                            viewModel.answerQuestionFirstText = binging.rbFifeQuestionFirst.text.toString()
                        }
                    }
                }
            }
        }
        //Слушатель кнопки Next
        binging.btnQuestionFirstNext.setOnClickListener {
            findNavController().navigate(R.id.action_questionFirst_to_questionTwo)
        }

    }






        override fun onDestroyView() {
            super.onDestroyView()
            questionFirst = null
        }


}



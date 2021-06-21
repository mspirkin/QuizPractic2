package com.avtocontrol.quizpractic2

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    //Правильные ответы на вопрос
    var correctResponseQuestionFirst: String =""
    var correctResponseQuestionTwo: String =""
    var correctResponseQuestionThree: String = ""
    var correctResponseQuestionFour: String = ""
    var correctResponseQuestionFife: String = ""
    //Массив с правильными ответами
    val correctAnswer = mutableMapOf<String, String>()

    //Текст вопросов
    var questionNameFirst = ""
    var questionNameTwo = ""
    var questionNameThree = ""
    var questionNameFour = ""
    var questionNameFife = ""
    //Текст ответов пользователя
    var answerQuestionFirstText = ""
    var answeQuestionTwoText = ""
    var answerQuestionThreeText = ""
    var answerQuestionFourText = ""
    var answerQuestionFifeText = ""

    //Ответы пользователя на вопрос
    var answerQuestionFirst: Int = 0
    var answerQuestionTwo: Int = 0
    var answerQuestionThree: Int = 0
    var answerQuestionFour: Int = 0
    var answerQuestionFife: Int = 0
    // Массив с ответами пользователя
    val result = mutableMapOf<String, String>()
    //Очистка RadioButton
    fun clearRadioButton() {
        answerQuestionFirst = 0
        answerQuestionTwo = 0
        answerQuestionThree = 0
        answerQuestionFour = 0
        answerQuestionFife = 0
    }


}
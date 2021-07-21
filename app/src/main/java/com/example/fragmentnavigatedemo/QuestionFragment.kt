package com.example.fragmentnavigatedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.fragmentnavigatedemo.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private lateinit var binding : FragmentQuestionBinding
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    var questionIndex = 0
    var score: Int = 0

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "what is mco stand for ?",
            answers = listOf(
                "Movement Control Order",
                "Multiple Choice Order",
                "More Coffee Order "
            )
        ),
        Question(
            text = "What is FMCO?",
            answers = listOf(
                "Full Movement Control Oder",
                "Fun Movement Control Oder",
                "Forever Movement Control Oder"
            )
        )
    )
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()

        answers.shuffle()

        binding.tvQuestion.text = currentQuestion.text
        binding.optA.text = answers[0]
        binding.optB.text = answers[1]
        binding.optC.text = answers[2]

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        setQuestion()

        binding.btnNext.setOnClickListener(){
            val checkedId = binding.radioGroup2.checkedRadioButtonId

            if (checkedId != -1) {
                var answerIndex = 0

                when (checkedId) {
                    R.id.optA -> answerIndex = 0
                    R.id.optB -> answerIndex = 1
                    R.id.optC -> answerIndex = 2
                }
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    score += 1
                }

                if (questionIndex < 1) {

                    questionIndex += 1
                    binding.radioGroup2.clearCheck()
                    setQuestion()

                } else {
                    // todo:: navigate to thankyou fragment
                    val percentage : Float = (score /2.0F) * 100
                    val action= QuestionFragmentDirections.actionQuestionFragmentToThankYouFragment(percentage)
                    Navigation.findNavController(it).navigate(action)


                }
            }else{
                Toast.makeText(context, "please select answer", Toast.LENGTH_LONG).show()
            }


        }
        return binding.root
    }

}
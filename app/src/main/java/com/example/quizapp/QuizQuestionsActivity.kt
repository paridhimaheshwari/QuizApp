package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : ComponentActivity(), View.OnClickListener {

    private var mCurrentPos:Int = 1
    private var mQuestionsList:ArrayList<Question>?= null
    private var mSelectedOptionPosition:Int = 0
//    private var mCorrectAnswer: Int =

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
//        in this version: new activity(empty) doesn't make xml file for the layout, need to create on your own
        mQuestionsList = Constants.getQuestions()
//        Log.i("Question_size", "${mQuestionsList.size}")
        setQuestion()

        val textView_tvo1: TextView = findViewById(R.id.tv_option_one) as TextView
        textView_tvo1.setOnClickListener(this)
        val textView_tvo2: TextView = findViewById(R.id.tv_option_two) as TextView
        textView_tvo2.setOnClickListener(this)
        val textView_tvo3: TextView = findViewById(R.id.tv_option_three) as TextView
        textView_tvo3.setOnClickListener(this)
        val textView_tvo4: TextView = findViewById(R.id.tv_option_four) as TextView
        textView_tvo4.setOnClickListener(this)


    }
    private fun setQuestion(){
        mCurrentPos = 1
        val question = mQuestionsList!![mCurrentPos-1]
        //        since id is 1, but the index of the first question would obviously be 0

        defaultOptionsView()

        var quiz_binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        quiz_binding.progressBar.progress = mCurrentPos

        val textView: TextView = findViewById(R.id.tv_progress) as TextView
        textView.text = "$mCurrentPos"+ "/" +quiz_binding.progressBar.max

        //        quiz_binding.tv_progress.text = "$currentPosition"+ "/" +quiz_binding.progressBar.max
        val textView_ques: TextView = findViewById(R.id.tv_question) as TextView
        textView_ques.text = question!!.question
//        quiz_binding.tv_question.text = question!!.question

        val imageView_ques: ImageView = findViewById(R.id.iv_image) as ImageView
        imageView_ques.setImageResource(question.image)

        val textView_tvo1: TextView = findViewById(R.id.tv_option_one) as TextView
        textView_tvo1.text = question.optionOne
        val textView_tvo2: TextView = findViewById(R.id.tv_option_two) as TextView
        textView_tvo2.text = question.optionTwo
        val textView_tvo3: TextView = findViewById(R.id.tv_option_three) as TextView
        textView_tvo3.text = question.optionThree
        val textView_tvo4: TextView = findViewById(R.id.tv_option_four) as TextView
        textView_tvo4.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        val textView_tvo1: TextView = findViewById(R.id.tv_option_one) as TextView
        options.add(0, textView_tvo1)

        val textView_tvo2: TextView = findViewById(R.id.tv_option_two) as TextView
        options.add(1, textView_tvo2)

        val textView_tvo3: TextView = findViewById(R.id.tv_option_three) as TextView
        options.add(2, textView_tvo3)

        val textView_tvo4: TextView = findViewById(R.id.tv_option_four) as TextView
        options.add(3, textView_tvo4)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

        }
    }

    override fun onClick(v: View?) {
        val textView_tvo1: TextView = findViewById(R.id.tv_option_one) as TextView
        val textView_tvo2: TextView = findViewById(R.id.tv_option_two) as TextView
        val textView_tvo3: TextView = findViewById(R.id.tv_option_three) as TextView
        val textView_tvo4: TextView = findViewById(R.id.tv_option_four) as TextView
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionView(textView_tvo1, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(textView_tvo2, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(textView_tvo3, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(textView_tvo4, 4)
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum:Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    QuizAppTheme {
        Greeting2("Android")
    }
}
package com.example.mathparty;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3, btn_answer4, btn_answer5, btn_answer6, btn_answer7;
    TextView tv_score, tv_score2, tv_questions, tv_questions2, tv_bottommessage, tv_bottommessage2, tv_timer, tv_timer2;
    ProgressBar prog_timer, prog_timer2;
    Game g = new Game();
    Game g2 = new Game();

    int secRemaining = 30, secRemaining2 = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secRemaining--;
            tv_timer.setText(secRemaining + "sec");
            prog_timer.setProgress(30 - secRemaining);
        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_bottommessage.setText("Time is up! " + g.getNumberCorrect() + " / " + (g.getTotalQuestions() - 1));
            final Handler handeler = new Handler();
            handeler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 4000);

        }
    };
    CountDownTimer timer2 = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secRemaining2--;
            tv_timer2.setText(secRemaining2 + "sec");
            prog_timer2.setProgress(30 - secRemaining2);
        }

        @Override
        public void onFinish() {
            btn_answer4.setEnabled(false);
            btn_answer5.setEnabled(false);
            btn_answer6.setEnabled(false);
            btn_answer7.setEnabled(false);
            tv_bottommessage2.setText("Time is up! " + g2.getNumberCorrect() + " / " + (g2.getTotalQuestions() - 1));


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_ans0);
        btn_answer1 = findViewById(R.id.btn_ans1);
        btn_answer2 = findViewById(R.id.btn_ans2);
        btn_answer3 = findViewById(R.id.btn_ans3);
        btn_answer4 = findViewById(R.id.btn_ans4);
        btn_answer5 = findViewById(R.id.btn_ans5);
        btn_answer6 = findViewById(R.id.btn_ans6);
        btn_answer7 = findViewById(R.id.btn_ans7);

        tv_score = findViewById(R.id.tv_score);
        tv_score2 = findViewById(R.id.tv_score2);
        tv_bottommessage = findViewById(R.id.tv_bottommessage);
        tv_bottommessage2 = findViewById(R.id.tv_bottommessage2);
        tv_questions = findViewById(R.id.tv_questions);
        tv_questions2 = findViewById(R.id.tv_questions2);
        tv_timer = findViewById(R.id.tv_timer);
        tv_timer2 = findViewById(R.id.tv_timer2);

        prog_timer = findViewById(R.id.prog_timer);
        prog_timer2 = findViewById(R.id.prog_timer2);

        tv_timer.setText("0 sec");
        tv_timer2.setText("0 sec");
        tv_questions.setText("");
        tv_questions2.setText("");
        tv_bottommessage.setText("Press go");
        tv_bottommessage2.setText("Press go");
        tv_score.setText("0pts");
        tv_score2.setText("0pts");

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                secRemaining = 30;
                secRemaining2 = 30;
                g = new Game();
                g2 = new Game();
                nextTurn();
                nextTurn2();
                timer.start();
                timer2.start();
            }
        };

        View.OnClickListener answerButtonClickListenner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                tv_score.setText(g.getScore() + "pts");
                nextTurn();

            }
        };

        View.OnClickListener answerButtonClickListenner2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g2.checkAnswer(answerSelected);
                tv_score2.setText(g2.getScore() + "pts");
                nextTurn2();

            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        btn_answer0.setOnClickListener(answerButtonClickListenner2);
        btn_answer1.setOnClickListener(answerButtonClickListenner2);
        btn_answer2.setOnClickListener(answerButtonClickListenner2);
        btn_answer3.setOnClickListener(answerButtonClickListenner2);
        btn_answer4.setOnClickListener(answerButtonClickListenner);
        btn_answer5.setOnClickListener(answerButtonClickListenner);
        btn_answer6.setOnClickListener(answerButtonClickListenner);
        btn_answer7.setOnClickListener(answerButtonClickListenner);
    }

    private void nextTurn() {
        //create a new question
        //set text on answer buttons.
        //enable answer buttons
        //start the timer
        g.makeNewQuestion();
        int[] answer = g.getCurrentQuestion().getAnswerArray();

        btn_answer4.setText(Integer.toString(answer[0]));
        btn_answer5.setText(Integer.toString(answer[1]));
        btn_answer6.setText(Integer.toString(answer[2]));
        btn_answer7.setText(Integer.toString(answer[3]));

        btn_answer4.setEnabled(true);
        btn_answer5.setEnabled(true);
        btn_answer6.setEnabled(true);
        btn_answer7.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tv_bottommessage.setText(g.getNumberCorrect() + " / " + (g.getTotalQuestions() - 1));
    }

    private void nextTurn2() {
        //create a new question
        //set text on answer buttons.
        //enable answer buttons
        //start the timer
        g2.makeNewQuestion();
        int[] answer2 = g2.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answer2[0]));
        btn_answer1.setText(Integer.toString(answer2[1]));
        btn_answer2.setText(Integer.toString(answer2[2]));
        btn_answer3.setText(Integer.toString(answer2[3]));


        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions2.setText(g2.getCurrentQuestion().getQuestionPhrase());
        tv_bottommessage2.setText(g2.getNumberCorrect() + " / " + (g2.getTotalQuestions() - 1));
    }
}



package ke.co.laptopcity.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView questions_recycler;
    private int correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions_recycler=findViewById(R.id.questions_recycler);
        questions_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<Question> all_qs=new ArrayList<Question>();
        Question q=new Question("What does the short name Sat stand for among week days","Saturday");
        Question q2=new Question("Which holiday is famous during the month of December",new String[]{"Christmas","Labour day","Easter","New year"},"Christmas");
        Question q3=new Question("Which is the first day of the week","Sunday");
        Question q4=new Question("The first Leap year after 2000","2004");
        Question q5=new Question("Which month comes after January",new String[]{"February","March","April","May"},"February");

        all_qs.add(q);
        all_qs.add(q2);
        all_qs.add(q3);
        all_qs.add(q4);
        all_qs.add(q5);

        final QuestionsAdapter qAdapt=new QuestionsAdapter(all_qs,getApplicationContext());
        questions_recycler.setAdapter(qAdapt);

        AppCompatButton b=findViewById(R.id.cross_check);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct=0;wrong=0;
                for(Question q:qAdapt.getAllQuestions()){
                    if(q.isAnswerCorrect())
                        correct+=1;
                    else
                        wrong+=1;
                }

                showScore();

            }
        });

    }

    public void showScore(){
        TextView correct_text=findViewById(R.id.correct_text);
        TextView wrong_text=findViewById(R.id.wrong_text);

        correct_text.setText(""+correct);
        wrong_text.setText(""+wrong);
    }
}

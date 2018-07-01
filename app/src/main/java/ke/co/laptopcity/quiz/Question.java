package ke.co.laptopcity.quiz;


import java.util.ArrayList;
import java.util.List;

public class Question{

    //Variable to hold whether it is a multi choice question or just a typable
    public int type;

    private String user_answer="";
    private String answer;
    public List<Answer> answers=new ArrayList<Answer>();

    private String question;


    public Question(String question,String answer){
        this.type=0;
        this.question=question;
        this.answer=answer;
    }

    public Question(String question,String[] choices,String answer){
        this.type=1;
        this.question=question;
        this.answer=answer;
        for(String s:choices){
            Answer a=new Answer(s,s.equals(answer));
            answers.add(a);
        }
    }


    public Boolean isAnswerCorrect(){
        if(this.type==0){
            if(this.user_answer.equals(this.answer))
                return true;
        }else{

            for(Answer answer : this.answers){
                if(answer.getAnswer().equals(this.answer))
                    return true;
            }

        }

        return false;
    }

    public String getQuestion() {
        return question;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }


    public class Answer{
        private boolean is_correct_choice;
        private String answer;

        public Answer(String answer,Boolean is_correct_choice){
            this.answer=answer;
            this.is_correct_choice=is_correct_choice;
        }

        public String getAnswer() {
            return answer;
        }

        public boolean isIsCorrectChoice() {
            return is_correct_choice;
        }
    }

}
package ke.co.laptopcity.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Question> all;
    private Context context;

    public QuestionsAdapter(List<Question> incoming, Context myContext){
        this.all=incoming;
        this.context = myContext;
    }

    public static class TypedQuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView question_text;
        public TextView question_index;
        public AppCompatEditText answer_text;
        public TypedQuestionViewHolder(View itemView) {
            super(itemView);
            question_text=itemView.findViewById(R.id.question_text);
            question_index=itemView.findViewById(R.id.question_index);
            answer_text=itemView.findViewById(R.id.answer_text);
        }
    }

    public static class ChoiceQuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView question_index;
        public TextView question_text;
        public RadioGroup radio_group;
        public ChoiceQuestionViewHolder(View itemView) {
            super(itemView);
            question_text=itemView.findViewById(R.id.question_text);
            question_index=itemView.findViewById(R.id.question_index);
            radio_group=itemView.findViewById(R.id.radio_group);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0) {
            View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_text, parent, false);
            return new TypedQuestionViewHolder(item_view);
        }else{
            View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_choice, parent, false);
            return new ChoiceQuestionViewHolder(item_view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Question question=all.get(position);
        switch (holder.getItemViewType()){
            case 0:
                TypedQuestionViewHolder holder_typed = (TypedQuestionViewHolder)holder;
                holder_typed.question_text.setText(question.getQuestion());
                holder_typed.question_index.setText("Q."+(position+1));
                holder_typed.answer_text.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                    @Override
                    public void afterTextChanged(Editable editable) { }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        question.setUser_answer(charSequence.toString());
                    }


                });
                break;
            case 1:
                ChoiceQuestionViewHolder holder_choice = (ChoiceQuestionViewHolder)holder;
                holder_choice.question_text.setText(question.getQuestion());
                holder_choice.question_index.setText("Q."+(position+1));

                //Populate answers
                for(int i=0;i<4;i++){
                    try{
                        RadioButton r=(RadioButton)holder_choice.radio_group.getChildAt(i);
                        r.setText(question.answers.get(i).getAnswer());
                    }catch (Exception e){}
                }

                holder_choice.radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId)
                    {
                        // This will get the radiobutton that has changed in its check state
                        RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                        // This puts the value (true/false) into the variable
                        question.setUser_answer(checkedRadioButton.getText().toString());

                    }
                });

                break;
        }


    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public List<Question> getAllQuestions() {
        return all;
    }

    @Override
    public int getItemViewType(int position) {
        return all.get(position).type;
    }
}

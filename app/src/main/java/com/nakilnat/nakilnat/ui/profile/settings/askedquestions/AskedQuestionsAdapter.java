package com.nakilnat.nakilnat.ui.profile.settings.askedquestions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.nakilnat.nakilnat.R;
import java.util.List;

public class AskedQuestionsAdapter extends RecyclerView.Adapter<AskedQuestionsAdapter.AskedQuestionVH> {

    List<AskedQuestionsList> askedQuestionsLists;

    public AskedQuestionsAdapter(List<AskedQuestionsList> askedQuestionsLists) {
        this.askedQuestionsLists = askedQuestionsLists;
    }

    @NonNull
    @Override
    public AskedQuestionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asked_question, parent, false);
        return new AskedQuestionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AskedQuestionVH holder, int position) {
        AskedQuestionsList askedQuestion = askedQuestionsLists.get(position);
        holder.titleTextView.setText(askedQuestion.getQuestion());
        holder.answerTextView.setText(askedQuestion.getAnswer());

        boolean isExpanded = askedQuestionsLists.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return askedQuestionsLists.size();
    }

    class AskedQuestionVH extends RecyclerView.ViewHolder {

        ConstraintLayout expandableLayout;
        TextView titleTextView, answerTextView;
        RelativeLayout questionLayout;

        public AskedQuestionVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.question_title);
            answerTextView = itemView.findViewById(R.id.answer);
            expandableLayout = itemView.findViewById(R.id.asked_question_expandable_layout);
            questionLayout = itemView.findViewById(R.id.question_title_layout);

            questionLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AskedQuestionsList askedQuestion = askedQuestionsLists.get(getAdapterPosition());
                    askedQuestion.setExpanded(!askedQuestion.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

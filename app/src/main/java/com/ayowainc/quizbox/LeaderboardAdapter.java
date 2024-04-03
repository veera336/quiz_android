package com.ayowainc.quizbox;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayowainc.quizbox.models.User;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<User> userList;

    public LeaderboardAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.usernameTextView.setText(user.getUsername());
        holder.logicalTextView.setText(String.valueOf(user.getLogicalScore()));
        holder.aptitudeTextView.setText(String.valueOf(user.getAptitudeScore()));
        holder.fractionalTextView.setText(String.valueOf(user.getFractionalScore()));
        holder.reasoningTextView.setText(String.valueOf(user.getReasoningScore()));
        holder.scoreTextView.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView logicalTextView;
        TextView aptitudeTextView;
        TextView fractionalTextView;
        TextView reasoningTextView;
        TextView scoreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            logicalTextView = itemView.findViewById(R.id.logicalTextView);
            aptitudeTextView = itemView.findViewById(R.id.aptitudeTextView);
            fractionalTextView = itemView.findViewById(R.id.fractionalTextView);
            reasoningTextView = itemView.findViewById(R.id.reasoningTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
        }
    }
}


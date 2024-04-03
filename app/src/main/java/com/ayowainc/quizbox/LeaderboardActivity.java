package com.ayowainc.quizbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ayowainc.quizbox.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;
    private List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        recyclerView = findViewById(R.id.leaderboardRecyclerView);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        adapter = new LeaderboardAdapter(userList);
        recyclerView.setAdapter(adapter);

        DatabaseReference leaderboardRef = FirebaseDatabase.getInstance().getReference().child("The QuizBoxers");

        leaderboardRef.orderByChild("score").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                String username = "";
                long score = 0L;
                int logicalScore = 0;
                int aptitudeScore = 0;
                int fractionalScore = 0;
                int reasoningScore = 0;

                if (dataSnapshot.hasChild("username")) {
                    username = dataSnapshot.child("username").getValue(String.class);
                }

                if (dataSnapshot.hasChild("score")) {
                    score = dataSnapshot.child("score").getValue(Long.class);
                }

                if (dataSnapshot.hasChild("logicalScore")) {
                    logicalScore = dataSnapshot.child("logicalScore").getValue(Integer.class);
                }

                if (dataSnapshot.hasChild("aptitudeScore")) {
                    aptitudeScore = dataSnapshot.child("aptitudeScore").getValue(Integer.class);
                }

                if (dataSnapshot.hasChild("fractionalScore")) {
                    fractionalScore = dataSnapshot.child("fractionalScore").getValue(Integer.class);
                }

                if (dataSnapshot.hasChild("reasoningScore")) {
                    reasoningScore = dataSnapshot.child("reasoningScore").getValue(Integer.class);
                }


                userList.add(new User(username,score,logicalScore,fractionalScore,aptitudeScore,reasoningScore));

                Collections.sort(userList, new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        // Sort in descending order
                        return Long.compare(u2.getScore(), u1.getScore());
                    }
                });

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle changes in score if needed
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removal of scores if needed
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle movement of scores if needed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("LeaderboardActivity", "Error fetching leaderboard data", databaseError.toException());
                Toast.makeText(LeaderboardActivity.this, "Error fetching leaderboard data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
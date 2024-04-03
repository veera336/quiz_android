package com.ayowainc.quizbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ayowainc.quizbox.Category_Levels.All_Knowledge.knowledgeLevel1;

import com.ayowainc.quizbox.Category_Levels.All_Knowledge.knowledgeLevel2;
import com.ayowainc.quizbox.Category_Levels.All_Knowledge.knowledgeLevel3;
import com.ayowainc.quizbox.Category_Levels.Marketing.MarketingBeginnerActivity;

public class custom_levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_levels);
        Button level1,level2,level3;

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), knowledgeLevel1.class);
                startActivity(BG);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), knowledgeLevel2.class);
                startActivity(BG);
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), knowledgeLevel3.class);
                startActivity(BG);
            }
        });

    }
}
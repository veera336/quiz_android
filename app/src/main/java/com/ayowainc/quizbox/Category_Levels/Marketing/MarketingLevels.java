package com.ayowainc.quizbox.Category_Levels.Marketing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ayowainc.quizbox.Category_Levels.Marketing.MarketingBeginnerLevel1;
import com.ayowainc.quizbox.Category_Levels.Marketing.MarketingBeginnerLevel2;
import com.ayowainc.quizbox.Category_Levels.Marketing.MarketingBeginnerLevel3;

import com.ayowainc.quizbox.R;

public class MarketingLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_levels);

        Button level1,level2,level3;

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingBeginnerLevel1.class);
                startActivity(BG);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingBeginnerLevel2.class);
                startActivity(BG);
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingBeginnerLevel3.class);
                startActivity(BG);
            }
        });

    }
}
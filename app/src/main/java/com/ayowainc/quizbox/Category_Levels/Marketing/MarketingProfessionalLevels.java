package com.ayowainc.quizbox.Category_Levels.Marketing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ayowainc.quizbox.R;

public class MarketingProfessionalLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_professional_levels);


        Button level1,level2,level3;

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingProfessionalLevel1.class);
                startActivity(BG);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingProfessionalLevel2.class);
                startActivity(BG);
            }
        });
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BG = new Intent(getApplicationContext(), MarketingProfessionalLevel3.class);
                startActivity(BG);
            }
        });

    }
}
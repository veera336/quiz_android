package com.ayowainc.quizbox.Category_Levels.Multimedia;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ayowainc.quizbox.Level2PassCertificate;
import com.ayowainc.quizbox.MenuHomeScreenActivity;
import com.ayowainc.quizbox.R;
import com.ayowainc.quizbox.User.LoginActivity;
import com.ayowainc.quizbox.User.UserProfileActivity;
import com.ayowainc.quizbox.questionsModelClass;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MultimediaBeginnerLevel2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    FirebaseAuth firebaseAuth;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button navToggler_btn, ShareQuestions_btn, Next_btn;
    LinearLayout linearLayout, linearLayout1;
    TextView txtQuestions, txtQuestionsIndicator;
    Dialog dialog;
    private int count = 0;
    private int position = 0;
    private List<questionsModelClass> list;
    private int score = 0;
    private InterstitialAd mInterstitialAd;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); ///Eneter into fullscreen mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions_view);
        firebaseAuth = FirebaseAuth.getInstance();


        //All Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navToggler_btn = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);
        linearLayout1 = findViewById(R.id.options_layout);
        txtQuestions = findViewById(R.id.question_view);
        txtQuestionsIndicator = findViewById(R.id.no_of_questions_view);
        ShareQuestions_btn = findViewById(R.id.share_que_btn);
        Next_btn = findViewById(R.id.next_btn);

        final MediaPlayer level_lose = MediaPlayer.create(this, R.raw.level_lose);///Play sound when user loses level
        final MediaPlayer level_won = MediaPlayer.create(this, R.raw.applause_wav);///Play sound when user wins level

        ShareQuestions_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = list.get(position).getQuestions() + "\n" +
                        "A :" + " " + list.get(position).getOptionA() + "\n" +
                        "B :" + " " + list.get(position).getOptionB() + "\n" +
                        "C :" + " " + list.get(position).getOptionC() + "\n" +
                        "D :" + " " + list.get(position).getOptionD();
                String shareSub = "Your subject here";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        ////Google ads- interstatial Integration-------------------///////////////
        MobileAds.initialize(this, getString(R.string.admob_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.inter_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                if (mInterstitialAd.isLoaded()) {
                    //mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });


        dialog = new Dialog(this, R.style.AnimateDialog);


        ////////////////////////////////////////////////////////////////////BEGINNER PROGRAMMING QUESTIONS////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////ADD YOUR MULTIMEDIA BEGINNER QUESTIONS HERE////////////////////////////////////////////////////////////////////////////////////////
        list = new ArrayList<>();
        list.add(new questionsModelClass("A multimedia file ", " is same as any other regular file", "Must be accessed at specific rate", "is not same as any other regular file", "None of the options", "Must be accessed at specific rate"));
        list.add(new questionsModelClass("In which type of streaming multimedia file is delivered to the client, but not shared?", "real-time streaming", "progressive download", "compression", "None of the options", "real-time streaming"));
        list.add(new questionsModelClass("The delay that occur during the playback of a stream is called", "stream delay", "‘playback delay", "jitter", "event delay", "jitter"));
        list.add(new questionsModelClass(" In teardown state of real time streaming protocol", "the server resources for client", "the server resources for client", "server suspends delivery of stream", "server breaks down the connection", "server breaks down the connection"));
        list.add(new questionsModelClass("Which one of the following resource is not necessarily required on a file server?", "Secondary storage", "Processor", "Network", "Monitor", "Monitor"));
        list.add(new questionsModelClass("What does AIFF stand for?", "Audio Interchange File Format", "Audio Interchange File Folder", "ASCII Interchange File Format", "Audio Internet File Format", "Audio Interchange File Format"));
        list.add(new questionsModelClass("Which company developed the AU audio format?", "Apple", "Sun", "Netscape", "Cisco", "Sun"));
        list.add(new questionsModelClass("What does MIDI stand for?", "Musical Internet Digital Interface", "Musical Internet Digital Interrupt", "Musical Instrument Digital Interface", "Musical Instrument Download Interface", "Musical Instrument Digital Interface"));
        list.add(new questionsModelClass("Which one of the following audio formats was developed by Microsoft?", "AIFF", "MIDI", "RealAudio", "WAV", "WAV"));
        list.add(new questionsModelClass("Which of the following tags cannot be used to include audio on a Web page?", "BGSOUND", "EMBED", "MUSIC", "OBJECT", "MUSIC"));


        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAns((Button) v);
                }
            });
        }

        txtQuestionsIndicator.setText(position + 1 + "/" + list.size());

        playAnim(txtQuestions, 0, list.get(position).getQuestions());

        Next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                Next_btn.setEnabled(false);
                Next_btn.setAlpha(0.7f);
                enableOptions(true);
                position++;

                if (position == list.size()) {
                    //Score Activities
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("The QuizBoxers")
                            .child(firebaseAuth.getCurrentUser().getUid());

                    // Fetch the current score from the database

                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            int currentScore = 0;
                            int reasoningScore = 0;

                            if (dataSnapshot.hasChild("score")) {
                                // Path exists, retrieve the value
                                currentScore = dataSnapshot.child("score").getValue(Integer.class);
                            }

                            if (dataSnapshot.hasChild("reasoningScore")) {
                                // Path exists, retrieve the value
                                reasoningScore = dataSnapshot.child("reasoningScore").getValue(Integer.class);
                            }
                            // Add the current score obtained during the quiz
                            currentScore += score;
                            reasoningScore += score;

                            // Update the database with the new score
                            int finalReasoningScore = reasoningScore;
                            userRef.child("score").setValue(currentScore)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Show appropriate dialog based on the score
                                            userRef.child("reasoningScore").setValue(finalReasoningScore)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Show appropriate dialog based on the score
                                                            if (score <= 2) {

                                                                Button try_again, share, cert;
                                                                dialog.setContentView(R.layout.activity_fail_20_layout);
                                                                try_again = dialog.findViewById(R.id.try_again_btn);
                                                                share = dialog.findViewById(R.id.share_btn);
                                                                cert = dialog.findViewById(R.id.get_cert);
                                                                cert.setVisibility(View.GONE);
//                        cert.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                                Toast.makeText(getApplicationContext(),"No Certificate, Try Again", Toast.LENGTH_LONG).show();
//                            }
//                        });

                                                                level_lose.start();

                                                                try_again.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        Intent BG = new Intent(getApplicationContext(), MultimediaBeginnerLevel2.class); //If User get 20% let him or her play again
                                                                        startActivity(BG);
                                                                    }
                                                                });

                                                                ///Share Quiz Box to friends
                                                                share.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                                                        sharingIntent.setType("text/plain");
                                                                        String shareBody = "Hey! I just played Quiz Box and it's WONDERFUL!";
                                                                        String shareSub = "Your subject here";
                                                                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                                                                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                                                                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                                                                    }
                                                                });

                                                                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                dialog.show();

                                                            } else if (score <= 4) {

                                                                Button try_again, share, cert;
                                                                dialog.setContentView(R.layout.activity_pass_50_layout);
                                                                try_again = dialog.findViewById(R.id.try_again_btn);
                                                                share = dialog.findViewById(R.id.share_btn);
                                                                cert = dialog.findViewById(R.id.get_cert);
                                                                cert.setVisibility(View.GONE);
//                        cert.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                                Toast.makeText(getApplicationContext(),"No Certificate, Try Again", Toast.LENGTH_LONG).show();
//                            }
//                        });

                                                                level_lose.start();

                                                                try_again.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        Intent BG = new Intent(getApplicationContext(), MultimediaBeginnerLevel2.class); ///If User get 50% let him or her play again
                                                                        startActivity(BG);
                                                                    }
                                                                });

                                                                ///Share Quiz Box to friends
                                                                share.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                                                        sharingIntent.setType("text/plain");
                                                                        String shareBody = "Hey! I just played Quiz Box and it's WONDERFUL!";
                                                                        String shareSub = "Your subject here";
                                                                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                                                                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                                                                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                                                                    }
                                                                });

                                                                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                dialog.show();


                                                            } else if (score <= 9) {

                                                                Button promote_btn, share, cert;
                                                                dialog.setContentView(R.layout.activity_pass_70_layout);
                                                                promote_btn = dialog.findViewById(R.id.nl_btn);
                                                                share = dialog.findViewById(R.id.share_btn);
                                                                cert = dialog.findViewById(R.id.get_cert);
                                                                cert.setVisibility(View.GONE);
//                        cert.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent  = new Intent(getApplicationContext(), Level2PassCertificate.class);
//                                startActivity(intent);
//                            }
//                        });

                                                                level_won.start();

                                                                promote_btn.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        Intent BG = new Intent(getApplicationContext(), MultimediaBeginnerLevel3.class); ///If User get 70% let him to next level
                                                                        startActivity(BG);
                                                                    }
                                                                });


                                                                ///Share Quiz Box to friends
                                                                share.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                                                        sharingIntent.setType("text/plain");
                                                                        String shareBody = "Hey! I just played Quiz Box and it's WONDERFUL!";
                                                                        String shareSub = "Your subject here";
                                                                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                                                                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                                                                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                                                                    }
                                                                });

                                                                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                dialog.show();

                                                            } else if (score == 10) {

                                                                Button promote_btn, share, cert;
                                                                dialog.setContentView(R.layout.activity_pass_100_layout);
                                                                promote_btn = dialog.findViewById(R.id.nl_btn);
                                                                share = dialog.findViewById(R.id.share_btn);
                                                                cert = dialog.findViewById(R.id.get_cert);
                                                                cert.setVisibility(View.GONE);
//                        cert.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent  = new Intent(getApplicationContext(),Level2PassCertificate.class);
//                                startActivity(intent);
//                            }
//                        });
                                                                level_won.start();

                                                                promote_btn.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        Intent BG = new Intent(getApplicationContext(), MultimediaBeginnerLevel3.class); ///If User get 100% promote him or her to next level
                                                                        startActivity(BG);
                                                                    }
                                                                });

                                                                ///Share Quiz Box to friends
                                                                share.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {

                                                                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                                                        sharingIntent.setType("text/plain");
                                                                        String shareBody = "Hey! I just played Quiz Box and it's WONDERFUL!";
                                                                        String shareSub = "Your subject here";
                                                                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                                                                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                                                                        startActivity(Intent.createChooser(sharingIntent, "Share using"));
                                                                    }
                                                                });


                                                                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                                dialog.show();

                                                            }
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Show toast message indicating failure to save logical score
                                                            Toast.makeText(getApplicationContext(), "Failed to save logical score", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Show toast message indicating failure to update score
                                            Toast.makeText(getApplicationContext(), "Failed to update score", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle error
                            Toast.makeText(getApplicationContext(), "Failed to fetch score", Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }

                count = 0;
                playAnim(txtQuestions, 0, list.get(position).getQuestions());

            }
        });

        navigationDrawer();
    }

    ///////////////////////////////////////////////////////////////////ANIMATING SCREEN/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void playAnim(final View view, final int value, final String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOptionA();
                    } else if (count == 1) {
                        option = list.get(position).getOptionB();
                    } else if (count == 2) {
                        option = list.get(position).getOptionC();
                    } else if (count == 3) {
                        option = list.get(position).getOptionD();
                    }
                    playAnim(linearLayout1.getChildAt(count), 0, option);
                    count++;
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0) {

                    try {
                        ((TextView) view).setText(data);
                        txtQuestionsIndicator.setText(position + 1 + "/" + list.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);


                    playAnim(view, 1, data);

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void checkAns(Button selectedOptions) {
        enableOptions(false);
        Next_btn.setEnabled(true);
        Next_btn.setAlpha(1);
        if (selectedOptions.getText().toString().equals(list.get(position).getCorrectAnswer())) {
            //correct Answer
            score++;
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.ding);
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));
            mp.start();
        } else {
            //wrong Answer
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.wrong_buzzer);
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2B55")));
            Button correctOption = linearLayout1.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));

            mp.start();
        }
    }

    private void enableOptions(boolean enable) {
        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setEnabled(enable);
            if (enable) {
                linearLayout1.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2133A0")));
            }
        }
    }

    ///////////////////////////////////////////////////////////////////ALL ABOUT NAVIGATION DRAWER/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void navigationDrawer() {

        //Navigation Drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navToggler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    ////////////////////////////////////////////////////////////ANIMATE NAV DRAWER////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.cat_heading));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                linearLayout.setScaleX(offsetScale);
                linearLayout.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = linearLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                linearLayout.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.home) {
            Intent home = new Intent(getApplicationContext(), MenuHomeScreenActivity.class);
            startActivity(home);
            MultimediaBeginnerLevel2.super.onBackPressed();

        } else if (menuItem.getItemId() == R.id.rate) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=")));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thedonuttech.tk")));
            }
        } else if (menuItem.getItemId() == R.id.user_profile) {
            Intent user_profile = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(user_profile);
        } else if (menuItem.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

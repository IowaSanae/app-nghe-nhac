package tdtu.finalproject.homescreen.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tdtu.finalproject.homescreen.Activity.MainActivity;
import tdtu.finalproject.homescreen.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

//TEST FOR BACKGROUND COLOR
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.drawable.AnimationDrawable;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    //TEST FOR BACKGROUND COLOR
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    //FIREBASE
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Button btnLogout;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //TEST FOR BACKGROUND COLOR
//        getSupportActionBar().hide();
        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);
        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);

        //BUTTON FUNCTION
        btnLogout = findViewById(R.id.logout);
        btnBack = findViewById(R.id.backtohomescreen);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent inToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(inToMain);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainPage = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(mainPage);
            }
        });
    }

    //SET COLOR ANIMATION
    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
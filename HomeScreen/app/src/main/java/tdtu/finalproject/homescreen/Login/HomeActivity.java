package tdtu.finalproject.homescreen.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tdtu.finalproject.homescreen.Activity.MainActivity;
import tdtu.finalproject.homescreen.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Button btnLogout;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Test code


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
}
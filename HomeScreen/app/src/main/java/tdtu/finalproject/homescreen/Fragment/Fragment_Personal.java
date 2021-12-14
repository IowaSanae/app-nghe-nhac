package tdtu.finalproject.homescreen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import tdtu.finalproject.homescreen.Login.*;
import tdtu.finalproject.homescreen.R;

//Test CODE
import android.widget.Button;
import android.widget.Toast;
//Test CODE

////FACEBOOK IMPORT TEST
//import tdtu.finalproject.homescreen.Activity.MainActivity;
//import tdtu.finalproject.homescreen.Activity.PersonalActivity;
//import tdtu.finalproject.homescreen.R;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FacebookAuthProvider;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
////Import fb lib
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;
//import com.squareup.picasso.Picasso;

public class Fragment_Personal extends Fragment {
    View view;
    Button btnLogin;
    Button btnLoginFB;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);

        //Test code
        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);

            }
        });

        btnLoginFB = view.findViewById(R.id.btnLoginFB);
        btnLoginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbPage = new Intent(getActivity(), FBLoginActivity.class);
                startActivity(fbPage);
            }
        });
        return view;
    }
}


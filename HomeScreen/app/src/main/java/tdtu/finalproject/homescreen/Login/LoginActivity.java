package tdtu.finalproject.homescreen.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import tdtu.finalproject.homescreen.Activity.MainActivity;
//import tdtu.finalproject.homescreen.Activity.PersonalActivity;
import tdtu.finalproject.homescreen.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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


public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //FACEBOOK
//    private CallbackManager mCallbackManager;
//    private FirebaseAuth mFirebaseAuthFB;
//    private TextView textViewUser;
//    private ImageView mLogo;
//    private LoginButton loginButton;
//    private static final String TAG = "FacebookAuthentication";
//    private FirebaseAuth.AuthStateListener authStateListener;
//    private AccessTokenTracker accessTokenTracker;
//    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.textViewSignUp);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this, "You have logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String passWord = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                }
                else if (passWord.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && passWord.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && passWord.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, passWord).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error, try again!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent inToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "An Error Occurred !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intSignUp);
            }
        });

//        //CASE FOR FB LOGIN
//        mFirebaseAuthFB = FirebaseAuth.getInstance();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        textViewUser = findViewById(R.id.txtFBName);
//        mLogo = findViewById(R.id.image_logo);
//        loginButton = findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email", "public_profile");
//        mCallbackManager = CallbackManager.Factory.create();
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "onSuccess" + loginResult);
//                handleFacebookToken(loginResult.getAccessToken());
//
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "onError" + error);
//            }
//        });
//
//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null){
//                    updateUI(user);
//                } else {
//                    updateUI(null);
//                }
//            }
//        };
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                if(currentAccessToken == null){
//                    mFirebaseAuthFB.signOut();
//                }
//            }
//        };
//
//    }
//    //Facebook
//    private void handleFacebookToken(AccessToken token){
//        Log.d(TAG, "handleFacebookToken" + token);
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mFirebaseAuthFB.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Log.d(TAG, "Sign in with credential: Successful");
//                    FirebaseUser user = mFirebaseAuthFB.getCurrentUser();
//                    updateUI(user);
//                } else{
//                    Log.d(TAG, "Sign in with credential: failure", task.getException());
//                    Toast.makeText(LoginActivity.this, "Authentication Faile", Toast.LENGTH_SHORT).show();
//                    updateUI(null);
//
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void updateUI(FirebaseUser user){
//        if(user != null){
//            textViewUser.setText(user.getDisplayName());
//            if(user.getPhotoUrl() != null){
//                String photoUrl = user.getPhotoUrl().toString();
//                photoUrl = photoUrl + "?type=large" ;
//                Picasso.with(context).load(photoUrl).into(mLogo);
//            }
//        }
//        else{
//            textViewUser.setText("");
//            mLogo.setImageResource(R.drawable.logo);
//        }
//
    }
//
//    //Facebook
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//        //for fb
////        mFirebaseAuthFB.addAuthStateListener(authStateListener);
//
//    }
//    //for FACEBOOK
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (authStateListener != null){
//            mFirebaseAuthFB.removeAuthStateListener(authStateListener);
//        }

    }
}
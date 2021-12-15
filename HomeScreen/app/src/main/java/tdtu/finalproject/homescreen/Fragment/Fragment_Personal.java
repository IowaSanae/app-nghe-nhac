package tdtu.finalproject.homescreen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tdtu.finalproject.homescreen.Login.FBLoginActivity;
import tdtu.finalproject.homescreen.Login.LoginActivity;
import tdtu.finalproject.homescreen.R;

//Test CODE
//Test CODE

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


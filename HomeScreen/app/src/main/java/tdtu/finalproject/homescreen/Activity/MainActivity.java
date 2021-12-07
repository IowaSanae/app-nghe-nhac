package tdtu.finalproject.homescreen.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tdtu.finalproject.homescreen.Fragment.Fragment_Home;
import tdtu.finalproject.homescreen.Fragment.Fragment_Personal;
import tdtu.finalproject.homescreen.Fragment.Fragment_Search;
import tdtu.finalproject.homescreen.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nevigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new Fragment_Home();
                        break;
                    case R.id.nav_search:
                        selectedFragment = new Fragment_Search();
                        break;
                    case R.id.nav_personal:
                        selectedFragment = new Fragment_Personal();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Fragment_Home()).commit();

    }
}
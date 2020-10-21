package com.julie.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    FirstFragment firstFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();

        loadFragment(firstFragment);

        navigationView = findViewById(R.id.bottomNavigationView);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.firstFragment:
                    fragment = firstFragment;
                    getSupportActionBar().setTitle("home");
                    break;
                    case R.id.secondFragment:
                     fragment = secondFragment;
                     getSupportActionBar().setTitle("비디오");
                    break;
                    case R.id.thirdFragment:
                     fragment = thirdFragment;
                     getSupportActionBar().setTitle("정보");
                    break;
            }
            return loadFragment(fragment);
        }
    });


//        // 화면이 바뀌려면, 컨트롤러가 필요. 그래서 컨트롤러 만든다.
//        NavController navController = Navigation.findNavController(MainActivity.this, R.id.fragment);
//
//        //// 액션바의 타이틀을, 프레그먼트에 맞게 변경하고 싶을때.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment
//        ).build();
//        // 네비게이션UI에 연결.
//        NavigationUI.setupActionBarWithNavController(MainActivity.this,
//                navController, appBarConfiguration);
//
//        // 아래쪽의 탭과, 컨트롤러를 결합.
//        NavigationUI.setupWithNavController(navigationView, navController);
    }
    private boolean loadFragment(Fragment fragment) {

        if(fragment != null) {
         getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
         return true;
        }
        return false;
    }
}
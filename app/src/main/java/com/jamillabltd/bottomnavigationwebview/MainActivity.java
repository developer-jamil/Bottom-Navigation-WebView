package com.jamillabltd.bottomnavigationwebview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jamillabltd.bottomnavigationwebview.BottomFragments.CartFragment;
import com.jamillabltd.bottomnavigationwebview.BottomFragments.HomeFragment;
import com.jamillabltd.bottomnavigationwebview.BottomFragments.MessageFragment;
import com.jamillabltd.bottomnavigationwebview.BottomFragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        //default fragment set - HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        //BackPress settings for Fragment
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, HomeFragment.class, null)
                .commit();

        //bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.menu_message:
                    selectedFragment = new MessageFragment();
                    break;
                case R.id.menu_cart:
                    selectedFragment = new CartFragment();
                    break;
                case R.id.menu_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        });

    }


   //onBackPress to
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.menu_home) {

            if (HomeFragment.mWebView.canGoBack()) {
                HomeFragment.mWebView.goBack();
            }else{
                super.onBackPressed();
                finish();
            }
        }else{
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }
    }


}
package com.example.as2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Toast.makeText(HomeActivity.this, "Welcome, " + username, Toast.LENGTH_SHORT).show();


        // managing fragments to load each fragment after clicking the top bar
        FragmentManager fragmentManager = getSupportFragmentManager();

        ImageButton btnShop = findViewById(R.id.btnShop);
        btnShop.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, ShopFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());

        ImageButton btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, ProfileFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());

        ImageButton btnBasket = findViewById(R.id.btnBasket);
        btnBasket.setOnClickListener(v -> fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, BasketFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit());
    }

    // preventing accidental logout
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Cannot exit at this time, please logout or close the app", Toast.LENGTH_SHORT).show();
    }
}

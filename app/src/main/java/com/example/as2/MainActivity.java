package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText username = findViewById(R.id.username1);
        EditText password = findViewById(R.id.password1);
        Button btnlogin = findViewById(R.id.btnsignin1);
        Button btnsignup = findViewById(R.id.btnsignup2);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields required", Toast.LENGTH_SHORT).show();
            else if (user.equals("admin") && pass.equals("adminkj")) { // check if admin credentials are entered
                Toast.makeText(MainActivity.this, "Admin sign-in successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            } else {
                Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                if(checkuserpass) {
                    Toast.makeText(MainActivity.this, "Sign-in successful", Toast.LENGTH_SHORT).show();
                    editor.putString("username", user);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsignup.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);

        });
    }

}
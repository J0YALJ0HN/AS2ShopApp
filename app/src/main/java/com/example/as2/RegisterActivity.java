package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText repassword = findViewById(R.id.repassword);
        EditText email = findViewById(R.id.email);
        EditText address = findViewById(R.id.address);
        EditText postcode = findViewById(R.id.postcode);

        Button signup = findViewById(R.id.btnsignup);
        Button signin = findViewById(R.id.btnsignin);

        DB = new DBHelper(this);

        signup.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            String mail = email.getText().toString();
            String add = address.getText().toString();
            String post = postcode.getText().toString();

            // expression for email validation
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty() || mail.isEmpty() || add.isEmpty() || post.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please input in all fields", Toast.LENGTH_SHORT).show();
            } else if (!mail.matches(emailPattern)) {
                Toast.makeText(RegisterActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(repass)) {
                    boolean checkUser = DB.checkUsername(user);
                    if (!checkUser) {
                        boolean insert = DB.insertData(user, pass, mail, add, post);
                        if (insert) {
                            Toast.makeText(RegisterActivity.this, "You are now registered! You are now logged in.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists, please sign in again with different credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}

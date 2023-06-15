package com.example.as2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminActivity extends AppCompatActivity {

    EditText prodName, prodDesc, category, prodListPrice, prodRetailPrice, prodQuantity;
    Button insert, view;

    DBHelper DB;
    String[] allowedCategories = {"Electronics", "Fashion", "Home", "Health", "Sports", "Stationery", "Toys", "Automotive", "Groceries", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        prodName = findViewById(R.id.prodName);
        prodDesc = findViewById(R.id.prodDesc);
        category = findViewById(R.id.category);
        prodListPrice = findViewById(R.id.prodListPrice);
        prodRetailPrice = findViewById(R.id.prodRetailPrice);
        prodQuantity = findViewById(R.id.prodQuantity);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        view.setOnClickListener(v -> startActivity(new Intent(AdminActivity.this, ListAdmin.class)));

        insert.setOnClickListener(v -> {
            String prodNameTXT = prodName.getText().toString();
            String prodDescTXT = prodDesc.getText().toString();
            String categoryTXT = category.getText().toString();
            double prodListPriceDBLE = Double.parseDouble(prodListPrice.getText().toString());
            double prodRetailPriceDBLE = Double.parseDouble(prodRetailPrice.getText().toString());
            int prodQuantityINT = Integer.parseInt(prodQuantity.getText().toString());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String prodDateCreatedTXT = dateFormat.format(new Date());

            boolean isCategoryAllowed = false;
            for (String allowedCategory : allowedCategories) {
                if (categoryTXT.equals(allowedCategory)) {
                    isCategoryAllowed = true;
                    break;
                }
            }

            if (isCategoryAllowed) {
                boolean checkInsertData = DB.insertProduct(prodNameTXT, prodDescTXT, categoryTXT, prodListPriceDBLE, prodRetailPriceDBLE, prodQuantityINT, prodDateCreatedTXT);
                if (checkInsertData) {
                    Toast.makeText(AdminActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "Data insertion failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                StringBuilder validCategories = new StringBuilder();
                for (int i = 0; i < allowedCategories.length; i++) {
                    validCategories.append(allowedCategories[i]);
                    if (i != allowedCategories.length - 1) {
                        validCategories.append(", ");
                    }
                }
                Toast.makeText(AdminActivity.this, "Invalid category. Valid categories: " + validCategories, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

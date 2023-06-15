package com.example.as2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

public class ListAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> prodName, prodDesc, category, prodListPrice, prodRetailPrice, prodQuantity, prodDateCreated;
    DBHelper DB;
    ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin);
        DB = new DBHelper(this);
        prodName = new ArrayList<>();
        prodDesc = new ArrayList<>();
        category = new ArrayList<>();
        prodListPrice = new ArrayList<>();
        prodRetailPrice = new ArrayList<>();
        prodQuantity = new ArrayList<>();
        prodDateCreated = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ShopAdapter(this, prodName, prodDesc, category, prodListPrice, prodRetailPrice, prodQuantity, prodDateCreated);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    private void displayData() {
        Cursor cursor = DB.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(ListAdmin.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                prodName.add(cursor.getString(0));
                prodDesc.add(cursor.getString(1));
                category.add(cursor.getString(2));
                prodListPrice.add(cursor.getString(3));
                prodRetailPrice.add(cursor.getString(4));
                prodQuantity.add(cursor.getString(5));
                prodDateCreated.add(cursor.getString(6));
            }
        }
    }
}

package com.example.as2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Database.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Users (username TEXT PRIMARY KEY, email TEXT, address TEXT, postcode TEXT, password TEXT, registration_date DATETIME DEFAULT CURRENT_TIMESTAMP, date_updated DATETIME DEFAULT CURRENT_TIMESTAMP)");
        DB.execSQL("CREATE TABLE Products (prodName TEXT PRIMARY KEY, prodDesc TEXT, category TEXT, prodListPrice REAL, prodRetailPrice REAL, prodQuantity INTEGER, prodDateCreated DATETIME DEFAULT CURRENT_TIMESTAMP)");
        DB.execSQL("CREATE TABLE Basket (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, prodName TEXT, prodRetailPrice REAL, quantity INTEGER, registration_date DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS Basket");
        DB.execSQL("DROP TABLE IF EXISTS Products");
        onCreate(DB);
    }

    public boolean insertData(String username, String password, String email, String address, String postcode) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("postcode", postcode);
        contentValues.put("registration_date", getCurrentDateTime());
        contentValues.put("date_updated", getCurrentDateTime());
        long result = DB.insert("Users", null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Users WHERE username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Users WHERE username = ? AND password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public boolean insertProduct(String prodName, String prodDesc, String category, double prodListPrice, double prodRetailPrice, int prodQuantity, String prodDateCreated) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Products WHERE prodName = ?", new String[]{prodName});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("prodQuantity");
            int existingQuantity = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
            prodQuantity += existingQuantity;
            updateProduct(prodName, prodQuantity);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("prodName", prodName);
            contentValues.put("prodDesc", prodDesc);
            contentValues.put("category", category);
            contentValues.put("prodListPrice", prodListPrice);
            contentValues.put("prodRetailPrice", prodRetailPrice);
            contentValues.put("prodQuantity", prodQuantity);
            contentValues.put("prodDateCreated", prodDateCreated);
            long result = DB.insert("Products", null, contentValues);
            return result != -1;
        }
        return true;
    }


    public boolean updateProduct(String prodName, int prodQuantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("prodQuantity", prodQuantity);
        int result = DB.update("Products", contentValues, "prodName = ?", new String[]{prodName});
        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products", null);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public Cursor getElectronicsData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Electronics'", null);
    }

    public Cursor getFashionData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Fashion'", null);
    }

    public Cursor getHomeData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Home'", null);
    }

    public Cursor getHealthData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Health'", null);
    }

    public Cursor getSportsData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Sports'", null);
    }

    public Cursor getStationeryData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Stationery'", null);
    }

    public Cursor getToysData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Toys'", null);
    }

    public Cursor getAutomotiveData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Automotive'", null);
    }

    public Cursor getGroceriesData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Groceries'", null);
    }

    public Cursor getMiscellaneousData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Products WHERE category = 'Miscellaneous'", null);
    }

    public boolean insertBasketData(String username, String prodName, String prodRetailPrice, int quantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("prodName", prodName);
        contentValues.put("prodRetailPrice", prodRetailPrice);
        contentValues.put("quantity", quantity);
        long result = DB.insert("Basket", null, contentValues);
        return result != -1;
    }

}

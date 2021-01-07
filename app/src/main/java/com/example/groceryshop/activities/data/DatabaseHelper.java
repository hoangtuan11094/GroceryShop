package com.example.groceryshop.activities.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.groceryshop.activities.entity.CategoryEntity;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.entity.VegetableEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "groceryshop.db";
    public static String PATH_DB;
    private SQLiteDatabase sqLiteDatabase;
    public Context context;
    private String NAME_TABLE = "product";
    private String ID = "id";
    private String IMG_PRODUCT = "productImg";
    private String NAME_PRODUCT = "productName";
    private String WEIGTH_PRODUCT = "productWeigth";
    private String PRICE_PRODUCT = "productPrice";
    private String ID_CATEGORY_PRODUCT = "productIdCategory";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 3);
        if (Build.VERSION.SDK_INT >= 17) {
            PATH_DB = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            PATH_DB = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.context = context;
    }

    public void createDataBase() {

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
                Log.e("TAG", "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(PATH_DB + DB_NAME);
        Log.v("dbFile", dbFile + "   " + dbFile.exists());
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = PATH_DB + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public List<VegetableEntity> getAllProducts() {
        List<VegetableEntity> productList = new ArrayList<>();
        SQLiteDatabase dbProduct = getReadableDatabase();
        Cursor cursor = dbProduct.rawQuery("SELECT * from product", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                VegetableEntity products = new VegetableEntity();
                products.setIdProduct(cursor.getInt(0));
                products.setImgProduct(cursor.getString(1));
                products.setProductName(cursor.getString(2));
                products.setProductWeight(cursor.getInt(3));
                products.setProductPrice(cursor.getInt(4));
                products.setProductIdCategory(cursor.getInt(5));

                productList.add(products);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }

    public List<CategoryEntity> getAllCategory() {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        SQLiteDatabase dbCategory = getReadableDatabase();
        Cursor cursor = dbCategory.rawQuery("SELECT * from category", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CategoryEntity category = new CategoryEntity();
                category.setIdCategory(cursor.getInt(0));
                category.setImgCategory(cursor.getString(1));
                category.setNameCategory(cursor.getString(2));
                categoryEntityList.add(category);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return categoryEntityList;
    }

    public long insertUser(UserEntity userEntity){
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME ,Context.MODE_PRIVATE, null);
        ContentValues  values = new ContentValues();
        values.put("idUser", userEntity.idUser);
        values.put("password", userEntity.passwordUser);
        values.put("fullName", userEntity.fullName);
        long newRow = sqLiteDatabase.insert("user", null, values);
        Log.e("TAG", "insertUser: " + newRow );
        return newRow;
    }
    public void getAllUser(){
        SQLiteDatabase dbUser = getReadableDatabase();
        Cursor cursor = dbUser.rawQuery("SELECT * from user", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CategoryEntity category = new CategoryEntity();
                category.setIdCategory(cursor.getInt(0));
                category.setImgCategory(cursor.getString(1));
                category.setNameCategory(cursor.getString(2));
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

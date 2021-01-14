package com.example.groceryshop.activities.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.groceryshop.R;
import com.example.groceryshop.activities.activities.ActMain;
import com.example.groceryshop.activities.entity.CategoryEntity;
import com.example.groceryshop.activities.entity.UserEntity;
import com.example.groceryshop.activities.entity.VegetableEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static String DB_NAME = "grocery_shop.db";
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
    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (databaseHelper == null) {
                    databaseHelper = new DatabaseHelper(context);
                }
            }
        }
        return databaseHelper;
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 4);
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
                products.setIdProduct(cursor.getInt(cursor.getColumnIndex(ID)));
                products.setImgProduct(cursor.getString(cursor.getColumnIndex(IMG_PRODUCT)));
                products.setProductName(cursor.getString(cursor.getColumnIndex(NAME_PRODUCT)));
                products.setProductWeight(cursor.getInt(cursor.getColumnIndex(WEIGTH_PRODUCT)));
                products.setProductPrice(cursor.getInt(cursor.getColumnIndex(PRICE_PRODUCT)));
                products.setProductIdCategory(cursor.getInt(cursor.getColumnIndex(ID_CATEGORY_PRODUCT)));

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
                category.setIdCategory(cursor.getInt(cursor.getColumnIndex("idCategory")));
                category.setImgCategory(cursor.getString(cursor.getColumnIndex("imgCategory")));
                category.setNameCategory(cursor.getString(cursor.getColumnIndex("nameCategory")));
                categoryEntityList.add(category);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return categoryEntityList;
    }

//GET USER
    //singup

    public long insertUser(UserEntity userEntity) {
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        long newRow = 0;
        ContentValues values = new ContentValues();
        values.put("email", userEntity.email);
        values.put("password", userEntity.passwordUser);
        values.put("name", userEntity.fullName);
        Toast.makeText(context, R.string.lbl_SignUpSuccess, Toast.LENGTH_SHORT).show();
        newRow = sqLiteDatabase.insert("user", null, values);

        return newRow;
    }

    public void getAllUser() {
        SQLiteDatabase dbUser = getReadableDatabase();
        Cursor cursor = dbUser.rawQuery("SELECT * from user", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UserEntity userEntity = new UserEntity();
                userEntity = dataUser(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    private UserEntity dataUser(Cursor cursor) {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.idUser = cursor.getInt(cursor.getColumnIndex("id"));
        userEntity1.email = cursor.getString(cursor.getColumnIndex("email"));
        userEntity1.passwordUser = cursor.getString(cursor.getColumnIndex("password"));
        userEntity1.fullName = cursor.getString(cursor.getColumnIndex("name"));

        return userEntity1;
    }


    public boolean checkEmail(String email1) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT email from user WHERE email= '" + email1 + "'", null);
        if (cursor != null & cursor.moveToFirst()) {
            String email2 = cursor.getString(cursor.getColumnIndex("email"));
            Log.e(TAG, "checkEmail: " + email2);
            if (email2.equals(email1)) {
                return true;
            }
        }
        return false;
    }

    public UserEntity Login(UserEntity userEntity) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "email", "password", "name"},
                "email" + "=?",
                new String[]{userEntity.email},
                null, null, null);

        UserEntity userEntity1 = new UserEntity();
        if (cursor != null && cursor.moveToFirst()) {
            userEntity1 = dataUser(cursor);
            if (userEntity.passwordUser.equalsIgnoreCase(userEntity1.passwordUser)) {
                return userEntity1;
            }
        }
        return null;
    }

    public UserEntity sendLinkEmail(UserEntity userEntity) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "email", "password", "name"},
                "email" + "=?",
                new String[]{userEntity.email},
                null, null, null);
        UserEntity userEntity1 = new UserEntity();
        if (cursor != null && cursor.moveToNext()) {
            userEntity1 = dataUser(cursor);
            if (userEntity.email.equalsIgnoreCase(userEntity1.email)) {
                return userEntity1;
            }
        }
        return null;
    }

    public int resetPassword(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", pass);

        int count = db.update("user", values, "email" + " = ?", new String[]{String.valueOf(email)});
        db.close();
        return count;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

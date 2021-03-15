package com.atec.samuraiinventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_NAME = "HARDWARE_DB";

    private final List<String> tableNames;
    public static final String ID = "_id";
    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String DETAILS = "details";
    public static final String RESPONSIBLE = "responsible";
    public static final String DEPARTMENT = "department";
    public static final String PLACEMENT = "placement";
    public static final String PRICE = "price";
    public static final String STATUS = "status";
    public static final String COMMENT = "comment";


    public DBHelper(@Nullable Context context, List<String> tableNames) {
        super(context, DB_NAME, null, VERSION);
        this.tableNames = tableNames;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String tableName : tableNames) {
            String command = "CREATE TABLE " + tableName +
                    "(" +
                    ID + " integer primary key," +
                    KEY + " text," +
                    NAME + " text," +
                    DETAILS + " text," +
                    RESPONSIBLE + " text," +
                    DEPARTMENT + " text," +
                    PLACEMENT + " text," +
                    PRICE + " text," +
                    STATUS + " text," +
                    COMMENT + " text," +
                    ")";
            db.execSQL(command);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String tableName : tableNames) {
            db.execSQL("drop table if exists " + tableName);
        }

        onCreate(db);

    }
}

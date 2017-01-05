package com.rakaadinugroho.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Raka Adi Nugroho on 12/30/16.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class PeopleOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PeopleOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION   = 1;
    private static final String PEOPLE_TABLE    = "people";
    private static final String DATABASE_NAME   = "creatures";
    /* Column */
    private static final String KEY_ID  = "_id";
    private static final String KEY_NAME    = "name";
    private static final String[] COLUMNS   = {KEY_ID, KEY_NAME};

    /* Query Create Table */
    private static final String PEOPLE_TABLE_CREATE = "CREATE TABLE "+PEOPLE_TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_NAME+" TEXT );";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;


    public PeopleOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PEOPLE_TABLE_CREATE);
        createDataDummies(db);
    }

    private void createDataDummies(SQLiteDatabase db) {
        /* Example Data */
        String[] peoples    = {
                "Raka Adi Nugroho",
                "Nadiem Makariem",
                "Ahmad Zacky",
                "William Tanuwijaya"
        };
        ContentValues values    = new ContentValues();
        for (int position  = 0; position < peoples.length; position++){
            values.put(KEY_NAME, peoples[position]);
            db.insert(PEOPLE_TABLE, null, values);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PeopleOpenHelper.class.getName(), "Upgrade Database From "+oldVersion+" To "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+PEOPLE_TABLE);
        onCreate(db);
    }

    public PeopleItem query(int position){
        String query    = "SELECT * FROM "+PEOPLE_TABLE+" ORDER BY "+KEY_NAME+" ASC LIMIT "+position+", 1";
        Cursor cursor   = null;
        PeopleItem entry    = new PeopleItem();
        try {
            if (mReadableDB == null)
                mReadableDB = getReadableDatabase();
            cursor  = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setmName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        }catch (Exception e){
            Log.d(TAG, "Terjadi Kesalahan "+e.getMessage());
        }finally {
            cursor.close();
            return entry;
        }
    }
}

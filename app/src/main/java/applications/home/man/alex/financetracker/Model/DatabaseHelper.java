package applications.home.man.alex.financetracker.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Alex on 7/11/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String TEXT_TYPE = " TEXT";
    public static final String AMT_TYPE = " REAL";
    public static final String COMMA = ", ";

    public static final String DATABASE_NAME = "TRANSACTIONS.db";
    public static final String TABLE_NAME = "TRANSACTION_INFO";
    public static final String DATE_COL = "DATE";
    public static final String TYPE_COL = "TYPE";
    public static final String AMT_COL = "AMOUNT";
    public static final String DESC_COL = "DESCRIPTION";

    public static final String CREATE_TRANSACTION_TABLE= "create table "
            + TABLE_NAME + " (id integer primary key autoincrement, "
            + DATE_COL + TEXT_TYPE + COMMA + TYPE_COL + TEXT_TYPE + COMMA
            + AMT_COL + AMT_TYPE + COMMA + DESC_COL + TEXT_TYPE + ")";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context mCxt;
    private static DatabaseHelper mInstance = null;

    public DatabaseHelper(Context c) {
        super(c, DATABASE_NAME, null, 1);
        mCxt = c;
    }

    public static DatabaseHelper getInstance(Context c) {
        if (mInstance == null)
            mInstance = new DatabaseHelper(c.getApplicationContext());
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
    }

    /**
     * Adds transaction to database
     */
    public boolean addTransaction(String date, String type, double amount, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATE_COL, date);
        cv.put(TYPE_COL, type);
        cv.put(AMT_COL, amount);
        cv.put(DESC_COL, desc);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        return true;
    }
    /**
     * Returns complete list of transactions
     */
    public ArrayList<Transaction> getTransactionList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Transaction> transaction_list = new ArrayList<>();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("DATABASEHELPER: ", DatabaseUtils.dumpCursorToString(cursor));
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String date = cursor.getString(cursor.getColumnIndex(DATE_COL));
                String type = cursor.getString(cursor.getColumnIndex(TYPE_COL));
                Double amount =
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex(AMT_COL)));
                String desc = cursor.getString(cursor.getColumnIndex(DESC_COL));
                transaction_list.add(new Transaction(amount, type, desc, date));
                Log.d("DATABSEHELPER: ", "IN WHILE LOOP");
                cursor.moveToNext();
            }
        }
        return transaction_list;
    }

}

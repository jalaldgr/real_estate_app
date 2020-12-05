package ir.hamedanmelk.hamedanmelk.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ir.hamedanmelk.hamedanmelk.models.ProvinceModel;


public class MYSQlDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String TAG = "MYSQlDBHelper";
    private static final String PROVINCE_TABLE_NAME = "Province";

    private static final String PROVINCE_TABLE_COLUMN_ID="id";
    private static final String PROVINCE_TABLE_COLUMN_TITLE="Title";
    private static final String PROVINCE_TABLE_COLUMN_TELEGRAM_CHANNEL_NAME="TelegramChannelName";
    private static final String PROVINCE_TABLE_COLUMN_DISABLED="Disabled";

    private static final String CREATE_PROVINCE_TABLE= "CREATE TABLE "+PROVINCE_TABLE_NAME+"("
            + PROVINCE_TABLE_COLUMN_ID + " TEXT,"
            + PROVINCE_TABLE_COLUMN_TITLE + " TEXT,"
            + PROVINCE_TABLE_COLUMN_TELEGRAM_CHANNEL_NAME + " TEXT,"
            + PROVINCE_TABLE_COLUMN_DISABLED + " TEXT"
            +")";


    public void InsertProvinces(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(PROVINCE_TABLE_NAME, null, cv);
        db.close();
    }
    public ArrayList<ProvinceModel> GetProvincesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ProvinceModel> provinceModels = new ArrayList<ProvinceModel>();
        ProvinceModel provinceModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+PROVINCE_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                provinceModel = new ProvinceModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3));
                provinceModels.add(provinceModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Provinces Says:", e.toString());}
        db.close();
        return provinceModels;
    }

    public ProvinceModel GetProvinceByID(String id){
        ProvinceModel provinceModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+PROVINCE_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetProvinceByID: "+cursor.toString());
            provinceModel = new ProvinceModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetProvinceByID: "+e.toString());
        }
        database.close();
        return provinceModel;
    }

    public void DeleteProvinces(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+PROVINCE_TABLE_NAME);
        db.close();
    }


    public MYSQlDBHelper( Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        db.disableWriteAheadLogging();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PROVINCE_TABLE_NAME);


    }
}

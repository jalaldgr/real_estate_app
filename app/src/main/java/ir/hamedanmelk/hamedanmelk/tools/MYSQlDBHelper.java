package ir.hamedanmelk.hamedanmelk.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.models.micro.ProvinceModel;


public class MYSQlDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String TAG = "MYSQlDBHelper";
    private static final String PROVINCE_TABLE_NAME = "Province";
    private static final String DISTRICT_TABLE_NAME = "District";


    private static final String PROVINCE_TABLE_COLUMN_ID="id";
    private static final String PROVINCE_TABLE_COLUMN_TITLE="Title";
    private static final String PROVINCE_TABLE_COLUMN_TELEGRAM_CHANNEL_NAME="TelegramChannelName";
    private static final String PROVINCE_TABLE_COLUMN_DISABLED="Disabled";

    private static final String DISTRICT_TABLE_COLUMN_ID = "id";
    private static final String DISTRICT_TABLE_COLUMN_TITLE = "Title";
    private static final String DISTRICT_TABLE_COLUMN_AREA_ID ="area_id";



    private static final String CREATE_PROVINCE_TABLE= "CREATE TABLE "+PROVINCE_TABLE_NAME+"("
            + PROVINCE_TABLE_COLUMN_ID + " TEXT,"
            + PROVINCE_TABLE_COLUMN_TITLE + " TEXT,"
            + PROVINCE_TABLE_COLUMN_TELEGRAM_CHANNEL_NAME + " TEXT,"
            + PROVINCE_TABLE_COLUMN_DISABLED + " TEXT"
            +")";

    private static final String CREATE_DISTRICT_TABLE= "CREATE TABLE "+DISTRICT_TABLE_NAME+"("
            + DISTRICT_TABLE_COLUMN_ID + " TEXT,"
            + DISTRICT_TABLE_COLUMN_TITLE + " TEXT,"
            + DISTRICT_TABLE_COLUMN_AREA_ID + " TEXT"
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


///////////////////////////////////District methods///////////////////////////////////////////////
public void InsertDistrict(ContentValues cv){
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(DISTRICT_TABLE_NAME, null, cv);
    db.close();
}

    public ArrayList<DistrictModel> GetDistrictList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DistrictModel> districtModels = new ArrayList<DistrictModel>();
        DistrictModel districtModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+DISTRICT_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                 districtModel = new DistrictModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                districtModels.add(districtModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Provinces Says:", e.toString());}
        db.close();
        return districtModels;
    }

    public DistrictModel GetDistrictByID(String id){
        DistrictModel districtModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+DISTRICT_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetDistrictByID: "+cursor.toString());
            districtModel = new DistrictModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetDistrictByID: "+e.toString());
        }
        database.close();
        return districtModel;
    }

    public void DeleteDistrict(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+DISTRICT_TABLE_NAME);
        db.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE_TABLE);
        sqLiteDatabase.execSQL(CREATE_DISTRICT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PROVINCE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DISTRICT_TABLE_NAME);


    }
}

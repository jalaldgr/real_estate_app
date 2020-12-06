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
    private static final String CITIES_TABLE_NAME ="Cities";
    private static final String AREAS_TABLE_NAME ="Areas";
    private static final String LAND_TYPES_TABLE_NAME ="LandTYPes";
    private static final String BUILDING_CONDITIONS_TABLE_NAME ="BuildingConditions";
    private static final String LAND_STATES_TABLE_NAME ="LandStates";
    private static final String RENTAL_PREFERENCES_TABLE_NAME ="RentalPreferences";
    private static final String DENSITY_TYPES_TABLE_NAME ="DensityTypes";
    private static final String FLOOR_COVERINGS_TABLE_NAME ="FloorCoverings";
    private static final String KITCHEN_SERVICES_TABLE_NAME ="KitchenServices";
    private static final String LAND_CASES_TABLE_NAME ="LandCases";
    private static final String LOAN_TYPES_TABLE_NAME ="LoanTypes";
    private static final String LAND_SITUATIONS_TABLE_NAME ="LandSituations";
    private static final String LAND_VIEWS_TABLE_NAME ="LandViews";
    private static final String LAND_DIRECTIONS_TABLE_NAME ="LandDirections";

    private static final String PROVINCE_TABLE_COLUMN_ID="id";
    private static final String PROVINCE_TABLE_COLUMN_TITLE="Title";
    private static final String PROVINCE_TABLE_COLUMN_TELEGRAM_CHANNEL_NAME="TelegramChannelName";
    private static final String PROVINCE_TABLE_COLUMN_DISABLED="Disabled";

    private static final String DISTRICT_TABLE_COLUMN_ID = "id";
    private static final String DISTRICT_TABLE_COLUMN_TITLE = "Title";
    private static final String DISTRICT_TABLE_COLUMN_AREA_ID ="area_id";

    private static final String CITIES_TABLE_COLUMN_ID              ="id";
    private static final String CITIES_TABLE_COLUMN_TITLE           ="Title";
    private static final String CITIES_TABLE_COLUMN_PROVINCE_ID     ="province_id";

    private static final String AREAS_TABLE_COLUMN_ID               ="id";
    private static final String AREAS_TABLE_COLUMN_TITLE            ="Title";
    private static final String AREAS_TABLE_COLUMN_CITY_ID          ="city_id";

    private static final String LAND_TYPES_TABLE_COLUMN_ID          ="id";
    private static final String LAND_TYPES_TABLE_COLUMN_TITLE       ="Title";

    private static final String BUILDING_CONDITIONS_TABLE_COLUMN_ID  ="id";
    private static final String BUILDING_CONDITIONS_TABLE_COLUMN_TITLE="Title";

    private static final String LAND_STATES_TABLE_COLUMN_ID         ="id";
    private static final String LAND_STATES_TABLE_COLUMN_TITLE      ="Title";

    private static final String RENTAL_PREFERENCE_TABLE_COLUMN_ID   ="id";
    private static final String RENTAL_PREFERENCE_TABLE_COLUMN_TITLE="Title";

    private static final String DENSITY_TYPE_TABLE_COLUMN_ID        ="id";
    private static final String DENSITY_TYPE_COLUMN_TITLE           ="Title";

    private static final String FLOOR_COVERINGS_TABLE_COLUMN_ID     ="id";
    private static final String FLOOR_COVERINGS_TABLE_COLUMN_TITLE  ="Title";

    private static final String KITCHEN_SERVICES_TABLE_COLUMN_ID    ="id";
    private static final String KITCHEN_SERVICES_TABLE_COLUMN_TITLE ="Title";

    private static final String LAND_CASE_TABLE_COLUMN_ID           ="id";
    private static final String LAND_CASE_TABLE_COLUMN_TITLE        ="Title";

    private static final String LOAN_TYPES_TABLE_COLUMN_ID          ="id";
    private static final String LOAN_TYPES_TABLE_COLUMN_TITLE       ="Title";

    private static final String LAND_SITUATIONS_TABLE_COLUMN_ID     ="id";
    private static final String LAND_SITUATIONS_TABLE_COLUMN_TITLE  ="Title";
    private static final String LAND_SITUATIONS_TABLE_COLUMN_COLOR  ="Color";

    private static final String LAND_VIEWS_TABLE_COLUMN_ID          ="id";
    private static final String LAND_VIEWS_TABLE_COLUMN_TITLE       ="Title";

    private static final String LAND_DIRECTIONS_TABLE_COLUMN_ID     ="id";
    private static final String LAND_DIRECTIONS_TABLE_COLUMN_TITLE  ="Title";

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

    private static final String CREATE_CITIES_TABLE= "CREATE TABLE "+CITIES_TABLE_NAME+"("
            + CITIES_TABLE_COLUMN_ID + " TEXT,"
            + CITIES_TABLE_COLUMN_TITLE + " TEXT,"
            + CITIES_TABLE_COLUMN_PROVINCE_ID + " TEXT"
            +")";

    private static final String CREATE_AREAS_TABLE= "CREATE TABLE "+AREAS_TABLE_NAME+"("
            + AREAS_TABLE_COLUMN_ID + " TEXT,"
            + AREAS_TABLE_COLUMN_TITLE + " TEXT,"
            + AREAS_TABLE_COLUMN_CITY_ID + " TEXT"
            +")";

    private static final String CREATE_LAND_TYPES_TABLE= "CREATE TABLE "+LAND_TYPES_TABLE_NAME+"("
            + LAND_TYPES_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_TYPES_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_BUILDING_CONDITIONS_TABLE= "CREATE TABLE "+BUILDING_CONDITIONS_TABLE_NAME+"("
            + BUILDING_CONDITIONS_TABLE_COLUMN_ID+ " TEXT,"
            + BUILDING_CONDITIONS_TABLE_COLUMN_TITLE+ " TEXT"
            +")";

    private static final String CREATE_LAND_STATES_TABLE= "CREATE TABLE "+LAND_STATES_TABLE_NAME+"("
            + LAND_STATES_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_STATES_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_RENTAL_PREFERENCE_TABLE= "CREATE TABLE "+RENTAL_PREFERENCES_TABLE_NAME+"("
            + RENTAL_PREFERENCE_TABLE_COLUMN_ID+ " TEXT,"
            + RENTAL_PREFERENCE_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_DENSITY_TYPES_TABLE= "CREATE TABLE "+DENSITY_TYPES_TABLE_NAME+"("
            + DENSITY_TYPE_TABLE_COLUMN_ID+ " TEXT,"
            + DENSITY_TYPE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_FLOOR_COVERINGS_TABLE= "CREATE TABLE "+FLOOR_COVERINGS_TABLE_NAME+"("
            + FLOOR_COVERINGS_TABLE_COLUMN_ID+ " TEXT,"
            + FLOOR_COVERINGS_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_KITCHEN_SERVICES_TABLE= "CREATE TABLE "+KITCHEN_SERVICES_TABLE_NAME+"("
            + KITCHEN_SERVICES_TABLE_COLUMN_ID+ " TEXT,"
            + KITCHEN_SERVICES_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_LAND_CASE_TABLE= "CREATE TABLE "+LAND_CASES_TABLE_NAME+"("
            + LAND_CASE_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_CASE_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_LOAN_TYPES_TABLE= "CREATE TABLE "+LOAN_TYPES_TABLE_NAME+"("
            + LOAN_TYPES_TABLE_COLUMN_ID+ " TEXT,"
            + LOAN_TYPES_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_LAND_SITUATION_TABLE= "CREATE TABLE "+LAND_SITUATIONS_TABLE_NAME+"("
            + LAND_SITUATIONS_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_SITUATIONS_TABLE_COLUMN_TITLE + " TEXT,"
            +LAND_SITUATIONS_TABLE_COLUMN_COLOR +" TEXT"
            +")";

    private static final String CREATE_LAND_VIEWS_TABLE= "CREATE TABLE "+LAND_VIEWS_TABLE_NAME+"("
            + LAND_VIEWS_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_VIEWS_TABLE_COLUMN_TITLE + " TEXT"
            +")";

    private static final String CREATE_LAND_DIRECTIONS_TABLE= "CREATE TABLE "+LAND_DIRECTIONS_TABLE_NAME+"("
            + LAND_DIRECTIONS_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_DIRECTIONS_TABLE_COLUMN_TITLE+ " TEXT"
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
        sqLiteDatabase.execSQL(CITIES_TABLE_NAME);
        sqLiteDatabase.execSQL(AREAS_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL(BUILDING_CONDITIONS_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_STATES_TABLE_NAME);
        sqLiteDatabase.execSQL(RENTAL_PREFERENCES_TABLE_NAME);
        sqLiteDatabase.execSQL(DENSITY_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL(FLOOR_COVERINGS_TABLE_NAME);
        sqLiteDatabase.execSQL(KITCHEN_SERVICES_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_CASES_TABLE_NAME);
        sqLiteDatabase.execSQL(LOAN_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_SITUATIONS_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_VIEWS_TABLE_NAME);
        sqLiteDatabase.execSQL(LAND_DIRECTIONS_TABLE_NAME);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PROVINCE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DISTRICT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CITIES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+AREAS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BUILDING_CONDITIONS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_STATES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RENTAL_PREFERENCES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DENSITY_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+FLOOR_COVERINGS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+KITCHEN_SERVICES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_CASES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LOAN_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_SITUATIONS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_VIEWS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_DIRECTIONS_TABLE_NAME);




    }
}

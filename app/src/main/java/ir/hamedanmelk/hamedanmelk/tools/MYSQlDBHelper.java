package ir.hamedanmelk.hamedanmelk.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import ir.hamedanmelk.hamedanmelk.models.AgencyModel;
import ir.hamedanmelk.hamedanmelk.models.AgentUserModel;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.models.LawyerModel;
import ir.hamedanmelk.hamedanmelk.models.OfficeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.AreaModel;
import ir.hamedanmelk.hamedanmelk.models.micro.BuildingConditionModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CityModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DensityTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.models.micro.EquipmentModel;
import ir.hamedanmelk.hamedanmelk.models.micro.FloorCoveringModel;
import ir.hamedanmelk.hamedanmelk.models.micro.KitchenServiceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandCaseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandDirectionModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandSituationModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandStateTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandViewModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LoanTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.ProvinceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.RentalPreferenceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.UseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.VoucherModel;


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
    private static final String LAND_EQUIPMENTS_TABLE_NAME="Equipments";
    private static final String COMPANY_TYPES_TABLE_NAME = "CompanyTypes";
    private static final String BOOKMARK_TABLE_NAME = "Bookmarks";
    private static final String OFFICE_TABLE_NAME = "Office";
    private static final String LAWYER_TABLE_NAME = "Lawyer";
    private static final String AGENCY_TABLE_NAME = "Agency";
    private static final String USE_TYPE_TABLE_NAME = "UseType";
    private static final String VOUCHER_TABLE_NAME = "Voucher";
    private static final String LAND_TABLE_NAME = "Land";


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

    private static final String LAND_EQUIPMENTS_TABLE_COLUMN_ID     ="id";
    private static final String LAND_EQUIPMENTS_TABLE_COLUMN_TITLE  ="Title";
    private static final String LAND_EQUIPMENTS_TABLE_COLUMN_LOGO   ="Logo";

    private static final String COMPANY_TYPES_TABLE_COLUMN_ID       ="id";
    private static final String COMPANY_TYPES_TABLE_COLUMN_TITLE    ="Title";
    private static final String COMPANY_TYPES_TABLE_COLUMN_ORDER    ="CTOrder";
    private static final String COMPANY_TYPES_TABLE_COLUMN_PARENT_ID="parent_id";

    private static final String VOUCHER_TABLE_COLUMN_ID           ="id";
    private static final String VOUCHER_TABLE_COLUMN_TITLE        ="Title";

    private static final String BOOKMARK_TABLE_COLUMN_ID            ="id";

    private static final String OFFICE_TABLE_COLUMN_ID = "id";
    private static final String OFFICE_TABLE_COLUMN_TITLE = "Title";
    private static final String OFFICE_TABLE_COLUMN_MANAGER="Manager";
    private static final String OFFICE_TABLE_COLUMN_OFFICE_NO = "OfficeNo";
    private static final String OFFICE_TABLE_COLUMN_ADDRESS = "Address";
    private static final String OFFICE_TABLE_COLUMN_LOGO = "Logo";
    private static final String OFFICE_TABLE_COLUMN_PHONE = "Phone";
    private static final String OFFICE_TABLE_COLUMN_FAX = "Fax";
    private static final String OFFICE_TABLE_COLUMN_DISABLED = "Disabled";
    private static final String OFFICE_TABLE_COLUMN_PROVINE_ID = "province_id";
    private static final String OFFICE_TABLE_COLUMN_CITY_ID = "city_id";
    private static final String OFFICE_TABLE_COLUMN_AREA_ID= "area_id";
    private static final String OFFICE_TABLE_COLUMN_DISTRICT_ID = "district_id";
    private static final String OFFICE_TABLE_COLUMN_USER_ID = "user_id";
    private static final String OFFICE_TABLE_COLUMN_CREATED_AT = "created_at";


    private static final String LAWYER_TABLE_COLUMN_ID ="id";
    private static final String LAWYER_TABLE_COLUMN_FULL_NAME ="FullName";
    private static final String LAWYER_TABLE_COLUMN_IMAGE = "Image";
    private static final String LAWYER_TABLE_COLUMN_DESCRIPTION = "Description";
    private static final String LAWYER_TABLE_COLUMN_DISABLED = "Disabled";
    private static final String LAWYER_TABLE_COLUMN_PHONE = "Phone";
    private static final String LAWYER_TABLE_COLUMN_USER_ID ="user_id";
    private static final String LAWYER_TABLE_COLUMN_CREATED_ID = "created_at";

    private static final String AGENCY_TABLE_COLUMN_ID = "id";
    private static final String AGENCY_TABLE_COLUMN_TITLE = "Title";
    private static final String AGENCY_TABLE_COLUMN_OWNER = "Owner";
    private static final String AGENCY_TABLE_COLUMN_MANAGER="Manager";
    private static final String AGENCY_TABLE_COLUMN_ADDRESS = "Address";
    private static final String AGENCY_TABLE_COLUMN_PROVINE_ID = "province_id";
    private static final String AGENCY_TABLE_COLUMN_CITY_ID = "city_id";
    private static final String AGENCY_TABLE_COLUMN_AREA_ID= "area_id";
    private static final String AGENCY_TABLE_COLUMN_DISTRICT_ID = "district_id";
    private static final String AGENCY_TABLE_COLUMN_USER_ID = "user_id";
    private static final String AGENCY_TABLE_COLUMN_MOBILE = "Mobile";
    private static final String AGENCY_TABLE_COLUMN_PHONE = "Phone";
    private static final String AGENCY_TABLE_COLUMN_LOGO = "Logo";
    private static final String AGENCY_TABLE_COLUMN_DISABLED = "Disabled";
    private static final String AGENCY_TABLE_COLUMN_CREATED_AT = "created_at";

    private static final String USE_TYPE_TABLE_COLUMN_ID          ="id";
    private static final String USE_TYPE_TABLE_COLUMN_TITLE       ="Title";

    private static final String LAND_TABLE_COLUMN_ID = "id";
    private static final String LAND_TABLE_COLUMN_SALE_TOTAL_PRICE = "SaleTotalPrice";
    private static final String LAND_TABLE_COLUMN_MORTGAGE_TOTAL_PRICE = "MortgageTotalPrice";
    private static final String LAND_TABLE_COLUMN_RENT_TOTAL_PRICE = "RentTotalPrice";
    private static final String LAND_TABLE_COLUMN_TITLE = "Title";
    private static final String LAND_TABLE_COLUMN_LAND_STATE_ID = "land_state_id";
    private static final String LAND_TABLE_COLUMN_CREATED_AT = "created_at";
    private static final String LAND_TABLE_COLUMN_LAND_SITUATION_ID = "land_situation_id";
    private static final String LAND_TABLE_COLUMN_VIEW = "LView";
    private static final String LAND_TABLE_COLUMN_IMAGES = "Images";
    private static final String LAND_TABLE_COLUMN_LAND_STATE_TITLE = "LandStateTitle";
    private static final String LAND_TABLE_COLUMN_DISTRICT_ID = "district_id";
    private static final String LAND_TABLE_COLUMN_LAND_SITUATION_TITLE = "LandSituationTitle";
    private static final String LAND_TABLE_COLUMN_LAND_SITUATION_COLOR = "LandSituationColor";
    private static final String LAND_TABLE_COLUMN_FIRST_NAME = "first_name";
    private static final String LAND_TABLE_COLUMN_LAST_NAME = "last_name";
    private static final String LAND_TABLE_COLUMN_LAND_CASE_ID = "land_case_id";


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

    private static final String CREATE_LAND_EQUIPMENTS_TABLE="CREATE TABLE "+LAND_EQUIPMENTS_TABLE_NAME+"("
            + LAND_EQUIPMENTS_TABLE_COLUMN_ID+ " TEXT,"
            + LAND_EQUIPMENTS_TABLE_COLUMN_TITLE + " TEXT,"
            +LAND_EQUIPMENTS_TABLE_COLUMN_LOGO +" TEXT"
            +")";

    private static final String CREATE_COMPANY_TYPES_TABLE="CREATE TABLE "+COMPANY_TYPES_TABLE_NAME+"("
            + COMPANY_TYPES_TABLE_COLUMN_ID + " TEXT,"
            + COMPANY_TYPES_TABLE_COLUMN_TITLE + " TEXT,"
            + COMPANY_TYPES_TABLE_COLUMN_ORDER + " TEXT,"
            + COMPANY_TYPES_TABLE_COLUMN_PARENT_ID + " TEXT"
            +")";

    private static final String CREATE_BOOKMARK_TABLE="CREATE TABLE "+BOOKMARK_TABLE_NAME+"("
            + COMPANY_TYPES_TABLE_COLUMN_ID + " TEXT"
            +")";


    private static final String CREATE_OFFICE_TABLE="CREATE TABLE "+OFFICE_TABLE_NAME+"("
            + OFFICE_TABLE_COLUMN_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_TITLE + " TEXT,"
            + OFFICE_TABLE_COLUMN_MANAGER + " TEXT,"
            + OFFICE_TABLE_COLUMN_OFFICE_NO + " TEXT,"
            + OFFICE_TABLE_COLUMN_ADDRESS + " TEXT,"
            + OFFICE_TABLE_COLUMN_LOGO + " TEXT,"
            + OFFICE_TABLE_COLUMN_PHONE + " TEXT,"
            + OFFICE_TABLE_COLUMN_FAX + " TEXT,"
            + OFFICE_TABLE_COLUMN_DISABLED + " TEXT,"
            + OFFICE_TABLE_COLUMN_PROVINE_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_CITY_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_AREA_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_DISTRICT_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_USER_ID + " TEXT,"
            + OFFICE_TABLE_COLUMN_CREATED_AT + " TEXT"
            +")";

    private static final String CREATE_LAWYER_TABLE="CREATE TABLE "+LAWYER_TABLE_NAME+"("
            + LAWYER_TABLE_COLUMN_ID + " TEXT,"
            + LAWYER_TABLE_COLUMN_FULL_NAME + " TEXT,"
            + LAWYER_TABLE_COLUMN_IMAGE + " TEXT,"
            + LAWYER_TABLE_COLUMN_DESCRIPTION + " TEXT,"
            + LAWYER_TABLE_COLUMN_DISABLED + " TEXT,"
            + LAWYER_TABLE_COLUMN_PHONE + " TEXT,"
            + LAWYER_TABLE_COLUMN_USER_ID + " TEXT,"
            + LAWYER_TABLE_COLUMN_CREATED_ID + " TEXT"
            +")";


    private static final String CREATE_AGENCY_TABLE="CREATE TABLE "+AGENCY_TABLE_NAME+"("
            + AGENCY_TABLE_COLUMN_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_TITLE + " TEXT,"
            + AGENCY_TABLE_COLUMN_OWNER + " TEXT,"
            + AGENCY_TABLE_COLUMN_MANAGER + " TEXT,"
            + AGENCY_TABLE_COLUMN_ADDRESS + " TEXT,"
            + AGENCY_TABLE_COLUMN_PROVINE_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_CITY_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_AREA_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_DISTRICT_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_USER_ID + " TEXT,"
            + AGENCY_TABLE_COLUMN_MOBILE + " TEXT,"
            + AGENCY_TABLE_COLUMN_PHONE + " TEXT,"
            + AGENCY_TABLE_COLUMN_LOGO + " TEXT,"
            + AGENCY_TABLE_COLUMN_DISABLED + " TEXT,"
            + AGENCY_TABLE_COLUMN_CREATED_AT + " TEXT"
            +")";


    private static final String CREATE_VOUCHER_TABLE= "CREATE TABLE "+VOUCHER_TABLE_NAME+"("
            + VOUCHER_TABLE_COLUMN_ID+ " TEXT,"
            + VOUCHER_TABLE_COLUMN_TITLE + " TEXT"
            +")";


    private static final String CREATE_USE_TYPE_TABLE= "CREATE TABLE "+USE_TYPE_TABLE_NAME+"("
            + USE_TYPE_TABLE_COLUMN_ID+ " TEXT,"
            + USE_TYPE_TABLE_COLUMN_TITLE+ " TEXT"
            +")";

    private static final String CREATE_LAND_TABLE="CREATE TABLE "+LAND_TABLE_NAME+"("
            + LAND_TABLE_COLUMN_ID + " TEXT,"
            + LAND_TABLE_COLUMN_SALE_TOTAL_PRICE + " TEXT,"
            + LAND_TABLE_COLUMN_MORTGAGE_TOTAL_PRICE + " TEXT,"
            + LAND_TABLE_COLUMN_RENT_TOTAL_PRICE + " TEXT,"
            + LAND_TABLE_COLUMN_TITLE + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_STATE_ID + " TEXT,"
            + LAND_TABLE_COLUMN_CREATED_AT + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_SITUATION_ID + " TEXT,"
            + LAND_TABLE_COLUMN_VIEW + " TEXT,"
            + LAND_TABLE_COLUMN_IMAGES + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_STATE_TITLE + " TEXT,"
            + LAND_TABLE_COLUMN_DISTRICT_ID + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_SITUATION_TITLE + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_SITUATION_COLOR + " TEXT,"
            + LAND_TABLE_COLUMN_FIRST_NAME + " TEXT,"
            + LAND_TABLE_COLUMN_LAST_NAME + " TEXT,"
            + LAND_TABLE_COLUMN_LAND_CASE_ID + " TEXT"
            +")";

//////////////////////////////////////Provinces methods////////////////////////////////////////////////
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

    ///////////////////////////////////Cities methods///////////////////////////////////////////////
    public void InsertCity(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CITIES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteCities(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+CITIES_TABLE_NAME);
        db.close();
    }

    public CityModel GetCityByID(String id){
        CityModel cityModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+CITIES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetCITYByID: "+cursor.toString());
            cityModel = new CityModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetCityByID: "+e.toString());
        }
        database.close();
        return cityModel;
    }

    public ArrayList<CityModel> GetCitiesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CityModel> cityModels = new ArrayList<CityModel>();
        CityModel cityModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+CITIES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                cityModel = new CityModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                cityModels.add(cityModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Cities Says:", e.toString());}
        db.close();
        return cityModels;
    }

    ///////////////////////////////////Areas methods///////////////////////////////////////////////
    public void InsertArea(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(AREAS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteAreas(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+AREAS_TABLE_NAME);
        db.close();
    }

    public AreaModel GetAreaByID(String id){
        AreaModel areaModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+AREAS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetAreaByID: "+cursor.toString());
             areaModel= new AreaModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetAreaByID: "+e.toString());
        }
        database.close();
        return areaModel;
    }

    public ArrayList<AreaModel> GetAreaList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AreaModel> areaModels = new ArrayList<AreaModel>();
        AreaModel areaModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+AREAS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                areaModel = new AreaModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                areaModels.add(areaModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Cities Says:", e.toString());}
        db.close();
        return areaModels;
    }

    ///////////////////////////////////LandTYPes methods///////////////////////////////////////////////
    public void InsertLandType(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_TYPES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandType(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_TYPES_TABLE_NAME);
        db.close();
    }

    public LandTypeModel GetLandTypeByID(String id){
        LandTypeModel landTypeModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_TYPES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetLandTypeByID: "+cursor.toString());
            landTypeModel = new LandTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetLandTypeByID: "+e.toString());
        }
        database.close();
        return landTypeModel;
    }

    public ArrayList<LandTypeModel> GetLandTypeList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandTypeModel> landTypeModels = new ArrayList<LandTypeModel>();
        LandTypeModel landTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_TYPES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                landTypeModel = new LandTypeModel(
                        c.getString(0),
                        c.getString(1));
                landTypeModels.add(landTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get LandType Says:", e.toString());}
        db.close();
        return landTypeModels;
    }

    ///////////////////////////////////BuildingConditions methods///////////////////////////////////////////////
    public void InsertBuildingCondition(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(BUILDING_CONDITIONS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteBuildingConditions(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+BUILDING_CONDITIONS_TABLE_NAME);
        db.close();
    }

    public BuildingConditionModel GetBuildingConditionByID(String id){
        BuildingConditionModel buildingConditionModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+BUILDING_CONDITIONS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetBuildingConditionByID: "+cursor.toString());
            buildingConditionModel= new BuildingConditionModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetBuildingConditionByID: "+e.toString());
        }
        database.close();
        return buildingConditionModel;
    }

    public ArrayList<BuildingConditionModel> GetBuildingConditionsList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<BuildingConditionModel> buildingConditionModels = new ArrayList<BuildingConditionModel>();
        BuildingConditionModel buildingConditionModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+BUILDING_CONDITIONS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                buildingConditionModel = new BuildingConditionModel(
                        c.getString(0),
                        c.getString(1));
                buildingConditionModels.add(buildingConditionModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Building Cons Says:", e.toString());}
        db.close();
        return buildingConditionModels;
    }

    ///////////////////////////////////LandStates methods///////////////////////////////////////////////
    public void InsertLandState(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_STATES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandStates(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_STATES_TABLE_NAME);
        db.close();
    }

    public LandStateTypeModel GetLandStateByID(String id){
        LandStateTypeModel landStateTypeModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_STATES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetLandStateByID: "+cursor.toString());
            landStateTypeModel= new LandStateTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetlandStateByID: "+e.toString());
        }
        database.close();
        return landStateTypeModel;
    }

    public ArrayList<LandStateTypeModel> GetLandStateList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandStateTypeModel> landStateTypeModels = new ArrayList<LandStateTypeModel>();
        LandStateTypeModel landStateTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_STATES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                landStateTypeModel = new LandStateTypeModel(
                        c.getString(0),
                        c.getString(1));
                landStateTypeModels.add(landStateTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get LandStateList Says:", e.toString());}
        db.close();
        return landStateTypeModels;
    }

    ///////////////////////////////////RentalPreferences methods///////////////////////////////////////////////
    public void InsertRentalPreference(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(RENTAL_PREFERENCES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteRentalPreference(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+RENTAL_PREFERENCES_TABLE_NAME);
        db.close();
    }

    public RentalPreferenceModel GetRentalRantalByID(String id){
        RentalPreferenceModel rentalPreferenceModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+RENTAL_PREFERENCES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetRentalPrefByID: "+cursor.toString());
            rentalPreferenceModel= new RentalPreferenceModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetRentalPrefByID: "+e.toString());
        }
        database.close();
        return rentalPreferenceModel;
    }

    public ArrayList<RentalPreferenceModel> GetRentalPreferenceList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<RentalPreferenceModel> rentalPreferenceModels = new ArrayList<RentalPreferenceModel>();
        RentalPreferenceModel rentalPreferenceModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+RENTAL_PREFERENCES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                rentalPreferenceModel = new RentalPreferenceModel(
                        c.getString(0),
                        c.getString(1));
                rentalPreferenceModels.add(rentalPreferenceModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetRentalPrefList Says:", e.toString());}
        db.close();
        return rentalPreferenceModels;
    }

    ///////////////////////////////////DensityTypes methods///////////////////////////////////////////////
    public void InsertDensityType(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DENSITY_TYPES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteDensityTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+DENSITY_TYPES_TABLE_NAME);
        db.close();
    }

    public DensityTypeModel GetDensityTypeByID(String id){
        DensityTypeModel densityTypeModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+DENSITY_TYPES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetDensityByID: "+cursor.toString());
            densityTypeModel= new DensityTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetDensityTypesByID: "+e.toString());
        }
        database.close();
        return densityTypeModel;
    }

    public ArrayList<DensityTypeModel> GetDensityTypesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DensityTypeModel> densityTypeModels = new ArrayList<DensityTypeModel>();
        DensityTypeModel densityTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+DENSITY_TYPES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                densityTypeModel = new DensityTypeModel(
                        c.getString(0),
                        c.getString(1));
                densityTypeModels.add(densityTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get DensityList Says:", e.toString());}
        db.close();
        return densityTypeModels;
    }

    ///////////////////////////////////FloorCoverings methods///////////////////////////////////////////////
    public void InsertFloorCovering(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(FLOOR_COVERINGS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteFloorCoverings(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+FLOOR_COVERINGS_TABLE_NAME);
        db.close();
    }

    public FloorCoveringModel GetFloorCoveringByID(String id){
        FloorCoveringModel floorCoveringModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+FLOOR_COVERINGS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetFloorCoveringByID: "+cursor.toString());
            floorCoveringModel= new FloorCoveringModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetFloorCoveringByID: "+e.toString());
        }
        database.close();
        return floorCoveringModel;
    }

    public ArrayList<FloorCoveringModel> GetFloorCoveringList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<FloorCoveringModel> floorCoveringModels = new ArrayList<FloorCoveringModel>();
        FloorCoveringModel floorCoveringModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+FLOOR_COVERINGS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                floorCoveringModel = new FloorCoveringModel(
                        c.getString(0),
                        c.getString(1));
                floorCoveringModels.add(floorCoveringModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get FloorCovers Says:", e.toString());}
        db.close();
        return floorCoveringModels;
    }

    ///////////////////////////////////KitchenServices methods///////////////////////////////////////////////
    public void InsertKitchenServices(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(KITCHEN_SERVICES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteKitchenServices(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+KITCHEN_SERVICES_TABLE_NAME);
        db.close();
    }

    public KitchenServiceModel GetKitchenServicesByID(String id){
        KitchenServiceModel kitchenServiceModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+KITCHEN_SERVICES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            kitchenServiceModel= new KitchenServiceModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetKitchenServicesByID: "+e.toString());
        }
        database.close();
        return kitchenServiceModel;
    }

    public ArrayList<KitchenServiceModel> GetKitchenServicesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<KitchenServiceModel> kitchenServiceModels = new ArrayList<KitchenServiceModel>();
        KitchenServiceModel kitchenServiceModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+KITCHEN_SERVICES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                kitchenServiceModel = new KitchenServiceModel(
                        c.getString(0),
                        c.getString(1));
                kitchenServiceModels.add(kitchenServiceModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get KitchenServs Says:", e.toString());}
        db.close();
        return kitchenServiceModels;
    }

    ///////////////////////////////////LandCases methods///////////////////////////////////////////////
    public void InsertLandCaseType(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_CASES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandCaseTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_CASES_TABLE_NAME);
        db.close();
    }

    public LandCaseTypeModel GetLandCaseTypeByID(String id){
        LandCaseTypeModel landCaseTypeModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_CASES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            landCaseTypeModel= new LandCaseTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetLandCaseByID: "+e.toString());
        }
        database.close();
        return landCaseTypeModel;
    }

    public ArrayList<LandCaseTypeModel> GetLandCaseTypesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandCaseTypeModel> landCaseTypeModels = new ArrayList<LandCaseTypeModel>();
        LandCaseTypeModel landCaseTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_CASES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                landCaseTypeModel = new LandCaseTypeModel(
                        c.getString(0),
                        c.getString(1));
                landCaseTypeModels.add(landCaseTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get LandCaseType Says:", e.toString());}
        db.close();
        return landCaseTypeModels;
    }

    ///////////////////////////////////LoanTypes methods///////////////////////////////////////////////
    public void InsertLoanType(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LOAN_TYPES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLoanTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LOAN_TYPES_TABLE_NAME);
        db.close();
    }

    public LoanTypeModel GetLoanTypeByID(String id){
        LoanTypeModel loanTypeModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LOAN_TYPES_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            loanTypeModel= new LoanTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetLoanTypeByID: "+e.toString());
        }
        database.close();
        return loanTypeModel;
    }

    public ArrayList<LoanTypeModel> GetLoanTypesList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LoanTypeModel> loanTypeModels= new ArrayList<LoanTypeModel>();
        LoanTypeModel loanTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LOAN_TYPES_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                loanTypeModel = new LoanTypeModel(
                        c.getString(0),
                        c.getString(1));
                loanTypeModels.add(loanTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get LoanTypes Says:", e.toString());}
        db.close();
        return loanTypeModels;
    }

    ///////////////////////////////////LandSituations methods///////////////////////////////////////////////
    public void InsertLandSituation(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_SITUATIONS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandSituations(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_SITUATIONS_TABLE_NAME);
        db.close();
    }

    public LandSituationModel GetLandSituationByID(String id){
        LandSituationModel landSituationModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_SITUATIONS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            landSituationModel= new LandSituationModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetSituationByID: "+e.toString());
        }
        database.close();
        return landSituationModel;
    }

    public ArrayList<LandSituationModel> GetSituationList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandSituationModel> landSituationModels = new ArrayList<LandSituationModel>();
        LandSituationModel situationModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_SITUATIONS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                situationModel = new LandSituationModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                landSituationModels.add(situationModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get Situation Says:", e.toString());}
        db.close();
        return landSituationModels;
    }

    ///////////////////////////////////LandViews methods///////////////////////////////////////////////
    public void InsertLandView(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_VIEWS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandViews(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_VIEWS_TABLE_NAME);
        db.close();
    }

    public LandViewModel GetLandViewByID(String id){
        LandViewModel landViewModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_VIEWS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            landViewModel= new LandViewModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetLandViewByID: "+e.toString());
        }
        database.close();
        return landViewModel;
    }

    public ArrayList<LandViewModel> GetLandViewsList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandViewModel> landViewModels = new ArrayList<LandViewModel>();
        LandViewModel landViewModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_VIEWS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                landViewModel = new LandViewModel(
                        c.getString(0),
                        c.getString(1));
                landViewModels.add(landViewModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get LandView Says:", e.toString());}
        db.close();
        return landViewModels;
    }

    ///////////////////////////////////LandDirections methods///////////////////////////////////////////////
    public void InsertLandDirection(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_DIRECTIONS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLadDirections(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_DIRECTIONS_TABLE_NAME);
        db.close();
    }

    public LandDirectionModel GetLandDirectionByID(String id){
        LandDirectionModel landDirectionModel =null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+LAND_DIRECTIONS_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            landDirectionModel= new LandDirectionModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetLandDirectionByID: "+e.toString());
        }
        database.close();
        return landDirectionModel;
    }

    public ArrayList<LandDirectionModel> GetLandDirectionsList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandDirectionModel> landDirectionModels = new ArrayList<LandDirectionModel>();
        LandDirectionModel landDirectionModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_DIRECTIONS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                landDirectionModel = new LandDirectionModel(
                        c.getString(0),
                        c.getString(1));
                landDirectionModels.add(landDirectionModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetLandDirections Says:", e.toString());}
        db.close();
        return landDirectionModels;
    }

    ////////////////////////////////////////Land Equipments////////////////////////////////////////////////
    public void InsertLandEquipments(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_EQUIPMENTS_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLandEquipments(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_EQUIPMENTS_TABLE_NAME);
        db.close();
    }
    public ArrayList<EquipmentModel> GetLandEquipmentsList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<EquipmentModel> equipmentModels = new ArrayList<EquipmentModel>();
        EquipmentModel equipmentModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_EQUIPMENTS_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                equipmentModel = new EquipmentModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                equipmentModels.add(equipmentModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetLandEquipments Says:", e.toString());}
        db.close();
        return equipmentModels;
    }

    public ArrayList<EquipmentModel> GetLandEquipmentsListByLandID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<EquipmentModel> equipmentModels = new ArrayList<EquipmentModel>();
        EquipmentModel equipmentModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+LAND_EQUIPMENTS_TABLE_NAME+" WHERE id = "+id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                equipmentModel = new EquipmentModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2));
                equipmentModels.add(equipmentModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetEquipmentsList Says:", e.toString());}
        db.close();
        return equipmentModels;
    }


    //////////////////////////////////CompanyTypes/////////////////////////////////////////
    public void InsertCompanyTypes(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(COMPANY_TYPES_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteCompanyTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+COMPANY_TYPES_TABLE_NAME);
        db.close();
    }
    public ArrayList<CompanyTypeModel> GetCompanyTypesByParentID(String id){
        Log.d(TAG, "GetCompanyTypesByParentID: is "+id);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CompanyTypeModel> companyTypeModels = new ArrayList<CompanyTypeModel>();
        CompanyTypeModel  companyTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+COMPANY_TYPES_TABLE_NAME+" WHERE parent_id = "+id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            Log.d(TAG, "GetCompanyTypesByParentID: "+c.getCount());
            do{
                companyTypeModel = new CompanyTypeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3));
                companyTypeModels.add(companyTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetCompanyTypes Says:", e.toString());}
        db.close();
        return companyTypeModels;
    }

    public ArrayList<CompanyTypeModel> GetParentCompanyTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CompanyTypeModel> companyTypeModels = new ArrayList<CompanyTypeModel>();
        CompanyTypeModel  companyTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+COMPANY_TYPES_TABLE_NAME+" WHERE parent_id = 'null'", null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                companyTypeModel = new CompanyTypeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3));
                companyTypeModels.add(companyTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("GetCompanyTypes Says:", e.toString());}
        db.close();
        return companyTypeModels;
    }

    //////////////Bookmarks Method/////////////
    public void InsertBookmark(String landID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.BOOKMARK_ID,landID);
        db.insert(BOOKMARK_TABLE_NAME, null, cv);
        Log.d(TAG, "InsertBookmark: "+landID);
        db.close();
    }

    public void DeleteBookmarks(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+BOOKMARK_TABLE_NAME);
        db.close();
    }

    public void DeleteBookmarkByLandID(String landid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOKMARK_TABLE_NAME, Constants.BOOKMARK_ID + "=" + landid, null);
        db.close();
    }

    public Boolean isBookmarkedByLandID(String landid){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean landExist=false;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+BOOKMARK_TABLE_NAME+" WHERE id = "+landid, null);
            if(c.getCount()>0)landExist=true;
            c.close();
        }catch (Exception e){
            Log.d(TAG, "isBookmarkedBayLandID: "+e.toString());
        }
        db.close();

        return landExist;
    }

    ////////////////////////////////////////////Office Methods////////////////////////////////////////////////////
    public void InsertOffice(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(OFFICE_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteOffices(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+OFFICE_TABLE_NAME);
        db.close();
    }

    public ArrayList<OfficeModel> GetOfficesByProvinceID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<OfficeModel> officeModels = new ArrayList<OfficeModel>();
        OfficeModel officeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + OFFICE_TABLE_NAME + " WHERE province_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                officeModel = new OfficeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14)
                );
                officeModels.add(officeModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetOfficesByProvinceID: "+e.toString());
        }
            db.close();
            return officeModels;
    }

    public ArrayList<OfficeModel> GetOfficesByCityID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<OfficeModel> officeModels = new ArrayList<OfficeModel>();
        OfficeModel officeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + OFFICE_TABLE_NAME + " WHERE city_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                officeModel = new OfficeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14)
                );
                officeModels.add(officeModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetOfficesByCityID: "+e.toString());
        }
        db.close();
        return officeModels;
    }

    public ArrayList<OfficeModel> GetOfficesByAreaID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<OfficeModel> officeModels = new ArrayList<OfficeModel>();
        OfficeModel officeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + OFFICE_TABLE_NAME + " WHERE area_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                officeModel = new OfficeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14)
                );
                officeModels.add(officeModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetOfficesByAreaID: "+e.toString());
        }
        db.close();
        return officeModels;
    }

    public ArrayList<OfficeModel> GetOfficesByDistrictID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<OfficeModel> officeModels = new ArrayList<OfficeModel>();
        OfficeModel officeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + OFFICE_TABLE_NAME + " WHERE district_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                officeModel = new OfficeModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14)
                );
                officeModels.add(officeModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetOfficesByDistrictID: "+e.toString());
        }
        db.close();
        return officeModels;
    }

///////////////////////// Lawyer Methods //////////////////////////////
public void InsertLawyer(ContentValues cv){
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(LAWYER_TABLE_NAME, null, cv);
    db.close();
}

public void DeleteLawyers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAWYER_TABLE_NAME);
        db.close();
    }

public ArrayList<LawyerModel> GetLawyers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LawyerModel> lawyerModels = new ArrayList<LawyerModel>();
        LawyerModel lawyerModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + LAWYER_TABLE_NAME , null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                lawyerModel = new LawyerModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7)
                );
                lawyerModels.add(lawyerModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetLawyers: "+e.toString());
        }
        db.close();
        return lawyerModels;
    }

    ///////////////////////// Lawyer Agency //////////////////////////////
    public void InsertAgency(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(AGENCY_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteAgency(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+AGENCY_TABLE_NAME);
        db.close();
    }

    public ArrayList<AgencyModel> GetAgencies() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AgencyModel> agencyModels = new ArrayList<AgencyModel>();
        AgencyModel agencyModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + AGENCY_TABLE_NAME , null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                agencyModel = new AgencyModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15)
                );
                agencyModels.add(agencyModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetAgencies: "+e.toString());
        }
        db.close();
        return agencyModels;
    }

    public ArrayList<AgencyModel> GetAgenciesByProvinceID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AgencyModel> agencyModels = new ArrayList<AgencyModel>();
        AgencyModel agencyModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + AGENCY_TABLE_NAME + " WHERE province_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                agencyModel = new AgencyModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15)
                );
                agencyModels.add(agencyModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetAgenciesByProvinceID: "+e.toString());
        }
        db.close();
        return agencyModels;
    }

    public ArrayList<AgencyModel> GetAgenciesByCityID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AgencyModel> agencyModels = new ArrayList<AgencyModel>();
        AgencyModel agencyModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + AGENCY_TABLE_NAME + " WHERE city_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                agencyModel = new AgencyModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15)
                );
                agencyModels.add(agencyModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetAgenciesByCityID: "+e.toString());
        }
        db.close();
        return agencyModels;
    }

    public ArrayList<AgencyModel> GetAgenciesByAreaID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AgencyModel> agencyModels = new ArrayList<AgencyModel>();
        AgencyModel agencyModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + AGENCY_TABLE_NAME + " WHERE area_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                agencyModel = new AgencyModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15)
                );
                agencyModels.add(agencyModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetAgenciesByAreaID: "+e.toString());
        }
        db.close();
        return agencyModels;
    }

    public ArrayList<AgencyModel> GetAgenciesByDistrictID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AgencyModel> agencyModels = new ArrayList<AgencyModel>();
        AgencyModel agencyModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + AGENCY_TABLE_NAME + " WHERE district_id = " + id, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                agencyModel = new AgencyModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15)
                );
                agencyModels.add(agencyModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetAgenciesByDistrictID: "+e.toString());
        }
        db.close();
        return agencyModels;
    }



    public void InsertUseType(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USE_TYPE_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteUseType(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+USE_TYPE_TABLE_NAME);
        db.close();
    }

    public ArrayList<UseTypeModel> GetUseTypeList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<UseTypeModel> useTypeModels = new ArrayList<UseTypeModel>();
        UseTypeModel useTypeModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+USE_TYPE_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                useTypeModel = new UseTypeModel(
                        c.getString(0),
                        c.getString(1));
                useTypeModels.add(useTypeModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get UseType Says:", e.toString());}
        db.close();
        return useTypeModels;
    }

    public UseTypeModel GetUseTypeByID(String id){
        UseTypeModel useTypeModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+USE_TYPE_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetUseTypeByID: "+cursor.toString());
            useTypeModel= new UseTypeModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetUseTypeByID: "+e.toString());
        }
        database.close();
        return useTypeModel;
    }


    //////////////////Voucher Methods////////////////
    public void InsertVoucher(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(VOUCHER_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteVouchers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+VOUCHER_TABLE_NAME);
        db.close();
    }

    public ArrayList<VoucherModel> GetVouchersList(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<VoucherModel> voucherModels = new ArrayList<VoucherModel>();
        VoucherModel voucherModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM "+VOUCHER_TABLE_NAME, null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do{
                voucherModel = new VoucherModel(
                        c.getString(0),
                        c.getString(1));
                voucherModels.add(voucherModel);
                counter++;
            }while (c.moveToNext());
            c.close();
        }catch (Exception e){
            Log.d("Get UseType Says:", e.toString());}
        db.close();
        return voucherModels;
    }
    public VoucherModel GetVoucherByID(String id){
        VoucherModel voucherModel=null;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;
        try {
            cursor = database.rawQuery("SELECT * FROM "+VOUCHER_TABLE_NAME+" WHERE id = "+id, null);
            if (cursor==null) return null;

            cursor.moveToFirst();
            Log.d(TAG, "GetVoucherByID: "+cursor.toString());
            voucherModel= new VoucherModel(
                    cursor.getString(0),
                    cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Log.d(TAG, "GetVoucherByID: "+e.toString());
        }
        database.close();
        return voucherModel;
    }


    ///////////////////////// Total Land Methods //////////////////////////////
    public void InsertLand(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(LAND_TABLE_NAME, null, cv);
        db.close();
    }

    public void DeleteLand(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+LAND_TABLE_NAME);
        db.close();
    }

    public ArrayList<LandModel> GetAllLands() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandModel> landModels = new ArrayList<LandModel>();
        LandModel landModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + LAND_TABLE_NAME , null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                landModel = new LandModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15),
                        c.getString(16)
                );
                landModels.add(landModel);
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetLands: "+e.toString());
        }
        db.close();
        return landModels;
    }

    public ArrayList<LandModel> GetAllFeaturedLands() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandModel> landModels = new ArrayList<LandModel>();
        LandModel landModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + LAND_TABLE_NAME , null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                landModel = new LandModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15),
                        c.getString(16)
                );
                if(Integer.parseInt(c.getString(16))>1) {
                    landModels.add(landModel);
                }
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetFeaturedLands: "+e.toString());
        }
        db.close();
        return landModels;
    }

    public ArrayList<LandModel> GetAllFeatured20Lands() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<LandModel> landModels = new ArrayList<LandModel>();
        LandModel landModel;
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM " + LAND_TABLE_NAME , null);
            if (c == null)
                return null;
            int counter = 0;
            c.moveToFirst();
            do {
                landModel = new LandModel(
                        c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        c.getString(13),
                        c.getString(14),
                        c.getString(15),
                        c.getString(16)
                );
                if(Integer.parseInt(c.getString(16))>1 && counter<20) {
                    landModels.add(landModel);
                }
                counter++;
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "GetFeaturedLands: "+e.toString());
        }
        db.close();
        return landModels;
    }

//----------------------------------------------------------------------------------------------

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
        sqLiteDatabase.execSQL(CREATE_DISTRICT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CITIES_TABLE);
        sqLiteDatabase.execSQL(CREATE_AREAS_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_TYPES_TABLE);
        sqLiteDatabase.execSQL(CREATE_BUILDING_CONDITIONS_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_STATES_TABLE);
        sqLiteDatabase.execSQL(CREATE_RENTAL_PREFERENCE_TABLE);
        sqLiteDatabase.execSQL(CREATE_DENSITY_TYPES_TABLE);
        sqLiteDatabase.execSQL(CREATE_FLOOR_COVERINGS_TABLE);
        sqLiteDatabase.execSQL(CREATE_KITCHEN_SERVICES_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_CASE_TABLE);
        sqLiteDatabase.execSQL(CREATE_LOAN_TYPES_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_SITUATION_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_VIEWS_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_DIRECTIONS_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_EQUIPMENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_COMPANY_TYPES_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOKMARK_TABLE);
        sqLiteDatabase.execSQL(CREATE_OFFICE_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAWYER_TABLE);
        sqLiteDatabase.execSQL(CREATE_AGENCY_TABLE);
        sqLiteDatabase.execSQL(CREATE_USE_TYPE_TABLE);
        sqLiteDatabase.execSQL(CREATE_VOUCHER_TABLE);
        sqLiteDatabase.execSQL(CREATE_LAND_TABLE);

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_EQUIPMENTS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+COMPANY_TYPES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BOOKMARK_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+OFFICE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAWYER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+AGENCY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+USE_TYPE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+VOUCHER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LAND_TABLE_NAME );



    }
}

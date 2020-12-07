package ir.hamedanmelk.hamedanmelk.tools;

import java.sql.SQLTransactionRollbackException;

public class Constants {

    //HttpRequestHandler
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    // Default Map Location
    public static final double MAP_EYDAN_LAT = 34.798315;
    public static final double MAP_MEYDAN_LNG= 48.514898;
    //JSON Response Fields
    public static final String JSON_RESPONSE_DATA = "Data";
    public static final String JSON_RESPONSE_STATE = "State";
    //User login
    public static final String USER_MODEL_ID = "id";
    public static final String USER_MODEL_EMAIL= "email";
    public static final String USER_MODEL_FIRST_NAME= "first_name";
    public static final String USER_MODEL_LAST_NAME= "last_name";
    public static final String USER_MODEL_IMAGE= "Image";
    public static final String[] USER_MODEL_FIELDS={"id","email","first_name","last_name","Image"};

    //RegisterPublicUser
    public static final String REGISTER_PUBLIC_USER_FIRST_NAME="FirstName";
    public static final String REGISTER_PUBLIC_USER_LAST_NAME="LastName";
    public static final String REGISTER_PUBLIC_USER_GENDER_TYPE="GenderType";
    public static final String REGISTER_PUBLIC_USER_MOBILE="Mobile";
    public static final String[] REGISTER_PUBLIC_USER_MODEL_FIELDS={"FirstName","LastName","GenderType","Mobile"};

    //Rent model
    public static final String RENT_MODEL_ID="id";
    public static final String RENT_MODEL_TOTAL_MORTGAGE_PRICE="MortgageTotalPrice";
    public static final String RENT_MODEL_TOTAL_RENT_PRICE="RentTotalPrice";
    public static final String RENT_MODEL_TITLE="Title";
    public static final String RENT_MODEL_LAND_STATE_ID="land_state_id";
    public static final String RENT_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String RENT_MODEL_VIEW="View";
    public static final String RENT_MODEL_IMAGES="Images";
    public static final String RENT_MODEL_LAND_STATE_TITLE="LandStateTitle";
    public static final String RENT_MODEL_DISTRICT_ID = "district_id";
    public static final String RENT_MODEL_LAND_SITUATION_TITLE="LandSituationTitle";
    public static final String RENT_MODEL_LAND_SITUATION_COLOR="LandSituationColor";
    public static final String RENT_MODEL_FIRST_NAME="first_name";
    public static final String RENT_MODEL_LAST_NAME="last_name";
    public static final String[] RENT_MODEL_FIELDS={"id","Title","land_state_id","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};
    //Lands model
    public static final String LAND_MODEL_ID="id";
    public static final String LAND_MODEL_TOTAL_PRICE="SaleTotalPrice";
    public static final String LAND_MODEL_TOTAL_MORTGAGE_PRICE="MortgageTotalPrice";
    public static final String LAND_MODEL_TOTAL_RENT_PRICE="RentTotalPrice";
    public static final String LAND_MODEL_TITLE="Title";
    public static final String LAND_MODEL_LAND_STATE_ID="land_state_id";
    public static final String LAND_MODEL_CREATED_AT="created_at";
    public static final String LAND_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String LAND_MODEL_VIEW="View";
    public static final String LAND_MODEL_IMAGES="Images";
    public static final String LAND_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String LAND_MODEL_LANDSITUATIONTITLE="LandSituationTitle";
    public static final String LAND_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String LAND_MODEL_FIRST_NAME="first_name";
    public static final String LAND_MODEL_LAST_NAME="last_name";
    public static final String[] LAND_MODEL_FIELDS={"ID","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};

    // PreSale model
    public static final String PRE_SALE_MODEL_ID="id";
    public static final String PRE_SALE_MODEL_TOTAL_SALE_PRICE="SaleTotalPrice";
    public static final String PRE_SALE_MODEL_TITLE="Title";
    public static final String PRE_SALE_MODEL_LAND_STATE_ID="land_state_id";
    public static final String PRE_SALE_MODEL_CREATED_AT="created_at";
    public static final String PRE_SALE_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String PRE_SALE_MODEL_VIEW="View";
    public static final String PRE_SALE_MODEL_IMAGES="Images";
    public static final String PRE_SALE_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String PRE_SALE_MODEL_DISTRICT_ID = "district_id";
    public static final String PRE_SALE_MODEL_LAND_SITUATIONTITLE="LandSituationTitle";
    public static final String PRE_SALE_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String PRE_SALE_MODEL_FIRST_NAME="first_name";
    public static final String PRE_SALE_MODEL_LAST_NAME="last_name";
    public static final String[] PRE_SALE_MODEL_FIELDS={"id","SaleTotalPrice","Title","land_state_id","created_at","land_situation_id","View","Images","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};


    //Sale model
    public static final String SALE_MODEL_ID="id";
    public static final String SALE_MODEL_TOTAL_PRICE="SaleTotalPrice";
    public static final String SALE_MODEL_TITLE="Title";
    public static final String SALE_MODEL_LAND_STATE_ID="land_state_id";
    public static final String SALE_MODEL_CREATED_AT="created_at";
    public static final String SALE_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String SALE_MODEL_VIEW="View";
    public static final String SALE_MODEL_IMAGES="Images";
    public static final String SALE_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String SALE_MODEL_DISTRICT_ID = "district_id";
    public static final String SALE_MODEL_LAND_SITUATIONTITLE="LandSituationTitle";
    public static final String SALE_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String SALE_MODEL_FIRST_NAME="first_name";
    public static final String SALE_MODEL_LAST_NAME="last_name";
    public static final String[] SALE_MODEL_FIELDS={"id","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};


    //Exchange Model
    public static final String EXCHANGE_MODEL_ID="id";
    public static final String EXCHANGE_MODEL_TITLE="Title";
    public static final String EXCHANGE_MODEL_LAND_STATE_ID="land_state_id";
    public static final String EXCHANGE_MODEL_CREATED_AT="created_at";
    public static final String EXCHANGE_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String EXCHANGE_MODEL_VIEW="View";
    public static final String EXCHANGE_MODEL_IMAGES="Images";
    public static final String EXCHANGE_MODEL_LAND_STATE_TITLE="LandStateTitle";
    public static final String EXCHANGE_MODEL_LAND_SITUATION_TITLE="LandSituationTitle";
    public static final String EXCHANGE_MODEL_LAND_SITUATION_COLOR="LandSituationColor";
    public static final String EXCHANGE_MODEL_FIRST_NAME="first_name";
    public static final String EXCHANGE_MODEL_LAST_NAME="last_name";
    public static final String[] EXCHANGE_MODEL_FIELDS={"ID","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};

    //Assignment Model

    public static final String ASSIGNMENT_MODEL_ID="id";
    public static final String ASSIGNMENT_MODEL_TITLE="Title";
    public static final String ASSIGNMENT_MODEL_LAND_STATE_ID="land_state_id";
    public static final String ASSIGNMENT_MODEL_CREATED_AT="created_at";
    public static final String ASSIGNMENT_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String ASSIGNMENT_MODEL_VIEW="View";
    public static final String ASSIGNMENT_MODEL_IMAGES="Images";
    public static final String ASSIGNMENT_MODEL_LAND_STATE_TITLE="LandStateTitle";
    public static final String ASSIGNMENT_MODEL_LAND_SITUATION_TITLE="LandSituationTitle";
    public static final String ASSIGNMENT_MODEL_LAND_SITUATION_COLOR="LandSituationColor";
    public static final String ASSIGNMENT_MODEL_FIRST_NAME="first_name";
    public static final String ASSIGNMENT_MODEL_LAST_NAME="last_name";    public static final String[] ASSIGNMENT_MODEL_FIELDS={"ID","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};


    // UserLand model
    public static final String USER_LAND_MODEL_ID="id";
    public static final String USER_LAND_MODEL_TITLE="Title";
    public static final String USER_LAND_MODEL_LAND_STATE_ID="land_state_id";
    public static final String USER_LAND_MODEL_CREATED_AT="created_at";
    public static final String USER_LAND_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String USER_LAND_MODEL_VIEW="View";
    public static final String USER_LAND_MODEL_IMAGES="Images";
    public static final String USER_LAND_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String USER_LAND_MODEL_DISTRICT_ID = "district_id";
    public static final String USER_LAND_MODEL_LAND_SITUATIONTITLE="LandSituationTitle";
    public static final String USER_LAND_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String USER_LAND_MODEL_FIRST_NAME="first_name";
    public static final String USER_LAND_MODEL_LAST_NAME="last_name";
    public static final String[] USER_LAND_MODEL_FIELDS={"id","Title","land_state_id","created_at","land_situation_id","View","Images","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};


    //Land Info
    public static final String LAND_INFO_ID="id";
    public static final String LAND_INFO_TITLe="Title";
    public static final String LAND_INFO_ADDRESS="Address";
    public static final String LAND_INFO_MAP="Map";
    public static final String LAND_INFO_IMAGES="Images";
    public static final String LAND_INFO_VIDEOS="Videos";
    public static final String LAND_INFO_PROVINCE_ID="province_id";
    public static final String LAND_INFO_CITY_ID="city_id";
    public static final String LAND_INFO_AREA_ID="area_id";
    public static final String LAND_INFO_DISTRICT_ID="district_id";
    public static final String LAND_INFO_LAND_TYPE_ID="land_type_id";
    public static final String LAND_INFO_BUILDING_CONDITION_ID="building_condition_id";
    public static final String LAND_INFO_BUILDING_YEAR="BuildingYear";
    public static final String LAND_INFO_LAND_STATE_ID="land_state_id";
    public static final String LAND_INFO_SALE_TOTAL_PRICE="SaleTotalPrice";
    public static final String LAND_INFO_SALE_TOTAL_PRICE_UNIT="SaleTotalPriceUnit";
    public static final String LAND_INFO_DEBT_TOTAL_PRICE="DebtTotalPrice";
    public static final String LAND_INFO_DEBT_TOTAL_PRICE_UNIT="DebtTotalPriceUnit";
    public static final String LAND_INFO_MORTGAGE_TOTAL_PRICE="MortgageTotalPrice";
    public static final String LAND_INFO_MORTGAGE_TOTAL_PRICE_UNIT="MortgageTotalPriceUnit";
    public static final String LAND_INFO_RENT_TOTAL_PRICE="RentTotalPrice";
    public static final String LAND_INFO_RENT_TOTAL_PRICE_UNIT="RentTotalPriceUnit";
    public static final String LAND_INFO_PRE_PAY_PRICE="PrePayPrice";
    public static final String LAND_INFO_PRE_PAY_PRICE_UNIT="PrePayPriceUnit";
    public static final String LAND_INFO_DELIVERY_DATE="DeliveryDate";
    public static final String LAND_INFO_DESCRIPTION = "Description";
    public static final String LAND_INFO_RENTAL_PREFERENCE_ID="rental_preference_id";
    public static final String LAND_INFO_RESIDENT_OWNER="ResidentOwner";
    public static final String LAND_INFO_VOUCHER_TYPE_ID="voucher_type_id";
    public static final String LAND_INFO_DONG="Dong";
    public static final String LAND_INFO_DENSITY="density_id";
    public static final String LAND_INFO_SPACE="Space";
    public static final String LAND_INFO_SPACE_UNIT_ID="space_unit_id";
    public static final String LAND_INFO_FOUNDATION_SPACE="FoundationSpace";
    public static final String LAND_INFO_FOUNDATION_SPACE_UNIT_ID="foundation_space_unit_id";
    public static final String LAND_INFO_SPACE_PRICE="SpacePrice";
    public static final String LAND_INFO_DIRECTION_ID="direction_id";
    public static final String LAND_INFO_LAND_VIEW_ID="land_view_id";
    public static final String LAND_INFO_FLOOR_COVERING_ID="floor_covering_id";
    public static final String LAND_INFO_KITCHEN_SERVICE_ID="kitchen_service_id";
    public static final String LAND_INFO_ROOM_COUNT="RoomCount";
    public static final String LAND_INFO_FLOOR_COUNT="FloorCount";
    public static final String LAND_INFO_UNIT_IN_FLOOR="UnitInFloor";
    public static final String LAND_INFO_FLOOR="Floor";
    public static final String LAND_INFO_LAND_CASE_ID="land_case_id";
    public static final String LAND_INFO_LOAN_TYPE_ID="loan_type_id";
    public static final String LAND_INFO_LAND_SITUATION_ID="land_situation_id";
    public static final String LAND_INFO_USER_ID="user_id";
    public static final String LAND_INFO_LAND_OWNER_ID="land_owner_id";
    public static final String LAND_INFO_VIEW="View";
    public static final String LAND_INFO_CREATED_AT="created_at";
    public static final String LAND_INFO_UPDATED_AT="updated_at";
    public static final String LAND_INFO_LAND_TYPE_TITLE="LandTypeTitle";
    public static final String LAND_INFO_LAND_STATE_TITLE="LandStateTitle";
    public static final String LAND_INFO_PROVINCE_TITLE="ProvinceTitle";
    public static final String LAND_INFO_CITY_TITLE="CityTitle";
    public static final String LAND_INFO_AREA_TITLE="AreaTitle";
    public static final String LAND_INFO_DISTRICT_TITLE="DistrictTitle";
    public static final String LAND_INFO_FIRST_NAME="first_name";
    public static final String LAND_INFO_LAST_NAME="last_name";
    public static final String LAND_INFO_USER_DESCRIPTION="UserDescription";
    public static final String LAND_INFO_USER_PHONE = "Mobile";
    public static final String LAND_INFO_USER_IMAGE = "Image";
//    public static final String[] LAND_INFO_FIELDS={"id","Title","Address","Map","Images","Videos","province_id","city_id","area_id","district_id","land_type_id","building_condition_id","BuildingYear","land_state_id","SaleTotalPrice","SaleTotalPriceUnit","DebtTotalPrice","DebtTotalPriceUnit","MortgageTotalPrice","MortgageTotalPriceUnit","RentTotalPrice","RentTotalPriceUnit","PrePayPrice","PrePayPriceUnit","DeliveryDate","rental_preference_id","ResidentOwner","voucher_type_id","Dong","density_id","Space","space_unit_id","FoundationSpace","foundation_space_unit_id","SpacePrice","direction_id","land_view_id","floor_covering_id","kitchen_service_id","RoomCount","FloorCount","UnitInFloor","Floor","land_case_id","loan_type_id","land_situation_id","user_id","land_owner_id","View","created_at","updated_at","LandTypeTitle","LandStateTitle","ProvinceTitle","CityTitle","AreaTitle","DistrictTitle","first_name","last_name","UserDescription"};


    //GetProvinceModelFields
    public static final String[] PROVINCE_MODEL_FIELDS = {"id","Title","TelegramChannelName","Disabled"};
//    public static final String PROVINCE_MODEL_ID="id";
//    public static final String PROVINCE_MODEL_TITLE="Title";
//    public static final String PROVINCE_MODEL_TELEGRAM_CHANNEL_NAME="TelegramChannelName";
//    public static final String PROVINCE_MODEL_DISABLED="Disabled";

    //GetCitiesModelFields
    public static final String[] CITIES_MODEL_FIELDS = {"id","Title","province_id"};
//    public static final String PREF_GET_CITIES_ID="id";
//    public static final String PREF_GET_CITIES_TITLE="Title";
//    public static final String PREF_GET_CITIES_PROVINCE_ID="province_id";

    //GetAreasModelFields
    public static final String[] AREAS_MODEL_FIELDS = {"id","Title","city_id"};
//    public static final String PREF_GET_AREAS_ID="id";
//    public static final String PREF_GET_AREAS_TITLE="Title";
//    public static final String PREF_GET_AREAS_CITY_ID="city_id";

    //GetDistrictsModelFields
    public static final String[] DISTRICT_MODEL_FIELDS = {"id","Title","area_id"};
//    public static final String DISTRICT_MODEL_ID="id";
//    public static final String DISTRICT_MODEL_TITLE="Title";
//    public static final String DISTRICT_MODEL_AREA_ID="area_id";

    //GetLandTypeModelFields
//    public static final String PREF_GET_LAND_TYPE_NAME = "PREF_GET_LAND_TYPE";
    public static final String[] LAND_TYPE_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_LAND_TYPE_ID="id";
//    public static final String PREF_GETLAND_TYPE_TITLE="Title";

    //GetBuildingConditionsModelFields
//    public static final String PREF_GET_BUILDING_CONDITIONS_NAME = "PREF_GET_BUILDING_CONDITIONS";
    public static final String[] BUILDING_CONDITIONS_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_BUILDING_CONDITIONS_ID="id";
//    public static final String PREF_GET_BUILDING_CONDITIONS_Title="Title";

    //GetLandStatesModelFields
//    public static final String PREF_GET_LAND_STATES_NAME = "PREF_GET_LAND_STATES";
    public static final String[] LAND_STATES_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_LAND_STATES_ID="id";
//    public static final String PREF_GET_LAND_STATES_TITLE="Title";

    //GetRentalPreferencesModelFields
//    public static final String PREF_GET_RENTAL_PREFERENCES_NAME = "PREF_GET_RENTAL_PREFERENCES";
    public static final String[] RENTAL_PREFERENCES_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_RENTAL_PREFERENCES_ID = "id";
//    public static final String PREF_GET_RENTAL_PREFERENCES_TITLE = "Title";

    //GetDensityTypesModelFields
//    public static final String PREF_GET_DENSITY_TYPES_NAME = "PREF_GET_DENSITY_TYPES";
    public static final String[] DENSITY_TYPES_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_DENSITY_TYPES_ID = "id";
//    public static final String PREF_GET_DENSITY_TYPE_TITLE = "Title";

    //GetFloorCoveringsModelFields
//    public static final String PREF_GET_FLOOR_COVERINGS_NAME = "PREF_GET_FLOOR_COVERINGS";
    public static final String[]FLOOR_COVERINGS_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_FLOOR_COVERINGS_ID = "id";
//    public static final String PREF_GET_FLOOR_COVERINGS_TITLE = "Title";

    //GetKitchenServices
//    public static final String PREF_GET_KITCHEN_SERVICES_NAME = "PREF_GET_KITCHEN_SERVICES";
    public static final String[] KITCHEN_SERVICES_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_KITCHEN_SERVICES_ID = "id";
//    public static final String PREF_GET_KITCHEN_SERVICES_TITLE = "Title";

    //GetLandCasesModelFields
//    public static final String PREF_GET_LAND_CASE_NAME = "PREF_GET_LAND_CASE";
    public static final String[] LAND_CASE_MODEL_FIELDS =  {"id","Title"};
//    public static final String PREF_GET_LAND_CASE_ID = "id";
//    public static final String PREF_GET_LAND_CASE_TITLE = "Title";

    //GetLoanTypesModelFields
//    public static final String PREF_GET_LOAN_TYPES_NAME = "PREF_GET_LOAN_TYPES";
    public static final String[] LOAN_TYPES_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_LOAN_TYPES_ID = "id";
//    public static final String PREF_GET_LOAN_TYPES_TITLE = "Title";

    //GetLandSituationsModelFields
//    public static final String PREF_GET_LAND_SITUATIONS_NAME = "PREF_GET_LAND_SITUATIONS";
    public static final String[] LAND_SITUATIONS_MODEL_FIELDS = {"id","Title","Color"};
//    public static final String PREF_GET_LAND_SITUATIONS_ID = "id";
//    public static final String PREF_GET_LAND_SITUATIONS_TITLE = "Title";
//    public static final String PREF_GET_LAND_SITUATIONS_COLOR = "Color";

    //GetLandViewsModelFields
//    public static final String PREF_GET_LAND_VIEWS_NAME = "PREF_GET_LAND_VIEWS";
    public static final String[] LAND_VIEWS_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_LAND_VIEWS_ID = "id";
//    public static final String PREF_GET_LAND_VIEWS_TITLE = "Title";

    //GetLandDirectionsModelFields
//    public static final String PREF_GET_LAND_DIRECTIONS_NAME = "PREF_GET_LAND_DIRECTIONS";
    public static final String[] LAND_DIRECTIONS_MODEL_FIELDS = {"id","Title"};
//    public static final String PREF_GET_LAND_DIRECTIONS_ID = "id";
//    public static final String PREF_GET_LAND_DIRECTIONS_TITLE = "Title";
}

package ir.hamedanmelk.hamedanmelk.tools;

public class Constants {

    //HttpRequestHandler
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";

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
    public static final String RENT_MODEL_TITLE="Title";
    public static final String RENT_MODEL_LAND_STATE_ID="land_state_id";
    public static final String RENT_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String RENT_MODEL_VIEW="View";
    public static final String RENT_MODEL_IMAGES="View";
    public static final String RENT_MODEL_LAND_STATE_TITLE="LandStateTitle";
    public static final String RENT_MODEL_LAND_SITUATION_TITLE="LandSituationTitle";
    public static final String RENT_MODEL_LAND_SITUATION_COLOR="LandSituationColor";
    public static final String RENT_MODEL_FIRST_NAME="first_name";
    public static final String RENT_MODEL_LAST_NAME="last_name";
    public static final String[] RENT_MODEL_FIELDS={"id","Title","land_state_id","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};
    //Lands model
    public static final String LAND_MODEL_ID="id";
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
    public static final String PRE_SALE_MODEL_TITLE="Title";
    public static final String PRE_SALE_MODEL_LAND_STATE_ID="land_state_id";
    public static final String PRE_SALE_MODEL_CREATED_AT="created_at";
    public static final String PRE_SALE_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String PRE_SALE_MODEL_VIEW="View";
    public static final String PRE_SALE_MODEL_IMAGES="Images";
    public static final String PRE_SALE_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String PRE_SALE_MODEL_LAND_SITUATIONTITLE="LandSituationTitle";
    public static final String PRE_SALE_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String PRE_SALE_MODEL_FIRST_NAME="first_name";
    public static final String PRE_SALE_MODEL_LAST_NAME="last_name";
    public static final String[] PRE_SALE_MODEL_FIELDS={"id","Title","land_state_id","created_at","land_situation_id","View","Images","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};


    //Sale model
    public static final String SALE_MODEL_ID="id";
    public static final String SALE_MODEL_TITLE="Title";
    public static final String SALE_MODEL_LAND_STATE_ID="land_state_id";
    public static final String SALE_MODEL_CREATED_AT="created_at";
    public static final String SALE_MODEL_LAND_SITUATION_ID="land_situation_id";
    public static final String SALE_MODEL_VIEW="View";
    public static final String SALE_MODEL_IMAGES="Images";
    public static final String SALE_MODEL_LANDSTATETITLE="LandStateTitle";
    public static final String SALE_MODEL_LAND_SITUATIONTITLE="LandSituationTitle";
    public static final String SALE_MODEL_LANDSITUATIONCOLOR="LandSituationColor";
    public static final String SALE_MODEL_FIRST_NAME="first_name";
    public static final String SALE_MODEL_LAST_NAME="last_name";
    public static final String[] SALE_MODEL_FIELDS={"id","Title","land_state_id","created_at","land_situation_id","View","LandStateTitle","LandSituationTitle","LandSituationColor","first_name","last_name"};

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
    public static final String LAND_INFO_BUILDINGYEAR="BuildingYear";
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
    public static final String[] LAND_INFO_FIELDS={"id","Title","Address","Map","Images","Videos","province_id","city_id","area_id","district_id","land_type_id","building_condition_id","BuildingYear","land_state_id","SaleTotalPrice","SaleTotalPriceUnit","DebtTotalPrice","DebtTotalPriceUnit","MortgageTotalPrice","MortgageTotalPriceUnit","RentTotalPrice","RentTotalPriceUnit","PrePayPrice","PrePayPriceUnit","DeliveryDate","rental_preference_id","ResidentOwner","voucher_type_id","Dong","density_id","Space","space_unit_id","FoundationSpace","foundation_space_unit_id","SpacePrice","direction_id","land_view_id","floor_covering_id","kitchen_service_id","RoomCount","FloorCount","UnitInFloor","Floor","land_case_id","loan_type_id","land_situation_id","user_id","land_owner_id","View","created_at","updated_at","LandTypeTitle","LandStateTitle","ProvinceTitle","CityTitle","AreaTitle","DistrictTitle","first_name","last_name","UserDescription"};
}

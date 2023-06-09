package ir.hamedanmelk.hamedanmelk.tools;

import java.sql.SQLTransactionRollbackException;

public class Constants {

    //HttpRequestHandler
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    // Default Map Location
    public static final double MAP_MEYDAN_LAT = 34.798315;
    public static final double MAP_MEYDAN_LNG= 48.514898;
    public static final String ZERO =  "0";
    public static final String ONE = "1";

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
    /////////////////Bookmarks/////////////////////////
    public static final String BOOKMARK_ID ="id";

    //RegisterPublicUser
    public static final String REGISTER_PUBLIC_USER_FIRST_NAME="FirstName";
    public static final String REGISTER_PUBLIC_USER_LAST_NAME="LastName";
    public static final String REGISTER_PUBLIC_USER_GENDER_TYPE="GenderType";
    public static final String REGISTER_PUBLIC_USER_MOBILE="Mobile";
    public static final String[] REGISTER_PUBLIC_USER_MODEL_FIELDS={"FirstName","LastName","GenderType","Mobile"};

    //RegisterAgencyPublicUser
    public static final String REGISTER_AGENCY_PUBLIC_USER_FIRST_NAME="FirstName";
    public static final String REGISTER_AGENCY_PUBLIC_USER_LAST_NAME="LastName";
    public static final String REGISTER_AGENCY_PUBLIC_USER_AGENCY_NAME="AgencyName";
    public static final String REGISTER_AGENCY_PUBLIC_USER_MANAGER_NAME="ManagerName";
    public static final String REGISTER_AGENCY_PUBLIC_USER_PHONE ="AgencyPhone";
    public static final String REGISTER_AGENCY_PUBLIC_USER_MOBILE="Mobile";
    public static final String[] REGISTER_AGENCY_PUBLIC_USER_MODEL_FIELDS={"FirstName","LastName","AgencyName","ManagerName","AgencyPhone","Mobile"};

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
    public static final String LAND_MODEL_LAND_CASE_ID="land_case_id";
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


    /////////////////////////////Land Equipments//////////////////////////////
    public static final String LAND_EQUIPMENTS_ID = "id";
    public static final String LAND_EQUIPMENTS_TITLE = "Title";
    public static final String LAND_EQUIPMENTS_LOGO = "Logo";
    public static final String[] EQUIPMENTS_MODEL_FIELDS = {"id","Title","Logo"};

    ////////////////////////////////Links//////////////////////////////////////
    public static final String LINKS_ID = "id";
    public static final String LINKS_TITLE = "Title";
    public static final String LINKS_lINK  = "Link";
    public static final String LINKS_LOGO = "Logo";
    public static final String LINKS_ORDER = "Order";
    public static final String LINKS_DISABLED = "Disabled";

    //////////////////////////////CompanyTypes//////////////////////////////////////
    public static final String[] COMPANY_TYPES_MODEL_FIELDS = {"id","Title","CTOrder","parent_id"};
    public static final String COMPANY_TYPES_ID = "id";
    public static final String COMPANY_TYPES_TITLE = "Title";
    public static final String COMPANY_TYPES_ORDER = "CTOrder";
    public static final String COMPANY_TYPES_PARENT_ID = "parent_id";

    ///////////////////Add Company//////////////////////////////
    public static final String[] ADD_COMPANY_TEXT_FIELDS ={"UID","Title","Address","Phone","SubCompanyTypeID"};
    public static final String ADD_COMPANY_UID = "UID";
    public static final String ADD_COMPANY_TITLE = "Title";
    public static final String ADD_COMPANY_ADDRESS ="Address";
    public static final String ADD_COMPANY_PHONE="Phone";
    public static final String ADD_COMPANY_SUB_COMPANY_TYPE_ID = "SubCompanyTypeID";
    public static final String ADD_COMPANY_MANAGER = "Manager";
    public static final String ADD_COMPANY_LOGO = "Logo";


    ///////////////////Add Office//////////////////////////////
    public static final String ADD_OFFICE_TITLE = "Title";
    public static final String ADD_OFFICE_MANAGER = "Manager";
    public static final String ADD_OFFICE_ADDRESS ="Address";
    public static final String ADD_OFFICE_LOGO = "Logo";
    public static final String ADD_OFFICE_FAX = "Fax";
    public static final String ADD_OFFICE_NO="No";
    public static final String ADD_OFFICE_PHONE="Phone";
    public static final String ADD_OFFICE_PROVINCE_ID="ProvinceID";
    public static final String ADD_OFFICE_CITY="CityID";
    public static final String ADD_OFFICE_AREA_ID="AreaID";
    public static final String ADD_OFFICE_DISTRICID="DistrictID";
    public static final String ADD_OFFICE_UID = "UID";

    public static final String[] COMPANY_FIELDS ={"id","Title","Manager","Phone","Address","company_type_id","user_id","Disabled","Logo","created_at"};
    public static final String COMPANY_ID= "id";
    public static final String COMPANY_TITLE = "Title";
    public static final String COMPANY_MANAGER="Manager";
    public static final String COMPANY_PHONE="Phone";
    public static final String COMPANY_ADDRESS= "Address";
    public static final String COMPANY_COMPANY_TYPE_ID = "company_type_id";
    public static final String COMPANY_USER_ID ="user_id";
    public static final String COMPANY_DISABLED = "Disabled";
    public static final String COMPANY_LOGO ="Logo";
    public static final String COMPANY_CREATED_AT= "created_at";
    public static final String COMPANY_CID = "CID";
    public static final String LAND_INFO_LID = "LID";
    public static final String LAND_INFO_UID = "UID";

    public static final String COMPANY_ADD_UID = "UID";
    public static final String COMPANY_ADD_TITLE = "Address";
    public static final String COMPANY_ADD_PHONE = "Phone";
    public static final String COMPANY_ADD_SUB_COMPANY_TYPE_ID="SubCompanyTypeID";
    public static final String COMPANY_ADD_LOGO = "Logo";
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

    public static final String[] USE_TYPE_MODEL_FIELDS = {"id","Title"};

    public static final String[] VOUCHERS_MODEL_FIELDS = {"id","Title"};

    public static final String START_CHAT_UID = "UID";
    public static final String START_CHAT_TO = "To";



    public static final String NEW_LAND_LATITIUDE="Latitude";
    public static final String NEW_LAND_LONGITUDE="Longitude";
    public static final String NEW_LAND_TITLE="Title";
    public static final String NEW_LAND_IMAGE_FILE="ImageFile";
    public static final String NEW_LAND_USE_TYPE_ID="UseTypeID";
    public static final String NEW_LAND_EQUIPMENT="Equipment";
    public static final String NEW_LAND_LAND_STATE_ID="LandStateID";
    public static final String NEW_LAND_CHK_EXCHANGED="ChkExchanged";
    public static final String NEW_LAND_DONG="Dong";
    public static final String NEW_LAND_UID="UID";
    public static final String NEW_LAND_VOUCHER_TYPE_ID="VoucherTypeID";
    public static final String NEW_LAND_SALE_TOTAL_PRICE="SaleTotalPrice";
    public static final String NEW_LAND_PRE_DONG="PreDong";
    public static final String NEW_LAND_PRE_VOUCHER_TYPE_ID="PreVoucherTypeID";
    public static final String NEW_LAND_DELIVERY_DATE="DeliveryDate";
    public static final String NEW_LAND_PRE_SALE_TOTAL_PRICE="PreSaleTotalPrice";
    public static final String NEW_LAND_EX_DONG="ExDong";
    public static final String NEW_LAND_EX_VOUCHER_TYPE_ID="ExVoucherTypeID";
    public static final String NEW_LAND_DESCRIPTION="Description";
    public static final String NEW_LAND_ADDRESS="Address";
    public static final String NEW_LAND_PROVINCE_ID="ProvinceID";
    public static final String NEW_LAND_CITY_ID="CityID";
    public static final String NEW_LAND_AREA_ID="AreaID";
    public static final String NEW_LAND_DISTRICT_ID="DistrictID";
    public static final String NEW_LAND_LAND_TYPE_ID="LandTypeID";
    public static final String NEW_LAND_BUILDING_CONDITION_ID="BuildingConditionID";
    public static final String NEW_LAND_BUILDING_YEAR="BuildingYear";
    public static final String NEW_LAND_DEBT_TOTAL_PRICE="DebtTotalPrice";
    public static final String NEW_LAND_MORTGAGE_TOTAL_PRICE="MortgageTotalPrice";
    public static final String NEW_LAND_RENTAL_TOTAL_PRICE="RentTotalPrice";
    public static final String NEW_LAND_PRE_PAY="PrePayPrice";
    public static final String NEW_LAND_RENTAL_PREFERENCE="RentalPreferenceID";
    public static final String NEW_LAND_RESIDENT_OWNER="ResidentOwner";
    public static final String NEW_LAND_FOUNDATION_SPACE="FoundationSpace";
    public static final String NEW_LAND_DIRECTION_ID="DirectionID";
    public static final String NEW_LAND_LAND_VIEW_ID="LandViewID";
    public static final String NEW_LAND_FLOOR_COVERING_ID="FloorCoveringID";
    public static final String NEW_LAND_KITCHEN_SERVICES="KitchenServiceID";
    public static final String NEW_LAND_ROOM_COUNT="RoomCount";
    public static final String NEW_LAND_FLOOR_COUNT="FloorCount";
    public static final String NEW_LAND_UNIT_IN_FLOOR="UnitInFloor";
    public static final String NEW_LAND_FLOOR="Floor";
    public static final String NEW_LAND_LAND_CASE_ID="LandCaseID";
    public static final String NEW_LAND_LOAN_TYPE_ID="LoanTypeID";
    public static final String NEW_LAND_WATER="Water";
    public static final String NEW_LAND_GAS="Gas";
    public static final String NEW_LAND_ELECTRICITY="Electricy";
    public static final String NEW_LAND_PHONE="Phone";



    public static final String OFFICE_ID = "id";
    public static final String OFFICE_TITLE = "Title";
    public static final String OFFICE_MANAGER="Manager";
    public static final String OFFICE_NO = "No";
    public static final String OFFICE_ADDRESS = "Address";
    public static final String OFFICE_LOGO = "Logo";
    public static final String OFFICE_PHONE = "Phone";
    public static final String OFFICE_FAX = "Fax";
    public static final String OFFICE_DISABLED = "Disabled";
    public static final String OFFICE_PROVINCE_ID = "province_id";
    public static final String OFFICE_CITY_ID = "city_id";
    public static final String OFFICE_AREA_ID= "area_id";
    public static final String OFFICE_DISTRICT_ID = "district_id";
    public static final String OFFICE_USER_ID = "user_id";
    public static final String OFFICE_CREATED_AT = "created_at";



    public static final String LAWYER_ID = "id";
    public static final String LAWYER_FULL_NAME = "FullName";
    public static final String LAWYER_IMAGE="Image";
    public static final String LAWYER_DESCRIPTION = "Description";
    public static final String LAWYER_DISABLED = "Disabled";
    public static final String LAWYER_PHONE = "Phone";
    public static final String LAWYER_USER_ID = "user_id";
    public static final String LAWYER_CREATED_AT = "created_at";

    public static final String AGENCY_ ="id";
    public static final String AGENCY_TITLE ="Title";
    public static final String AGENCY_OWNER ="Owner";
    public static final String AGENCY_MANAGER ="Manager";
    public static final String AGENCY_ADDRESS ="Address";
    public static final String AGENCY_PROVINCE_ID ="province_id";
    public static final String AGENCY_CITY_ID ="city_id";
    public static final String AGENCY_AREA_ID ="area_id";
    public static final String AGENCY_DISTRICT_ID ="district_id";
    public static final String AGENCY_USER_ID ="user_id";
    public static final String AGENCY_MOBILE ="Mobile";
    public static final String AGENCY_PHONE ="Phone";
    public static final String AGENCY_LOGO ="Logo";
    public static final String AGENCY_DISABLED ="Disabled";
    public static final String AGENCY_CREATE_AT ="created_at";
    public static final String AGENCY_USER_IMAGE ="UserImage";

    public static final String ENERGY_LAND_ID = "land_id";
    public static final String ENERGY_TYPE_ID="energy_type_id";
    public static final String ENERGY_STATE_ID="energy_state_id";
    public static final String ENERGY_TYPE_TITLE="EnergyTypeTitle";
    public static final String ENERGY_STATE_TITLE="EnergyStateTitle";



}
